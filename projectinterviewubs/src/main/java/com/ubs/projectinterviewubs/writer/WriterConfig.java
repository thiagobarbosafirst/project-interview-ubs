package com.ubs.projectinterviewubs.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ubs.projectinterviewubs.domain.ProductItem;

@Configuration
public class WriterConfig {
	@Bean
	public ItemWriter<ProductItem> leituraArquivoJson() {
		return items -> items.forEach(System.out::println);
	}

}
