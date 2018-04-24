package com.n26.exercise.model;


import javax.money.MonetaryAmount;
import java.time.ZonedDateTime;
import java.util.Comparator;

public class InternalTransaction implements Comparable<InternalTransaction> {

    private MonetaryAmount amount;
    private ZonedDateTime transactionDate;

    public MonetaryAmount getAmount() {
        return amount;
    }

    public void setAmount(MonetaryAmount amount) {
        this.amount = amount;
    }

    public ZonedDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(ZonedDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public double getAmountAsDouble() {
        return amount.getNumber().doubleValueExact();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InternalTransaction that = (InternalTransaction) o;

        if (!amount.equals(that.amount)) return false;
        return transactionDate.equals(that.transactionDate);

    }

    @Override
    public int hashCode() {
        int result = amount.hashCode();
        result = 31 * result + transactionDate.hashCode();
        return result;
    }


    @Override
    public int compareTo(InternalTransaction other) {
        if (this.getAmountAsDouble() > other.getAmountAsDouble()) {
            return 1;
        }
        if (this.getAmountAsDouble() < other.getAmountAsDouble()) {
            return -1;
        } else {
            if (this.getTransactionDate().isAfter(other.getTransactionDate())) {
                return 1;
            }
            if (this.getTransactionDate().isBefore(other.getTransactionDate())) {
                return -1;
            }else {
                return 0;
            }
        }


    }
}
