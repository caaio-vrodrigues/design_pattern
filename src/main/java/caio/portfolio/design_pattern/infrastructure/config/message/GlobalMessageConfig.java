package caio.portfolio.design_pattern.infrastructure.config.message;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class GlobalMessageConfig {

	@Bean
	public MessageSource salableComponentMessageSource() {
		ReloadableResourceBundleMessageSource messageSource = 
			new ReloadableResourceBundleMessageSource();
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setAlwaysUseMessageFormat(true);
		messageSource.setBasenames(
			"classpath:message/salable_component/salable_component_msg",
			"classpath:message/salable_component/conventional/product_msg",
			"classpath:message/salable_component/conventional/service_msg",
			"classpath:message/salable_component/kit/kit_msg"
		);
		return messageSource;
	}
}