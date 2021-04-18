package com.ubs.projectinterviewubs.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ubs.projectinterviewubs.domain.ProductItem;

@Configuration
public class StepConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Bean
	public Step readerFileJsonStep(
			@Qualifier("fileDataOneJsonItemReader") JsonItemReader<ProductItem> fileDataOneJsonItemReader, 
			ItemWriter<ProductItem> dataBaseItemProductWriter) {
		return stepBuilderFactory
				.get("readerFileJsonStep")
				.<ProductItem, ProductItem>chunk(1000)
				.reader(fileDataOneJsonItemReader)
				.writer(dataBaseItemProductWriter)
				.build();
	}
	
	
}
