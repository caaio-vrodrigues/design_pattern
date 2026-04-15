package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.quote.kit;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.kit.InsufficientKitUnitsException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.quote.SalableComponentQuoteValidator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;

@Component
public class KitQuoteValidator implements SalableComponentQuoteValidator<Kit> {

	@Override
	public void validateRequiredItemQuote(Kit item, Integer requiredQuantity) {
		if(item.getUnits() - requiredQuantity < 0)
			throw new InsufficientKitUnitsException("Unidades insuficientes para realizar orçamento do Kit: [id: `"+item.getId()+"`].");
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.KIT;
	}
}