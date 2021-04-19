package com.ubs.projectinterviewubs.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
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
			ClassifierCompositeItemWriter<ProductItem> productClassifierWriter,
			FlatFileItemWriter<ProductItem> createFileWithInvalidProductWriter) {
		return stepBuilderFactory
				.get("readerFileJsonStep")
				.<ProductItem, ProductItem>chunk(10000)
				.reader(fileDataOneJsonItemReader)
				.writer(productClassifierWriter)
				.stream(createFileWithInvalidProductWriter)
				.build();
	}
	
	
}
