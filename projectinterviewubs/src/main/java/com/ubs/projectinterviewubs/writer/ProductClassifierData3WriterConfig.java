package com.ubs.projectinterviewubs.writer;

import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.support.ClassifierCompositeItemWriter;
import org.springframework.batch.item.support.builder.ClassifierCompositeItemWriterBuilder;
import org.springframework.classify.Classifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ubs.projectinterviewubs.domain.ProductItem;

@Configuration
public class ProductClassifierData3WriterConfig {
	@Bean
	public ClassifierCompositeItemWriter<ProductItem> productClassifierData3Writer(
			JdbcBatchItemWriter<ProductItem> dataBaseItemProductWriter,
			FlatFileItemWriter<ProductItem> createFileWithInvalidProductData3Writer) {
		return new ClassifierCompositeItemWriterBuilder<ProductItem>()
				.classifier(classifier(dataBaseItemProductWriter, createFileWithInvalidProductData3Writer))
				.build();
	}

	@SuppressWarnings("serial")
	private Classifier<ProductItem, ItemWriter<? super ProductItem>> classifier(
			JdbcBatchItemWriter<ProductItem> dataBaseItemProductWriter,
			FlatFileItemWriter<ProductItem> createFileWithInvalidProductWriter) {
		return new Classifier<ProductItem, ItemWriter<? super ProductItem>>() {

			@Override
			public ItemWriter<? super ProductItem> classify(ProductItem productItem) {
				if(productItem.isValid())
					return dataBaseItemProductWriter;
				else
					return createFileWithInvalidProductWriter;
			}
			
		};
	}
}
