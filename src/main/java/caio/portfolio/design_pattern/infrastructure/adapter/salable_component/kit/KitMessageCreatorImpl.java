package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.kit;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.kit.message.KitMessageCreator;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KitMessageCreatorImpl implements KitMessageCreator {
	
	private final MessageSource salableComponentMessageSource ;

	@Override
	public String getConcurrentEntityMsg(String entityName) {
		return salableComponentMessageSource.getMessage(
			"concurrent_save_process.entityName", 
			new Object[] {entityName}, 
			LocaleContextHolder.getLocale());
	}

	@Override
	public String getNotFoundEntityById(String entityName, Long id) {
		return salableComponentMessageSource.getMessage(
			"not_found.entityName.id", 
			new Object[] {entityName, id}, 
			LocaleContextHolder.getLocale());
	}

	@Override
	public String getKitAlreadyExistsMsg(String code) {
		return salableComponentMessageSource.getMessage(
			"kit_alreadyExists.code", 
			new Object[] {code}, 
			LocaleContextHolder.getLocale());
	}
}