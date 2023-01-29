package com.github.nenomm.tddkatas.banking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;

// https://kata-log.rocks/banking-kata
public class AccountTest {

  @Test
  public void initialStatementIsEmpty() {
    //given
    final Account account = new Account(null);

    // when
    final String printStatement = account.printStatement();

    // then
    assertEquals("Date        Amount  Balance", printStatement);
  }

  @Test
  public void depositOf1ShouldBeVisible() {
    final DateProvider dateProvider = mock(DateProvider.class);
    when(dateProvider.dateString()).thenReturn("24.12.2015");
    final Account account = new Account(dateProvider);

    // given
    account.deposit(1);

    // when
    final String[] lines = account.printStatement().split("\n");

    // then
    assertEquals("Date        Amount  Balance", lines[0]);
    assertEquals("24.12.2015     +1        1", lines[1]);
  }

  @Test
  public void depositOf500ShouldBeVisible() {
    final DateProvider dateProvider = mock(DateProvider.class);
    when(dateProvider.dateString()).thenReturn("24.12.2015");
    final Account account = new Account(dateProvider);

    // given
    account.deposit(500);

    // when
    final String[] lines = account.printStatement().split("\n");

    // then
    assertEquals("Date        Amount  Balance", lines[0]);
    assertEquals("24.12.2015   +500      500", lines[1]);
  }

  @Test
  public void withdrawalOf100ShouldBeVisible() {
    final DateProvider dateProvider = mock(DateProvider.class);
    when(dateProvider.dateString()).thenReturn("24.12.2015").thenReturn("23.8.2016");
    final Account account = new Account(dateProvider);

    // given
    account.deposit(500);
    account.withdraw(100);

    // when
    final String[] lines = account.printStatement().split("\n");

    // then
    assertEquals("Date        Amount  Balance", lines[0]);
    assertEquals("24.12.2015   +500      500", lines[1]);
    assertEquals("23.8.2016    -100      400", lines[2]);
  }

  @Test
  public void cannotDepositNegativeAmount() {
    final DateProvider dateProvider = mock(DateProvider.class);
    when(dateProvider.dateString()).thenReturn("24.12.2015").thenReturn("23.8.2016");
    final Account account = new Account(dateProvider);

    assertThrowsExactly(IllegalArgumentException.class, () -> account.deposit(-600));
  }

  @Test
  public void cannotWithdrawNegativeAmount() {
    final DateProvider dateProvider = mock(DateProvider.class);
    when(dateProvider.dateString()).thenReturn("24.12.2015").thenReturn("23.8.2016");
    final Account account = new Account(dateProvider);

    account.deposit(500);
    assertThrowsExactly(IllegalArgumentException.class, () -> account.withdraw(-50));
  }

  @Test
  public void cannotWithdrawMoreThanDeposited() {
    final DateProvider dateProvider = mock(DateProvider.class);
    when(dateProvider.dateString()).thenReturn("24.12.2015").thenReturn("23.8.2016");
    final Account account = new Account(dateProvider);

    account.deposit(500);

    assertThrowsExactly(IllegalStateException.class, () -> account.withdraw(600));
  }
}
