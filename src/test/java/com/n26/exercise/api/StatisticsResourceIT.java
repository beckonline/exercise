package com.n26.exercise.api;

import com.n26.exercise.api.model.Transaction;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class StatisticsResourceIT {

    public static final String STATISTICS_URL = "/statistics";
    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void shouldListenToGET() {
        assertTrue(restTemplate.getForEntity(STATISTICS_URL, Void.class).getStatusCode().is2xxSuccessful());
    }

    @Test
    public void shouldNotListenToDELETE() {
        assertTrue(restTemplate.exchange(STATISTICS_URL, HttpMethod.DELETE, null, Void.class).getStatusCode().is4xxClientError());
    }

    @Test
    public void shouldNotListenToPUT() {
        assertTrue(restTemplate.exchange(STATISTICS_URL, HttpMethod.PUT, null, Void.class).getStatusCode().is4xxClientError());
    }

    @Test
    public void shouldNotListenToPost() {
        assertTrue(restTemplate.postForEntity(STATISTICS_URL, Void.class, Void.class).getStatusCode().is4xxClientError());

    }

}
