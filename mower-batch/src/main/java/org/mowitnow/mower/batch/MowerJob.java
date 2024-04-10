package org.mowitnow.mower.batch;

import org.apache.commons.io.FileUtils;
import org.mowitnow.mower.Lawn;
import org.mowitnow.mower.LawnService;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Configuration
public class MowerJob {
    private LawnService lawnService;

    public MowerJob(LawnService lawnService) {
        this.lawnService = lawnService;
    }

    @Bean
    public Job mower(JobRepository jobRepository, Step explorationStep) {
        return new JobBuilder("mowerJob", jobRepository)
                .start(explorationStep)
                .build();
    }

    @Bean
    public Step explorationStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                                ItemReader<Lawn> reader,
                                MowerProcessor processor,
                                ItemWriter<Lawn> writer) {
        return new StepBuilder("explorationStep", jobRepository)
                .<Lawn, Lawn>chunk(3, transactionManager)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();
    }

    @Bean
    public ItemReader<Lawn> reader(@Value("${mowerJob.input}") String inputFilePath) throws IOException {
        return new ItemReader<>() {
            Lawn lawn = lawnService.loadInput(new File(inputFilePath));
            boolean alreadyRead = false;

            @Override
            public Lawn read() throws Exception {
                if (!alreadyRead) {
                    alreadyRead = true;
                    return lawn;
                }
                return null;
            }
        };
    }

    @Bean
    public MowerProcessor processor() {
        return new MowerProcessor(lawnService);
    }

    @Bean
    public ItemWriter<Lawn> writer(@Value("${mowerJob.output}") String outputFilePath) {
        return new ItemWriter<>() {
            @Override
            public void write(Chunk<? extends Lawn> items) throws Exception {
                FileUtils.writeStringToFile(new File(outputFilePath),
                        String.valueOf(items.getItems().get(0)),
                        StandardCharsets.UTF_8);
            }
        };
    }


}
