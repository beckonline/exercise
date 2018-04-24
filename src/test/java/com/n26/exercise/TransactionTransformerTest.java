package com.n26.exercise;


import com.n26.exercise.model.InternalTransaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

import static com.n26.exercise.test.utils.TransactionBuilder.*;
import static com.n26.exercise.test.utils.TransactionBuilder.A_DAY_IN_MAY_1981;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TransactionTransformerTest {


    private static final double DELTA = 0;

    @Autowired
    private TransactionTransformer transactionTransformer;

    @Test
    public void toZonedDateTimeTest() {

        assertEquals(ZonedDateTime.parse(A_DAY_IN_MAY_1981), transactionTransformer.toZonedDateTime(timeInMilisForDayInMay()));

    }

    @Test
    public void toMoneyTest() {

        assertEquals(TWO_EURO_FIFTY_CENT,
                transactionTransformer.toMoneyWithDefaultEuro(TWO_EURO_FIFTY_CENT).getNumber().doubleValueExact(),
                DELTA);

    }

    @Test
    public void toTransactionTest() {

        InternalTransaction internalTransaction = transactionTransformer.toInternalTransaction(transactionIn1981());
        assertEquals(TWO_EURO_FIFTY_CENT,
                internalTransaction.getAmount().getNumber().doubleValueExact(),
                DELTA);
        assertEquals(ZonedDateTime.parse(A_DAY_IN_MAY_1981), internalTransaction.getTransactionDate());


    }


}
