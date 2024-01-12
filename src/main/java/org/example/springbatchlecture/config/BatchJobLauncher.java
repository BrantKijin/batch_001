package org.example.springbatchlecture.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

// @RequiredArgsConstructor
// @Component
public class BatchJobLauncher implements CommandLineRunner {

	private JobLauncher jobLauncher;

	private Job job;

	@Override
	public void run(String... args) throws Exception {
		JobExecution execution = jobLauncher.run(job, new JobParameters());
		System.out.println("Exit status :: " + execution.getStatus());
	}
}
