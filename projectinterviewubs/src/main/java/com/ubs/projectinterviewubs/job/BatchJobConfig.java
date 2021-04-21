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
public class BatchJobConfig {
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Bean
	public Job fileJsonParallelJob(
			@Qualifier("readerFileJsonOneStep") Step fileData1StepConfig,
			@Qualifier("readerFileJsonTwoStep") Step fileData2StepConfig,
			@Qualifier("readerFileJsonThreeStep") Step fileData3StepConfig,
			@Qualifier("readerFileJsonFourStep") Step fileData4StepConfig
			) {
		return jobBuilderFactory
				.get("fileJsonParallelJob")
				.start(parallelSteps(fileData1StepConfig, fileData2StepConfig, fileData3StepConfig, fileData4StepConfig))
				.end()
				.build();
	}

	private Flow parallelSteps(Step fileData1StepConfig, Step fileData2StepConfig,
			Step fileData3StepConfig, Step fileData4StepConfig) {
		return new FlowBuilder<Flow>("parallelSteps")
				.start(fileData1Flow(fileData1StepConfig))
				.split(new SimpleAsyncTaskExecutor())
				.add(fileData2Flow(fileData2StepConfig),
						fileData3Flow(fileData3StepConfig), 
							fileData4Flow(fileData4StepConfig))
				.build();
	}
	
	private Flow fileData1Flow(Step fileData1StepConfig) {
		return new FlowBuilder<Flow>("fileData1Flow")
				.start(fileData1StepConfig)
				.build();
	}
	
	private Flow fileData2Flow(Step fileData2StepConfig) {
		return new FlowBuilder<Flow>("fileData2Flow")
				.start(fileData2StepConfig)
				.build();
	}

	private Flow fileData3Flow(Step fileData3StepConfig) {
		return new FlowBuilder<Flow>("fileData3Flow")
				.start(fileData3StepConfig)
				.build();
	}

	private Flow fileData4Flow(Step fileData4StepConfig) {
		return new FlowBuilder<Flow>("fileData4Flow")
				.start(fileData4StepConfig)
				.build();
	}
}
