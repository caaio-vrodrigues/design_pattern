package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.quote.conventional.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.quote.SalableComponentQuoteCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Service;

@Component
public class ServiceQuoteCreator implements SalableComponentQuoteCreator<Service> {

	@Override
	public BigDecimal calculateQuote(Service item, Integer requiredQuantity) {
		return item.getPrice()
			.multiply(BigDecimal.valueOf(requiredQuantity));
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.SERVICE;
	}
}