package caio.portfolio.design_pattern.infrastructure.config;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.CreateSalableComponentValidator;

@Configuration
public class GlobalConfig {

	@Bean
	public Map<SalableComponentType, CreateSalableComponentValidator<?>> createSalableComponentValidators(
		List<CreateSalableComponentValidator<?>> validators	
	) {
		return validators.stream().collect(Collectors.toMap(
			CreateSalableComponentValidator::getType, 
			Function.identity()));
	}
}