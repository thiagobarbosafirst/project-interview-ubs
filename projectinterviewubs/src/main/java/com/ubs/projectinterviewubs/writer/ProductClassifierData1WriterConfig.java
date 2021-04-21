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
public class ProductClassifierData1WriterConfig {
	@Bean
	public ClassifierCompositeItemWriter<ProductItem> productClassifierData1Writer(
			JdbcBatchItemWriter<ProductItem> dataBaseItemProductWriter,
			FlatFileItemWriter<ProductItem> createFileWithInvalidProductData1Writer) {
		return new ClassifierCompositeItemWriterBuilder<ProductItem>()
				.classifier(classifier(dataBaseItemProductWriter, createFileWithInvalidProductData1Writer))
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
