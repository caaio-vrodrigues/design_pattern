package caio.portfolio.design_pattern.domain.model.interfaces;

import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;

public interface SalableComponentQuoteValidator<T extends SalableComponent> {
	
	void validateRequiredItemQuote(T item, Integer requiredQuantity);
	SalableComponentType getType();
}