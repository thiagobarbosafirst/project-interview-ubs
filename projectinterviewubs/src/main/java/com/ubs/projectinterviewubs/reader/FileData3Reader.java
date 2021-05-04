package com.ubs.projectinterviewubs.reader;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.ubs.projectinterviewubs.config.Constants;
import com.ubs.projectinterviewubs.domain.ProductItem;

@Configuration
public class FileData3Reader {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	@Bean
	public JsonItemReader<ProductItem> fileDataJsonThreeItemReader() throws IOException {
		Resource resource = resourceLoader.getResource(Constants.DIR_FILES_DATA3);
		JsonItemReader<ProductItem> reader = new JsonItemReaderBuilder<ProductItem>()
				.jsonObjectReader(new JacksonJsonObjectReader<>(ProductItem.class))
				.resource(resource)
				.name("fileDataJsonThreeItemReader")
				.saveState(false)
				.build();
		return reader;
	}

}
