package caio.portfolio.design_pattern.infrastructure.config;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentFinder;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentValidator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.ConventionalComponentCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.ConventionalComponentValidator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.kit.linked_item.LinkedKitItemCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.kit.linked_item.LinkedKitItemValidator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.quote.SalableComponentQuoteCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.quote.SalableComponentQuoteValidator;

@Configuration
public class SalableComponentConfig {

	@Bean
	public Map<SalableComponentType, ConventionalComponentValidator<?>> salableComponentValidators(
		List<ConventionalComponentValidator<?>> validators	
	) {
		return validators.stream().collect(Collectors.toMap(
			SalableComponentValidator::getType, 
			Function.identity()));
	}
	
	@Bean
	public Map<SalableComponentType, ConventionalComponentCreator<?, ?>> salableComponentCreators(
		List<ConventionalComponentCreator<?, ?>> creators
	) {
		return creators.stream().collect(Collectors.toMap(
			SalableComponentCreator::getType, 
			Function.identity()));
	}
	
	@Bean
	public Map<SalableComponentType, SalableComponentFinder<?>> salableComponentFinders(
		List<SalableComponentFinder<?>> finders	
	) {
		return finders.stream().collect(Collectors.toMap(
			SalableComponentFinder::getType, 
			Function.identity()));
	}
	
	@Bean
	public Map<SalableComponentType, LinkedKitItemValidator<?>> linkedKitItemValidators(
		List<LinkedKitItemValidator<?>> validators
	) {
		return validators.stream().collect(Collectors.toMap(
			LinkedKitItemValidator::getType, 
			Function.identity()));
	}
	
	@Bean
	public Map<SalableComponentType, LinkedKitItemCreator<?, ?>> linkedKitItemCreators(
		List<LinkedKitItemCreator<?, ?>> creators	
	) {
		return creators.stream().collect(Collectors.toMap(
			LinkedKitItemCreator::getType, 
			Function.identity()));
	}
	
	@Bean
	public Map<SalableComponentType, SalableComponentQuoteValidator<?>> salableComponentQuoteValidators(
		List<SalableComponentQuoteValidator<?>> validators
	) {
		return validators.stream().collect(Collectors.toMap(
			SalableComponentQuoteValidator::getType, 
			Function.identity()));
	}
	
	@Bean
	public Map<SalableComponentType, SalableComponentQuoteCreator<?>> salableComponentQuoteCreators(
		List<SalableComponentQuoteCreator<?>> creators
	) {
		return creators.stream().collect(Collectors.toMap(
			SalableComponentQuoteCreator::getType, 
			Function.identity()));
	}
}