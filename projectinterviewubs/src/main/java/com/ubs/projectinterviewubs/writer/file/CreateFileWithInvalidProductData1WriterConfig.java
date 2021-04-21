package com.ubs.projectinterviewubs.writer.file;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.ubs.projectinterviewubs.domain.ProductItem;

@Configuration
public class CreateFileWithInvalidProductData1WriterConfig {
	
	String[] names = new String [] { "quantity", "price", "type", "industry", "origin"};
	
	@Bean
	public FlatFileItemWriter<ProductItem> createFileWithInvalidProductData1Writer() {
	   return new FlatFileItemWriterBuilder<ProductItem>()
			   .name("createFileWithInvalidProductData1")
			   .resource(new FileSystemResource("files/fileInvalidsProductsData1.txt"))
			   .delimited()
			   .names(names)
			   .build();
	}
}
