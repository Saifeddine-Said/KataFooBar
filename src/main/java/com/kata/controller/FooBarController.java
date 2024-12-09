package com.kata.controller;

import com.kata.service.FooBarService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FooBarController {

    private final FooBarService fooBarService;

    private final JobLauncher jobLauncher;

    private final Job job;

    @Autowired
    public FooBarController(FooBarService fooBarService, JobLauncher jobLauncher, Job job) {
        this.fooBarService = fooBarService;
        this.jobLauncher = jobLauncher;
        this.job = job;
    }

    @GetMapping("/foobar")
    public ResponseEntity<String> fooBarQuix(@RequestParam int number) {
        String result = fooBarService.checkNumber(number);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping("/run-batch")
    public ResponseEntity<String> runBatch() {
        try {
            jobLauncher.run(job, new JobParameters());
            return ResponseEntity.ok("Batch job executed successfully!");
        } catch (JobExecutionException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Batch job execution failed: " + e.getMessage());
        }
    }

}
