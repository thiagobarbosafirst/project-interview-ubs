package com.ubs.projectinterviewubs.processor;

import java.util.HashSet;
import java.util.Set;

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
	
	//{"product":"EVF","quantity":69,"price":"$3.77","type":"3XL","industry":"n/a","origin":"TX"},
	
	private Set<ProductItem> products = new HashSet<ProductItem>();
	/*
	 * private Set<String> type = new HashSet<>(); private Set<String> industry =
	 * new HashSet<>(); private Set<String> origin = new HashSet<>();
	 */
	
	@Bean
	public ItemProcessor<ProductItem, ProductItem> validationProcessor() {
		/*
		 * BeanValidatingItemProcessor<ProductItem> processor = new
		 * BeanValidatingItemProcessor<>(); processor.setFilter(true);
		 */
		ValidatingItemProcessor<ProductItem> processor = new ValidatingItemProcessor<>();
		processor.setValidator(validator());
		return processor;
	}

	private Validator<ProductItem> validator() {
		return new Validator<ProductItem>() {

			@Override
			public void validate(ProductItem productItem) throws ValidationException {
				if(products.contains(productItem))
					throw new ValidationException(String.format("The product %s has already been processed!", productItem.getProduct()));
				products.add(productItem);
			}
		};
	}

}
