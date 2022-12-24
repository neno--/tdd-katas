package com.github.nenomm.tddkatas.carpaccio;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Collections;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;

public class PriceCalculator {

  private Map<String, BigDecimal> taxRates;
  private Optional<Map<BigDecimal, BigDecimal>> discountRates;

  public PriceCalculator(Map<String, BigDecimal> taxRates) {
    this.taxRates = taxRates;
    this.discountRates = Optional.empty();
  }

  public void setDiscountRates(Map<BigDecimal, BigDecimal> discountRates) {
    this.discountRates = Optional.of(new TreeMap<>(discountRates));
  }

  public BigDecimal totalPrice(int numberOfItems, BigDecimal itemPrice, String stateCode) {
    final BigDecimal total = itemPrice.multiply(BigDecimal.valueOf(numberOfItems));
    final BigDecimal discount = calculateDiscount(total);

    final BigDecimal discountedTotal = total.multiply(BigDecimal.valueOf(100).subtract(discount).divide(BigDecimal.valueOf(100)));

    return applyTax(discountedTotal, stateCode).setScale(2, RoundingMode.HALF_UP);
  }

  private BigDecimal applyTax(BigDecimal total, String stateCode) {
    return total.multiply(taxRates.get(stateCode).divide(BigDecimal.valueOf(100)).add(BigDecimal.ONE));
  }

  private BigDecimal calculateDiscount(BigDecimal total) {
    return this.discountRates.orElse(Collections.emptyMap()).entrySet()
        .stream()
        .filter(entry -> entry.getKey().compareTo(total) <= 0)
        .reduce((first, second) -> second)
        .map(Entry::getValue)
        .orElse(BigDecimal.ZERO);
  }
}
