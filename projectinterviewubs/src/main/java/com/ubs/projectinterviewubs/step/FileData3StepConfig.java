package com.ubs.projectinterviewubs.step;

import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.ubs.projectinterviewubs.domain.ProductItem;

@Configuration
public class FileData3StepConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	@Qualifier("transactionManagerApp")
	private PlatformTransactionManager transactionManagerApp;
	
	@Bean
	public Step readerFileJsonThreeStep(
			@Qualifier("fileDataJsonThreeItemReader") JsonItemReader<ProductItem> fileDataJsonThreeItemReader, 
			ItemProcessor<ProductItem, ProductItem> validationProcessor,
			ClassifierCompositeItemWriter<ProductItem> productClassifierData3Writer,
			FlatFileItemWriter<ProductItem> createFileWithInvalidProductData3Writer) {
		return stepBuilderFactory
				.get("readerFileJsonThreeStep")
				.<ProductItem, ProductItem>chunk(2000)
				.reader(fileDataJsonThreeItemReader)
				.processor(validationProcessor)
				.writer(productClassifierData3Writer)
				.transactionManager(transactionManagerApp)
				.stream(createFileWithInvalidProductData3Writer)
				.build();
	}
	
	
}
