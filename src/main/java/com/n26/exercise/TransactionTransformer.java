package com.n26.exercise;


import com.google.common.annotations.VisibleForTesting;
import com.google.gag.annotation.team.Visionary;
import com.n26.exercise.api.model.Transaction;
import com.n26.exercise.model.InternalTransaction;
import org.springframework.stereotype.Controller;

import javax.money.Monetary;
import javax.money.MonetaryAmount;
import java.time.Instant;
import java.time.ZonedDateTime;

import static java.time.ZoneOffset.UTC;

@Controller
public class TransactionTransformer {


    public InternalTransaction toInternalTransaction(Transaction transaction) {
        InternalTransaction internal = new InternalTransaction();

        internal.setTransactionDate(toZonedDateTime(transaction.getTimestamp()));
        internal.setAmount(toMoneyWithDefaultEuro(transaction.getAmount()));

        return internal;

    }

    @Visionary("Yeap... since the interview was for an online bank I assumed that we were talking about money and so I assumed" +
            "a default currency - EUR")
    @VisibleForTesting
    protected MonetaryAmount toMoneyWithDefaultEuro(double amount) {
        return Monetary.getDefaultAmountFactory().setNumber(amount).setCurrency("EUR").create();
    }

    @VisibleForTesting
    protected ZonedDateTime toZonedDateTime(long timestamp) {

        Instant transactionInstant = Instant.ofEpochMilli(timestamp);
        return ZonedDateTime.ofInstant(transactionInstant, UTC);
    }
}
