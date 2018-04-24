package com.n26.exercise.test.utils;


import com.n26.exercise.TransactionTransformer;
import com.n26.exercise.api.model.Transaction;
import com.n26.exercise.model.InternalTransaction;

import java.time.Instant;
import java.time.ZoneOffset;

import static java.time.ZoneOffset.UTC;
import static java.time.ZonedDateTime.now;

public class TransactionBuilder {


    public static final String A_DAY_IN_MAY_1981 = "1981-05-08T10:00:00Z";
    public static final double TWO_EURO_FIFTY_CENT = 2.5;
    public static TransactionTransformer transactionTransformer = new TransactionTransformer();

    public static Transaction transactionWithAmount(double amount) {
        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setTimestamp(now().toInstant().toEpochMilli());
        return transaction;
    }

    public static Transaction transactionIn1981() {
        Transaction transaction = new Transaction();
        transaction.setAmount(TWO_EURO_FIFTY_CENT);
        transaction.setTimestamp(timeInMilisForDayInMay());
        return transaction;
    }

    public static InternalTransaction internalTransactionin1981() {
        return transactionTransformer.toInternalTransaction(transactionIn1981());
    }

    public static Transaction transaction30SecondsOlder() {
        Transaction transaction = new Transaction();
        transaction.setAmount(TWO_EURO_FIFTY_CENT);
        transaction.setTimestamp(now(UTC).minusSeconds(30).toInstant().toEpochMilli());
        return transaction;
    }

    public static InternalTransaction internalTransaction30SecondsOlder() {
        return transactionTransformer.toInternalTransaction(transaction30SecondsOlder());
    }

    public static Transaction transaction61SecondsOlder() {
        Transaction transaction = new Transaction();
        transaction.setAmount(TWO_EURO_FIFTY_CENT);
        transaction.setTimestamp(now(UTC).minusSeconds(61).toInstant().toEpochMilli());
        return transaction;
    }

    public static InternalTransaction internalTransaction61SecondsOlder() {
        return transactionTransformer.toInternalTransaction(transaction61SecondsOlder());
    }

    public static long timeInMilisForDayInMay() {
        return Instant.parse(A_DAY_IN_MAY_1981).atOffset(ZoneOffset.UTC).toInstant().toEpochMilli();
    }

}
