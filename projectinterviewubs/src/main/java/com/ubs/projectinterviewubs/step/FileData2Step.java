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
public class FileData2Step {
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	@Qualifier("transactionManagerApp")
	private PlatformTransactionManager transactionManagerApp;
	
	@Bean
	public Step readerFileJsonTwoStep(
			@Qualifier("fileDataJsonTwoItemReader") JsonItemReader<ProductItem> fileDataJsonTwoItemReader, 
			ItemProcessor<ProductItem, ProductItem> validationProcessor,
			ClassifierCompositeItemWriter<ProductItem> executeProductClassifierData2Writer,
			FlatFileItemWriter<ProductItem> executeCreateFileWithInvalidProductData2Writer) {
		return stepBuilderFactory
				.get("readerFileJsonTwoStep")
				.<ProductItem, ProductItem>chunk(2000)
				.reader(fileDataJsonTwoItemReader)
				.processor(validationProcessor)
				.writer(executeProductClassifierData2Writer)
				.transactionManager(transactionManagerApp)
				.stream(executeCreateFileWithInvalidProductData2Writer)
				.build();
	}
	
	
}
