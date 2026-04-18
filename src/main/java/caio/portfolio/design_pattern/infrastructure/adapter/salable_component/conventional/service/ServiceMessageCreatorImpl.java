package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.conventional.service;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.model.enums.ServiceCategory;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.message.ServiceMessageCreator;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServiceMessageCreatorImpl implements ServiceMessageCreator {
	
	private final MessageSource salableComponentMessageSource;

	@Override
	public String getConcurrentEntityMsg(String entityName) {
		return salableComponentMessageSource.getMessage(
			"concurrent_save_process.entityName", 
			new Object[] {entityName}, 
			LocaleContextHolder.getLocale()
		);
	}

	@Override
	public String getNotFoundEntityById(String entityName, Long id) {
		return salableComponentMessageSource.getMessage(
			"not_found.entityName.id", 
			new Object[] {entityName, id}, 
			LocaleContextHolder.getLocale()
		);
	}

	@Override
	public String getServiceAlreadyExistsMsg(String name, ServiceCategory category) {
		return salableComponentMessageSource.getMessage(
			"service_alreadyExists.name.category", 
			new Object[] {name, category}, 
			LocaleContextHolder.getLocale()
		);
	}
}