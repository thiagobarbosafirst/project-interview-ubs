package com.ubs.projectinterviewubs.writer.classifier;

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
public class ProductClassifierData2Writer {
	@Bean
	public ClassifierCompositeItemWriter<ProductItem> executeProductClassifierData2Writer(
			JdbcBatchItemWriter<ProductItem> executeDataBaseItemProductWriter,
			FlatFileItemWriter<ProductItem> executeCreateFileWithInvalidProductData2Writer) {
		return new ClassifierCompositeItemWriterBuilder<ProductItem>()
				.classifier(classifier(executeDataBaseItemProductWriter, executeCreateFileWithInvalidProductData2Writer))
				.build();
	}

	@SuppressWarnings("serial")
	private Classifier<ProductItem, ItemWriter<? super ProductItem>> classifier(
			JdbcBatchItemWriter<ProductItem> dataBaseItemProductWriter,
			FlatFileItemWriter<ProductItem> executeCreateFileWithInvalidProductData2Writer) {
		return new Classifier<ProductItem, ItemWriter<? super ProductItem>>() {

			@Override
			public ItemWriter<? super ProductItem> classify(ProductItem productItem) {
				if(productItem.isValid())
					return dataBaseItemProductWriter;
				else
					return executeCreateFileWithInvalidProductData2Writer;
			}
			
		};
	}
}
