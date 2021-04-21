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
public class FileData2StepConfig {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	@Qualifier("transactionManagerApp")
	private PlatformTransactionManager transactionManagerApp;
	
	@Bean
	public Step readerFileJsonTwoStep(
			@Qualifier("fileDataJsonTwoItemReader") JsonItemReader<ProductItem> fileDataJsonTwoItemReader, 
			ItemProcessor<ProductItem, ProductItem> validationProcessor,
			ClassifierCompositeItemWriter<ProductItem> productClassifierData2Writer,
			FlatFileItemWriter<ProductItem> createFileWithInvalidProductData2Writer) {
		return stepBuilderFactory
				.get("readerFileJsonTwoStep")
				.<ProductItem, ProductItem>chunk(2000)
				.reader(fileDataJsonTwoItemReader)
				.processor(validationProcessor)
				.writer(productClassifierData2Writer)
				.transactionManager(transactionManagerApp)
				.stream(createFileWithInvalidProductData2Writer)
				.build();
	}
	
	
}
