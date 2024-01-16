package org.example.springbatchlecture.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.builder.StepBuilderHelper;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;

@Configuration
public class HelloJobConfiguration {

	@Bean
	public Job helloJob(JobRepository jobRepository, Step helloStep1, Step helloStep2) {
		return new JobBuilder("helloJob", jobRepository)
			.start(helloStep1)
			.next(helloStep2)
			.build();
	}

	@Bean
	public Step helloStep1(JobRepository jobRepository, PlatformTransactionManager tx) {
		return new StepBuilder("helloStep1", jobRepository)
			.tasklet((contribution, chunkContext) -> {
				contribution.getStepExecution().getJobExecution().getJobParameters();
				System.out.println("====================================");
				System.out.println(" helloStep1 executed ");
				System.out.println("====================================");
				return RepeatStatus.FINISHED;
			}, tx).build();
	}

	@Bean
	public Step helloStep2(JobRepository jobRepository, PlatformTransactionManager tx) {
		return new StepBuilder("helloStep2", jobRepository)
			.tasklet((contribution, chunkContext) -> {
				System.out.println("====================================");
				System.out.println(" helloStep2 executed ");
				System.out.println("====================================");
				return RepeatStatus.FINISHED;
			}, tx).build();
	}
	// @Bean
	// public Step helloStep3(JobRepository jobRepository, PlatformTransactionManager tx) {
	// 	// return new StepBuilder( "helloStep3", jobRepository)
	// 	// 	.<String,String>chunk(1000,tx)
	// 	// 	.reader(itemReader())
	// 	// 	.processor(itemProcessor())// option. 필수가 아니다
	// 	// 	.writer(itemWriter())
	// 	// 	.build();
	// }

	// @Bean
	// public Step helloStep3(JobRepository jobRepository, PlatformTransactionManager tx) {
	// 	return new StepBuilder( "helloStep3", jobRepository)
	// 		.<String,String>chunk(1000,tx)
	// 		.reader(new ItemReader<String>() {
	// 			@Override
	// 			public String read() throws
	// 				Exception,
	// 				UnexpectedInputException,
	// 				ParseException,
	// 				NonTransientResourceException {
	// 				return null;
	// 			}
	// 		})
	// 		.processor(new ItemProcessor<String, String>() {
	// 			@Override
	// 			public String process(String item) throws Exception {
	// 				return null;
	// 			}
	// 		})// option. 필수가 아니다
	// 		.writer(new ItemWriter<String>() {
	// 			@Override
	// 			public void write(Chunk<? extends String> chunk) throws Exception {
	//
	// 			}
	// 		})
	// 		.build();
	// }
}