package com.ubs.projectinterviewubs.reader;

import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.ubs.projectinterviewubs.domain.ProductItem;

@Configuration
public class FileData2ReaderConfig {

	@Bean
	public JsonItemReader<ProductItem> fileDataJsonTwoItemReader() {
		JsonItemReader<ProductItem> reader = new JsonItemReaderBuilder<ProductItem>()
				.jsonObjectReader(new JacksonJsonObjectReader<>(ProductItem.class))
				.resource(new FileSystemResource("files/data_2.json"))
				.name("fileDataJsonTwoItemReader")
				.saveState(false)
				.build();
		return reader;
	}
}
