package caio.portfolio.design_pattern.domain.model.interfaces.salable_component.quote;

import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;

public interface SalableComponentQuoteValidator<T extends SalableComponent> {
	
	void validateRequiredItemQuote(T item, Integer requiredQuantity);
	SalableComponentType getType();
}