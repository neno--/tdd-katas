package com.github.nenomm.tddkatas.banking;

public class Account {

  private static final String HEADER_FORMAT = "%-12s%-8s%s";
  private static final String ROW_FORMAT = "%-10s%8s%8d";
  private int balance;
  private final DateProvider dateProvider;

  private final StringBuilder transactionLog;

  public Account(DateProvider dateProvider) {
    this.balance = 0;
    this.dateProvider = dateProvider;
    this.transactionLog = new StringBuilder();
  }

  public void deposit(int amount) {
    validateAmount(amount);

    balance += amount;
    addNewlineIfNeeded();
    transactionLog.append(createRow(amount));
  }

  public void withdraw(int amount) {
    validateAmount(amount);

    if (amount > balance) {
      throw new IllegalStateException("Withdraw amount is too high");
    }

    balance -= amount;
    addNewlineIfNeeded();
    transactionLog.append(createRow(-amount));
  }

  private void addNewlineIfNeeded() {
    if (transactionLog.length() != 0) {
      transactionLog.append(System.lineSeparator());
    }
  }

  private String createRow(int amount) {
    return String.format(ROW_FORMAT, dateProvider.dateString(), formatAmount(amount), balance);
  }

  private static String formatAmount(int amount) {
    if (amount > 0) {
      return String.format("+%d ", amount);
    } else if (amount < 0) {
      return String.format("%d ", amount);
    } else {
      throw new IllegalArgumentException("Amount cannot be 0");
    }
  }

  public String printStatement() {
    final StringBuilder stringBuilder = new StringBuilder();
    addHeader(stringBuilder);

    if (transactionLog.length() != 0) {
      stringBuilder.append(System.lineSeparator()).append(transactionLog);
    }

    return stringBuilder.toString();
  }

  private void addHeader(StringBuilder stringBuilder) {
    stringBuilder.append(String.format(HEADER_FORMAT, "Date", "Amount", "Balance"));
  }

  private void validateAmount(int amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("Invalid amount");
    }
  }
}
