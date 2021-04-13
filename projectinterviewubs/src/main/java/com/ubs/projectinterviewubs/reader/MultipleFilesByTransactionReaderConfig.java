package com.ubs.projectinterviewubs.reader;

import javax.annotation.Resource;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.builder.MultiResourceItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MultipleFilesByTransactionReaderConfig {
	/*
	 * @StepScope
	 * 
	 * @Bean public MultiResourceItemReader multipleFilesByTransactionReader(
	 * 
	 * @Value("#{jobParameters['fileItemProduct']}") Resource[] fileItemProduct) {
	 * return new MultiResourceItemReaderBuilder<>() .name("") }
	 */
}
