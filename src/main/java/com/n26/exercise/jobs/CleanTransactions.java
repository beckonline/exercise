package com.n26.exercise.jobs;


import com.n26.exercise.model.InternalTransaction;
import com.n26.exercise.persistence.TransactionRepository;
import com.n26.exercise.utils.UTCTimeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.TreeSet;

@Component
public class CleanTransactions {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TransactionRepository transactionRepository;

    @Scheduled(fixedRate = 1000)
    public void cleanOldTransactions() {
        Collection<InternalTransaction> transactions =
                transactionRepository.getLatestTransactions();
        for (InternalTransaction transaction : transactions) {
            if (UTCTimeUtils.olderThan60Seconds(transaction.getTransactionDate())) {
                transactionRepository.archiveTransaction(transaction);
                transactionRepository.deleteTransaction(transaction);
                log.debug("Cleaned transaction.");
            }

        }


    }
}
