package com.ubs.projectinterviewubs.writer;

import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.ubs.projectinterviewubs.domain.ProductItem;

@Configuration
public class InvalidRowsInFileWriterConfig {
	
	String[] names = new String [] {"product", "quantity", "price"};
	
	@Bean
	public FlatFileItemWriter<ProductItem> createFileWithInvalidProductWriter() {
	   return new FlatFileItemWriterBuilder<ProductItem>()
			   .name("createFileWithInvalidProduct")
			   .resource(new FileSystemResource("files/fileInvalidsProducts.csv"))
			   .delimited()
			   .names(names)
			   .build();
	}
}
