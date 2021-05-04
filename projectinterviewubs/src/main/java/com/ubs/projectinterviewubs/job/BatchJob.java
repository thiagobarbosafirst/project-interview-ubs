package com.ubs.projectinterviewubs.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.job.builder.FlowBuilder;
import org.springframework.batch.core.job.flow.Flow;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.SimpleAsyncTaskExecutor;

@Configuration
@EnableBatchProcessing
public class BatchJob {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job fileJsonParallelJob(
			@Qualifier("readerFileJsonOneStep") Step fileData1Step,
			@Qualifier("readerFileJsonTwoStep") Step fileData2Step,
			@Qualifier("readerFileJsonThreeStep") Step fileData3Step,
			@Qualifier("readerFileJsonFourStep") Step fileData4Step
			) {
		return jobBuilderFactory
				.get("fileJsonParallelJob")
				.start(parallelSteps(fileData1Step, fileData2Step, fileData3Step, fileData4Step))
				.end()
				.build();
	}

	private Flow parallelSteps(Step fileData1Step, Step fileData2Step,
			Step fileData3Step, Step fileData4Step) {
		return new FlowBuilder<Flow>("parallelSteps")
				.start(fileData1Flow(fileData1Step))
				.split(new SimpleAsyncTaskExecutor())
				.add(fileData2Flow(fileData2Step),
						fileData3Flow(fileData3Step), 
							fileData4Flow(fileData4Step))
				.build();
	}
	
	private Flow fileData1Flow(Step fileData1Step) {
		return new FlowBuilder<Flow>("fileData1Flow")
				.start(fileData1Step)
				.build();
	}
	
	private Flow fileData2Flow(Step fileData2Step) {
		return new FlowBuilder<Flow>("fileData2Flow")
				.start(fileData2Step)
				.build();
	}

	private Flow fileData3Flow(Step fileData3Step) {
		return new FlowBuilder<Flow>("fileData3Flow")
				.start(fileData3Step)
				.build();
	}

	private Flow fileData4Flow(Step fileData4Step) {
		return new FlowBuilder<Flow>("fileData4Flow")
				.start(fileData4Step)
				.build();
	}
}
