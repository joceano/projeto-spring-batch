package com.joceano.projetospringbatch.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.function.FunctionItemProcessor;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ImprimeParImparStepConfig {

    private final StepBuilderFactory stepBuilderFactory;

    public ImprimeParImparStepConfig(StepBuilderFactory stepBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
    }

    @Bean
    public Step imprimeParImparStep(
            IteratorItemReader<Integer> contaAteDezReader,
            FunctionItemProcessor<Integer, String> parOuImparProcessor,
            ItemWriter<String> imprimeWriter) {
        return stepBuilderFactory
                .get("imprimeParImparStep")
                .<Integer, String>chunk(10)
                .reader(contaAteDezReader)
                .processor(parOuImparProcessor)
                .writer(imprimeWriter)
                .build();
    }
}
