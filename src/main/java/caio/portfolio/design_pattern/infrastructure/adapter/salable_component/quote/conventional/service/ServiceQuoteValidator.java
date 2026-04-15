package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.quote.conventional.service;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.service.UnavailableServiceException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.quote.SalableComponentQuoteValidator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Service;

@Component
public class ServiceQuoteValidator implements SalableComponentQuoteValidator<Service> {

	@Override
	public void validateRequiredItemQuote(Service item, Integer requiredQuantity) {
		if(!item.getIsAvailable())
			throw new UnavailableServiceException("Serviço indisponível para orçamento. Service: [id: `"+item.getId()+"`].");
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.SERVICE;
	}
}