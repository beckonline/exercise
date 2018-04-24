package com.n26.exercise.api;


import com.n26.exercise.api.model.Statistics;
import com.n26.exercise.service.StatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StatisticsResource {

    @Autowired
    private StatisticsService statisticsService;

    @RequestMapping(method = RequestMethod.GET, value = "/statistics")
    @ResponseBody
    public Statistics statistics() {

        return statisticsService.getStatistics();

    }

}
