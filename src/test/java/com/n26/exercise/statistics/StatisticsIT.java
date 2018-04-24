package com.n26.exercise.statistics;


import com.n26.exercise.api.model.Statistics;
import com.n26.exercise.service.StatisticsService;
import com.n26.exercise.service.TransactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.n26.exercise.test.utils.TransactionBuilder.transaction61SecondsOlder;
import static com.n26.exercise.test.utils.TransactionBuilder.transactionIn1981;
import static com.n26.exercise.test.utils.TransactionBuilder.transactionWithAmount;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class StatisticsIT {

    private static final double DELTA = 0;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private StatisticsService statisticsService;

    @Test
    public void statisticsShouldBeCorrectForLatestTransactions(){
        transactionService.executeTransaction(transactionWithAmount(23.5));
        transactionService.executeTransaction(transactionWithAmount(15));
        transactionService.executeTransaction(transactionWithAmount(21.5));

        Statistics stats = statisticsService.getStatistics();

        assertEquals(3,stats.getCount());
        assertEquals(15,stats.getMin(),DELTA);
        assertEquals(23.5,stats.getMax(),DELTA);
        assertEquals(60,stats.getSum(),DELTA);
        assertEquals(20,stats.getAverage(),DELTA);

    }

    @Test
    public void transactionsOlderThan60SecondsShouldNotBeConsideredForStats(){
        transactionService.executeTransaction(transactionIn1981());
        transactionService.executeTransaction(transaction61SecondsOlder());

        Statistics stats = statisticsService.getStatistics();

        assertEquals(0,stats.getCount());
        assertEquals(0,stats.getMin(),DELTA);
        assertEquals(0,stats.getMax(),DELTA);
        assertEquals(0,stats.getSum(),DELTA);
        assertEquals(0,stats.getAverage(),DELTA);

    }


}
