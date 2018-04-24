package com.n26.exercise.api;


import com.n26.exercise.api.model.Transaction;
import com.n26.exercise.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class TransactionResource {

    @Autowired
    private TransactionService transactionService;

    @RequestMapping(method = RequestMethod.POST, value = "/transactions")
    @ResponseBody
    public ResponseEntity transaction(@RequestBody Transaction transaction) {

        Optional<Transaction> executionResult = transactionService.executeTransaction(transaction);

        if (executionResult.isPresent()) {
            return new ResponseEntity(HttpStatus.OK);
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);

    }

}
