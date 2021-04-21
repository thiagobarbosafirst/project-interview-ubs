package com.ubs.projectinterviewubs.processor;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.validator.BeanValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidatingItemProcessor;
import org.springframework.batch.item.validator.ValidationException;
import org.springframework.batch.item.validator.Validator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.ubs.projectinterviewubs.domain.ProductItem;

@Configuration
public class ValidationProcessorConfig {
	
	Logger logger = LoggerFactory.getLogger(ValidationProcessorConfig.class);
	
	private Set<ProductItem> products = new HashSet<ProductItem>();
	
	@Bean
	public ItemProcessor<ProductItem, ProductItem> validationProcessor() {
		/*
		 * BeanValidatingItemProcessor<ProductItem> processor = new
		 * BeanValidatingItemProcessor<>(); processor.setFilter(true);
		 */
		ValidatingItemProcessor<ProductItem> processor = new ValidatingItemProcessor<>();
		processor.setValidator(validator());
		processor.setFilter(true);
		return processor;
	}

	private Validator<ProductItem> validator() {
		return new Validator<ProductItem>() {

			@Override
			public void validate(ProductItem productItem) throws ValidationException {
				if(products.contains(productItem)) {
					logger.error(String.format("The product %s has already been processed!", productItem.getProduct()));
					throw new ValidationException(String.format("The product %s has already been processed!", productItem.getProduct()));
					
				}
				products.add(productItem);
			}
		};
	}

}
