package com.n26.exercise.api;


import com.n26.exercise.api.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

import java.net.URI;

import static com.n26.exercise.test.utils.TransactionBuilder.transaction30SecondsOlder;
import static com.n26.exercise.test.utils.TransactionBuilder.transaction61SecondsOlder;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TransactionResourceIT {

    public static final String TRANSACTIONS_URL = "/transactions";
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldNotListenToGET() {
        assertTrue(restTemplate.getForEntity(TRANSACTIONS_URL, Void.class).getStatusCode().is4xxClientError());
    }

    @Test
    public void shouldNotListenToDELETE() {
        assertTrue(restTemplate.exchange(TRANSACTIONS_URL, HttpMethod.DELETE, null, Void.class).getStatusCode().is4xxClientError());
    }

    @Test
    public void shouldNotListenToPUT() {
        assertTrue(restTemplate.exchange(TRANSACTIONS_URL, HttpMethod.PUT, null, Void.class).getStatusCode().is4xxClientError());
    }

    @Test
    public void shouldListenToPost() {
        assertTrue(restTemplate.postForEntity(TRANSACTIONS_URL, new Transaction(), Void.class).getStatusCode().is2xxSuccessful());

    }

    @Test
    public void shouldReturn200WhenTransactionIs30SecondsOld() {

        assertEquals(HttpStatus.OK,
                restTemplate.postForEntity(TRANSACTIONS_URL, transaction30SecondsOlder(), Void.class).getStatusCode()
        );

    }

    @Test
    public void shouldReturn204WhenTransactionIs61SecondsOld() {

        assertEquals(
                HttpStatus.NO_CONTENT,
                restTemplate.postForEntity(TRANSACTIONS_URL, transaction61SecondsOlder(), Void.class).getStatusCode()
        );

    }

}
