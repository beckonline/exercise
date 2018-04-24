package com.n26.exercise.persistence;

import com.n26.exercise.model.InternalTransaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;

import static com.n26.exercise.test.utils.TransactionBuilder.internalTransaction30SecondsOlder;
import static com.n26.exercise.test.utils.TransactionBuilder.internalTransactionin1981;
import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
public class InMemmoryTransactionRepositoryTest {

    @Autowired
    TransactionRepository transactionRepository;

    @Test
    public void persistTransactionAndGetTransactions() {
        InternalTransaction transaction = internalTransactionin1981();
        transactionRepository.persistTransaction(transaction);
        assertTrue(transactionRepository.getLatestTransactions().contains(transaction));
    }

    @Test
    public void persistTransactionDoesNotPersistDuplicates() {
        InternalTransaction transaction = internalTransactionin1981();

        transactionRepository.persistTransaction(transaction);
        transactionRepository.persistTransaction(transaction);

        assertTrue(transactionRepository.getLatestTransactions().contains(transaction));
        assertEquals(1, transactionRepository.getLatestTransactions().size());

    }

    @Test
    public void archieveTransactionAndGetArchievedTransactions() {
        InternalTransaction transaction = internalTransactionin1981();
        transactionRepository.archiveTransaction(transaction);
        assertTrue(transactionRepository.getArchivedTransactions().contains(transaction));
    }

    @Test
    public void archieveTransactionDoesNotPersistDuplicates() {
        InternalTransaction transaction = internalTransactionin1981();

        transactionRepository.archiveTransaction(transaction);
        transactionRepository.archiveTransaction(transaction);

        assertTrue(transactionRepository.getArchivedTransactions().contains(transaction));
        assertEquals(1, transactionRepository.getArchivedTransactions().size());

    }

    @Test
    public void deletesCorrectTransaction() {
        InternalTransaction toBeDeleted = internalTransactionin1981();
        transactionRepository.persistTransaction(toBeDeleted);
        transactionRepository.persistTransaction(internalTransaction30SecondsOlder());

        assertTrue(transactionRepository.getLatestTransactions().contains(toBeDeleted));
        assertEquals(2, transactionRepository.getLatestTransactions().size());

        transactionRepository.deleteTransaction(toBeDeleted);

        assertFalse(transactionRepository.getLatestTransactions().contains(toBeDeleted));
        assertEquals(1, transactionRepository.getLatestTransactions().size());
    }


}
