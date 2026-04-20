package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.conventional.product;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.product.message.ProductMessageCreator;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductMessageCreatorImpl implements ProductMessageCreator {
	
	private final MessageSource salableComponentMessageSource;
	
	@Override
	public String getConcurrentEntityMsg(String entityName) {
		return salableComponentMessageSource.getMessage(
			"concurrent_save_process.entityName",
			new Object[] {entityName}, 
			LocaleContextHolder.getLocale());
	}

	@Override
	public String getProductAlreadyExistsMsg(String name, String brand, String model) {
		return salableComponentMessageSource.getMessage(
			"product_alreadyExists.name.brand.model",
			new Object[] {name, brand, model}, 
			LocaleContextHolder.getLocale());
	}

	@Override
	public String getEntityNotFoundByIdMsg(String entityName, Long id) {
		return salableComponentMessageSource.getMessage(
			"not_found.entityName.id",
			new Object[] {entityName, id}, 
			LocaleContextHolder.getLocale());
	}
}