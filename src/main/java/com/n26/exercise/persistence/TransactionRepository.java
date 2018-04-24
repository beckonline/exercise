package com.n26.exercise.persistence;


import com.n26.exercise.model.InternalTransaction;

import java.util.Collection;

public interface TransactionRepository {

    InternalTransaction persistTransaction(InternalTransaction transaction);

    void deleteTransaction(InternalTransaction transaction);

    void archiveTransaction(InternalTransaction transaction);

    Collection<InternalTransaction> getLatestTransactions();

    Collection<InternalTransaction> getArchivedTransactions();
}
