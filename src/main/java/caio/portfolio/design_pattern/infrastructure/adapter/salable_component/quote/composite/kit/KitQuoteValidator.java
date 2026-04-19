package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.quote.composite.kit;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.composite.kit.InsufficientKitUnitsException;
import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.quote.SalableComponentQuoteValidator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.composite.kit.Kit;

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