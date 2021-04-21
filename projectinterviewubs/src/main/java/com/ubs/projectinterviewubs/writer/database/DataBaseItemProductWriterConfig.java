package com.ubs.projectinterviewubs.writer;

import javax.sql.DataSource;

import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ubs.projectinterviewubs.domain.ProductItem;

@Configuration
public class DataBaseItemProductWriterConfig {
	
	@Bean
	public JdbcBatchItemWriter<ProductItem> dataBaseItemProductWriter(
			@Qualifier("appDataSource") DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<ProductItem>()
				.dataSource(dataSource)
				.sql("INSERT INTO product_item (product, quantity, price, type, industry, origin) VALUES (:product, :quantity, :price, :type, :industry, :origin)")
				.beanMapped()
				.build();
	}
}
