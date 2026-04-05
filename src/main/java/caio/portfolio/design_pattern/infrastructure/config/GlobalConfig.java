package caio.portfolio.design_pattern.infrastructure.config;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.ConventionalComponentCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.ConventionalComponentValidator;
import caio.portfolio.design_pattern.domain.model.interfaces.SalableComponentCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.SalableComponentValidator;

@Configuration
public class GlobalConfig {

	@Bean
	public Map<SalableComponentType, ConventionalComponentValidator<?>> salableComponentValidators(
		List<ConventionalComponentValidator<?>> validators	
	) {
		return validators.stream().collect(Collectors.toMap(
			SalableComponentValidator::getType, 
			Function.identity()));
	}
	
	@Bean
	public Map<SalableComponentType, ConventionalComponentCreator<?>> salableComponentCreators(
		List<ConventionalComponentCreator<?>> creators
	) {
		return creators.stream().collect(Collectors.toMap(
			SalableComponentCreator::getType, 
			Function.identity()));
	}
}