package com.github.nenomm.tddkatas.carpaccio;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
import java.util.Map;
import org.junit.jupiter.api.Test;

public class PriceCalculatorTest {

  private static final Map<String, BigDecimal> taxRates = Map.of(
      "UT", new BigDecimal("6.85"),
      "NV", new BigDecimal("8.00"),
      "TX", new BigDecimal("6.25"),
      "AL", new BigDecimal("4.00"),
      "CA", new BigDecimal("8.28")
  );

  private static final Map<BigDecimal, BigDecimal> discountRates = Map.of(
      new BigDecimal(1000), new BigDecimal(3),
      new BigDecimal(5000), new BigDecimal(5),
      new BigDecimal(7000), new BigDecimal(7),
      new BigDecimal(10000), new BigDecimal(10),
      new BigDecimal(15000), new BigDecimal(15)
  );
  private PriceCalculator calculator;

  @Test
  public void calculatorAcceptsStateTaxes() {
    assertDoesNotThrow(() -> calculator = new PriceCalculator(taxRates));
  }

  @Test
  public void calculatorAcceptsOptionalDiscounts() {
    calculator = new PriceCalculator(taxRates);
    assertDoesNotThrow(() -> calculator.setDiscountRates(discountRates));
  }

  @Test
  public void calculatePriceWithoutDiscounts() {
    calculator = new PriceCalculator(taxRates);
    assertEquals("1080.00", calculator.totalPrice(1, BigDecimal.valueOf(1000), "NV").toPlainString());
  }

  @Test
  public void calculatePriceWithDiscounts() {
    calculator = new PriceCalculator(taxRates);
    calculator.setDiscountRates(discountRates);
    assertEquals("1047.60", calculator.totalPrice(1, BigDecimal.valueOf(1000), "NV").toPlainString());
  }

}
