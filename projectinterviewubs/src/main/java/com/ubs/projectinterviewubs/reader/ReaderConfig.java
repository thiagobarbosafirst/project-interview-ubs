package com.ubs.projectinterviewubs.reader;

import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ubs.projectinterviewubs.domain.Item;

@Configuration
public class ReaderConfig {

	@StepScope
	@Bean
	public JsonItemReader<Item> jsonItemReader() {
		return new JsonItemReaderBuilder<Item>()
				.jsonObjectReader(new JacksonJsonObjectReader<>(Item.class))
				.resource(new ClassPathResource("Files/data_1.sjon"));
	}
}
