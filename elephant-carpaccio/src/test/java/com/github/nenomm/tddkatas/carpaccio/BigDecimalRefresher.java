package com.github.nenomm.tddkatas.carpaccio;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import org.junit.jupiter.api.Test;

public class BigDecimalRefresher {

  @Test
  public void defaultScaleForIntsIsZero() {
    final BigDecimal number = new BigDecimal("2");
    assertEquals(0, number.scale());
  }

  @Test
  public void shouldHaveThisScale() {
    assertEquals(0, new BigDecimal("2").scale());
    assertEquals(0, new BigDecimal("2.").scale());
    assertEquals(1, new BigDecimal("2.0").scale());
    assertEquals(2, new BigDecimal("2.00").scale());
  }

  @Test
  public void shouldHaveThisPrecision() {
    assertEquals(1 + 0, new BigDecimal("2").precision());
    assertEquals(1 + 0, new BigDecimal("2.").precision());
    assertEquals(1 + 1, new BigDecimal("2.0").precision());
    assertEquals(1 + 2, new BigDecimal("2.00").precision());
  }

  @Test
  public void unusedScaleIsNotIgnored() {
    final BigDecimal first = new BigDecimal("2.000");
    assertEquals(3, first.scale());
  }

  @Test
  public void unusedExpansionIsNotIgnored() {
    final BigDecimal first = new BigDecimal("2.000");

    assertEquals("2.000", first.toPlainString());
    assertEquals(3, first.scale());

    final BigDecimal second = first.setScale(5);

    assertEquals("2.00000", second.toPlainString());
    assertEquals(5, second.scale());
  }

  @Test
  public void newScaleAffectsPrecision() {
    final BigDecimal first = new BigDecimal("2.000");

    assertEquals("2.000", first.toPlainString());
    assertEquals(4, first.precision());

    final BigDecimal second = first.setScale(5);

    assertEquals("2.00000", second.toPlainString());
    assertEquals(6, second.precision());
  }

  @Test
  public void unusedPrecisionIsIgnored() {
    final MathContext mc = new MathContext(6);
    final BigDecimal first = new BigDecimal("2", mc);

    assertEquals("2", first.toPlainString());
    assertEquals(1, first.precision());
  }

  @Test
  public void cannotBeRepresented() {
    final BigDecimal first = new BigDecimal("1");
    final BigDecimal second = new BigDecimal("3");

    assertThrows(ArithmeticException.class, () -> first.divide(second));
  }

  // https://blogs.oracle.com/javamagazine/post/four-common-pitfalls-of-the-bigdecimal-class-and-how-to-avoid-them
  @Test
  public void precisionAffectsTheNumber() {
    final MathContext mc = new MathContext(4);

    assertEquals(4, new BigDecimal("62.123456", mc).precision());

    assertEquals("62.12", new BigDecimal("62.123456", mc).toPlainString());
    assertEquals("162.1", new BigDecimal("162.123456", mc).toPlainString());
    assertEquals("1162", new BigDecimal("1162.123456", mc).toPlainString());
    assertEquals("11620", new BigDecimal("11624.123456", mc).toPlainString());
  }

  @Test
  public void afterTruncationPrecisionIsLost() {
    final MathContext mc1 = new MathContext(4);

    final BigDecimal truncated = new BigDecimal("123456234", mc1);

    assertEquals("123500000", truncated.toPlainString());
    assertEquals(4, truncated.precision());

    final MathContext mc2 = new MathContext(9);
    final BigDecimal exact = new BigDecimal("123456234", mc2);

    assertEquals("123456234", exact.toPlainString());
    assertEquals(9, exact.precision());

    final BigDecimal expanded = truncated.round(mc2);
    assertEquals("123500000", expanded.toPlainString());

    // precision is ignored again
    assertEquals(4, expanded.precision());
  }

  @Test
  public void shouldHaveDefinedPrecisionButDifferentScale() {
    final MathContext mc = new MathContext(4);

    assertEquals(4, new BigDecimal("123462.123456", mc).precision());

    // https://blogs.oracle.com/javamagazine/post/four-common-pitfalls-of-the-bigdecimal-class-and-how-to-avoid-them
    assertEquals("123500", new BigDecimal("123462.123456", mc).toPlainString());
    assertEquals(-2, new BigDecimal("123462.123456", mc).scale());

    // do not mix precisions
    final BigDecimal result = BigDecimal.ONE.multiply(new BigDecimal("123462.123456", mc));

    assertEquals("123500", result.toPlainString());
    assertEquals(-2, result.scale());
  }

  @Test
  public void digitsAreNotPreservedSomewhere() {
    final MathContext mc1 = new MathContext(4);
    final MathContext mc2 = new MathContext(6);
    final BigDecimal rounded = new BigDecimal("123462.123456", mc1);

    assertEquals("123500", rounded.toPlainString());

    final BigDecimal expanded = rounded.round(mc2);
    assertNotEquals("123462", expanded.toPlainString());
    assertEquals("123500", expanded.toPlainString());
  }

  @Test
  public void originalNumberIsNotPreserved() {
    final BigDecimal exact = new BigDecimal("1234.5678");

    assertEquals("1234.5678", exact.toPlainString());
    assertEquals(8, exact.precision());
    assertEquals(4, exact.scale());

    final BigDecimal rounded = exact.setScale(2, RoundingMode.HALF_UP);

    assertEquals("1234.57", rounded.toPlainString());
    assertEquals(6, rounded.precision());
    assertEquals(2, rounded.scale());

    // if scale is more than needed it is ignored
    final BigDecimal expanded = rounded.setScale(4, RoundingMode.HALF_UP);

    assertEquals("1234.57", rounded.toPlainString());
    assertEquals(6, rounded.precision());
    assertEquals(2, rounded.scale());

    // original scale is preserved even though it is not needed anymore
    final BigDecimal result = rounded.multiply(BigDecimal.valueOf(100));

    assertEquals("123457.00", result.toPlainString());
    assertEquals(8, result.precision());
    assertEquals(2, result.scale());
  }

  @Test
  public void scaleIsAdjustedToAccommodateNumberWithMoreDecimals() {
    final BigDecimal first = new BigDecimal("1000.0001");

    assertEquals("1000.0001", first.toPlainString());
    assertEquals(8, first.precision());
    assertEquals(4, first.scale());

    final BigDecimal second = new BigDecimal("100.001");

    assertEquals("100.001", second.toPlainString());
    assertEquals(6, second.precision());
    assertEquals(3, second.scale());

    final BigDecimal result = first.multiply(second);

    assertEquals("100001.0100001", result.toPlainString());
    assertEquals(13, result.precision());
    assertEquals(7, result.scale());
  }
}
