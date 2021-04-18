package com.ubs.projectinterviewubs.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.file.ResourceAwareItemReaderItemStream;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.ubs.projectinterviewubs.domain.ProductItem;

@Configuration
public class ReaderConfig {

	@StepScope
	@Bean
	public JsonItemReader<ProductItem> jsonItemReader(
		@Value("#{jobParameters['fileItemProduct']}") Resource fileItemProduct) {
		return new JsonItemReaderBuilder<ProductItem>()
				.jsonObjectReader(new JacksonJsonObjectReader<>(ProductItem.class))
				.resource(fileItemProduct)
				.name("jsonItemReader")
				.build();
	}
}
