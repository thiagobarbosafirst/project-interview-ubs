package com.ubs.projectinterviewubs.writer.file;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import com.ubs.projectinterviewubs.config.Constants;
import com.ubs.projectinterviewubs.domain.ProductItem;

@Configuration
public class CreateFileWithInvalidProductData2Writer {
	
	@Autowired
	ResourceLoader resourceLoader;
	
	String[] names = new String [] { "product", "quantity", "price", "type", "industry", "origin"};
	
	@Bean
	public FlatFileItemWriter<ProductItem> executeCreateFileWithInvalidProductData2Writer() {
	   return new FlatFileItemWriterBuilder<ProductItem>()
			   .name("createFileWithInvalidProductData2")
			   .resource(new FileSystemResource(Constants.DIR_REPROCESS_INVALIDS_DATA2))
			   .delimited()
			   .names(names)
			   .build();
	}
}
