package com.spring.quartz.action;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class DailyMarketJob implements Job {

    private RestTemplate restTemplate;

    @Value("${historicalDataApi}")
    private String historicalDataApi;

    @Autowired
    public void setRestTemplate(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        try {
            ApplicationContext applicationContext = (ApplicationContext) context.getScheduler().getContext().get("applicationContext");

            ObjectMapper objectMapper = applicationContext.getBean(ObjectMapper.class);

            log.info("Action ** {} ** starting @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());
            log.info("Stored Data {}", objectMapper.writeValueAsString(context.getJobDetail().getJobDataMap()));
            String result = restTemplate.getForObject(historicalDataApi, String.class);
            log.info("GET request for historical Data:{}", result);
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
