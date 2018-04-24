package com.n26.exercise.persistence;


import com.n26.exercise.model.InternalTransaction;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryTransactionRepository implements TransactionRepository {

    private ConcurrentHashMap<Integer,InternalTransaction> latestTransactions = new ConcurrentHashMap<>();
    private ConcurrentHashMap<Integer,InternalTransaction> transactionsArchived = new ConcurrentHashMap<>();

    @Override
    public InternalTransaction persistTransaction(InternalTransaction transaction) {
        latestTransactions.put(transaction.hashCode(),transaction);
        return transaction;
    }

    @Override
    public void deleteTransaction(InternalTransaction transaction) {
        latestTransactions.remove(transaction.hashCode());
    }

    @Override
    public void archiveTransaction(InternalTransaction transaction) {
        transactionsArchived.put(transaction.hashCode(),transaction);
    }

    @Override
    public Collection<InternalTransaction> getLatestTransactions() {
        return latestTransactions.values();
    }

    @Override
    public Collection<InternalTransaction> getArchivedTransactions() {
        return transactionsArchived.values();
    }
}
