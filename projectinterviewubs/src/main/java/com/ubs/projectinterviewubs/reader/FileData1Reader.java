package com.ubs.projectinterviewubs.reader;

import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.ubs.projectinterviewubs.config.Constants;
import com.ubs.projectinterviewubs.domain.ProductItem;

@Configuration
public class FileData1Reader {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Bean
	public JsonItemReader<ProductItem> fileDataJsonOneItemReader() {
		Resource resource = resourceLoader.getResource(Constants.DIR_FILES_DATA1);
		JsonItemReader<ProductItem> reader = new JsonItemReaderBuilder<ProductItem>()
				.jsonObjectReader(new JacksonJsonObjectReader<>(ProductItem.class))
				.resource(resource)
				.name("fileDataJsonOneItemReader")
				.saveState(false)
				.build();
		return reader;
	}
}
