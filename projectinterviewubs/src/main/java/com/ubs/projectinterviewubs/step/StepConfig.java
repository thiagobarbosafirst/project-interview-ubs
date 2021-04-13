package com.ubs.projectinterviewubs.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ubs.projectinterviewubs.domain.ProductItem;

@Configuration
public class StepConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step readerFileJsonStep(ItemReader<ProductItem> readerFileJsonReader, 
			ItemWriter<ProductItem> readerFileJsonWriter) {
		return stepBuilderFactory
				.get("readerFileJsonStep")
				.<ProductItem, ProductItem>chunk(1)
				.reader(readerFileJsonReader)
				.writer(readerFileJsonWriter)
				.build();
	}
	
	
}
