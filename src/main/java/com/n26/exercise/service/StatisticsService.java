package com.n26.exercise.service;


import com.n26.exercise.api.model.Statistics;
import com.n26.exercise.model.InternalTransaction;
import com.n26.exercise.persistence.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Collection;
import java.util.Comparator;

import static java.util.Comparator.comparing;

@Controller
public class StatisticsService {

    @Autowired
    private TransactionRepository transactionRepository;


    public Statistics getStatistics() {


        Collection<InternalTransaction> transactions = transactionRepository.getLatestTransactions();

        return getStatistics(transactions);
    }

    private Statistics getStatistics(Collection<InternalTransaction> transactions) {

        Statistics stats = new Statistics();

        int size = transactions.size();
        if (size > 0) {
            double sum = getSum(transactions);
            stats.setMin(transactions.stream()
                    .min(comparing(InternalTransaction::getAmountAsDouble)).get().getAmountAsDouble());
            stats.setMax(transactions.stream()
                    .max(comparing(InternalTransaction::getAmountAsDouble)).get().getAmountAsDouble());
            stats.setCount(size);
            stats.setSum(sum);
            stats.setAverage(sum / size);
        }
        return stats;
    }

    private double getSum(Collection<InternalTransaction> transactions) {
        return transactions.stream().mapToDouble(InternalTransaction::getAmountAsDouble).sum();
    }
}
