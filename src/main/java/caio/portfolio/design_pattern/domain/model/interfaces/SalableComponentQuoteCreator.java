package caio.portfolio.design_pattern.domain.model.interfaces;

import java.math.BigDecimal;

import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;

public interface SalableComponentQuoteCreator<T extends SalableComponent> {

	BigDecimal calculateQuote(T item, Integer requiredQuantity);
	SalableComponentType getType();
}