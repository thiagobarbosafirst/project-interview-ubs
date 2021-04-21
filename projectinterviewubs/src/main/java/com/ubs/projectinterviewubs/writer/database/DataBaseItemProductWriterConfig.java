package com.ubs.projectinterviewubs.writer.database;

import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.batch.item.database.ItemPreparedStatementSetter;
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
				.sql("INSERT INTO product_item (product, quantity, price, type, industry, origin) VALUES (?, ?, ?, ?, ?, ?)")
				.itemPreparedStatementSetter(itemPrepararedStatementSetter())
				.build();
	}

	private ItemPreparedStatementSetter<ProductItem> itemPrepararedStatementSetter() {
		
		return new ItemPreparedStatementSetter<ProductItem>() {

			@Override
			public void setValues(ProductItem prod, PreparedStatement ps) throws SQLException {
				ps.setString(1, prod.getProduct());
				ps.setInt(2, prod.getQuantity());
				ps.setBigDecimal(3, new BigDecimal(prod.getPrice().substring(1)));
				ps.setString(4, prod.getType());
				ps.setString(5, prod.getIndustry());
				ps.setString(6, prod.getOrigin());
				
				
			}
			
		};
	}
}
