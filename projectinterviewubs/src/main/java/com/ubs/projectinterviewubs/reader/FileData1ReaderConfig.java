package com.ubs.projectinterviewubs.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.ubs.projectinterviewubs.domain.ProductItem;

@Configuration
public class FileData_1ReaderConfig {
	
	@StepScope
	@Bean
	public JsonItemReader<ProductItem> fileDataOneJsonItemReader() {
		JsonItemReader<ProductItem> reader = new JsonItemReaderBuilder<ProductItem>()
				.jsonObjectReader(new JacksonJsonObjectReader<>(ProductItem.class))
				.resource(new FileSystemResource("files/data_1.json"))
				.name("fileDataOneJsonItemReader")
				.build();
		return reader;
	}
}
