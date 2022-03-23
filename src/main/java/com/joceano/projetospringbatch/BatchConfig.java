package com.joceano.projetospringbatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableBatchProcessing
@Configuration
public class BatchConfig {

    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    private static final Logger LOGGER = LoggerFactory.getLogger(BatchConfig.class);

    public BatchConfig(
        JobBuilderFactory jobBuilderFactory,
        StepBuilderFactory stepBuilderFactory) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Job imprimeOlaJob() {
        return jobBuilderFactory
            .get("imprimeOlaJob")
            .start(imprimeOlaStep())
            .build();
    }

    public Step imprimeOlaStep() {
        return stepBuilderFactory
            .get("imprimeOlaStep")
            .tasklet(new Tasklet() {
                @Override
                public RepeatStatus execute(StepContribution stepContribution,
                    ChunkContext chunkContext) throws Exception {
                    LOGGER.info("Olá Mundo!");
                    return RepeatStatus.FINISHED;
                }
            })
            .build();
    }
}
