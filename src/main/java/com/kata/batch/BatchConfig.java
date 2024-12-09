package com.kata.batch;

import com.kata.model.FooBarModel;
import com.kata.service.FooBarService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {
    private final FooBarService fooBarService;

    public BatchConfig(FooBarService fooBarService) {
        this.fooBarService = fooBarService;
    }
    @Bean
    public FlatFileItemReader<FooBarModel> reader() {
        FlatFileItemReader<FooBarModel> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("src/main/resources/input.txt"));
        reader.setLineMapper((line, lineNumber) -> {
            FooBarModel fooBarModel = new FooBarModel();
            fooBarModel.setNumber(Integer.parseInt(line.trim()));
            return fooBarModel;
        });
        return reader;
    }

    @Bean
    public FlatFileItemWriter<FooBarModel> writer() {
        FlatFileItemWriter<FooBarModel> writer = new FlatFileItemWriter<>();
        writer.setResource(new FileSystemResource("src/main/resources/output.txt"));
        writer.setAppendAllowed(true);
        writer.setLineAggregator(item -> {
            return item.getNumber() + " " + item.getResult();
        });
        return writer;

    }

    @Bean
    public ItemProcessor<FooBarModel, FooBarModel> itemProcessor() {
        return item -> {
            String resul = fooBarService.checkNumber(item.getNumber());
            item.setResult(resul);
            return item;
        };
    }


    @Bean
    public Step step(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("step", jobRepository)
                .<FooBarModel, FooBarModel>chunk(10, transactionManager)
                .reader(reader())
                .processor(itemProcessor())
                .writer(writer())
                .build();
    }

    @Bean
    public Job job(JobRepository jobRepository, Step step) {
        return new JobBuilder("job", jobRepository).start(step).build();
    }
}
