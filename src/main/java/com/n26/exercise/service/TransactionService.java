package com.n26.exercise.service;


import com.n26.exercise.TransactionTransformer;
import com.n26.exercise.api.model.Transaction;
import com.n26.exercise.model.InternalTransaction;
import com.n26.exercise.persistence.TransactionRepository;
import com.n26.exercise.utils.UTCTimeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Optional;

@Controller
public class TransactionService {

    @Autowired
    private TransactionTransformer transactionTransformer;

    @Autowired
    private TransactionRepository transactionRepository;

    public Optional<Transaction> executeTransaction(Transaction transaction) {

        InternalTransaction internalTransaction = transactionTransformer.toInternalTransaction(transaction);

        //could also be done with checked exception but went for optional because it's
        //not an exception and also to avoid processing of checked exception aftewards
        if (UTCTimeUtils.olderThan60Seconds(internalTransaction.getTransactionDate())) {
            transactionRepository.archiveTransaction(internalTransaction);
            return Optional.empty();

        }

        transactionRepository.persistTransaction(internalTransaction);
        return Optional.of(transaction);

    }
}
