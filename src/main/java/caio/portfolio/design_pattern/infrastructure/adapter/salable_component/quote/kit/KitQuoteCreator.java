package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.quote.kit;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.quote.SalableComponentQuoteCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;

@Component
public class KitQuoteCreator implements SalableComponentQuoteCreator<Kit> {

	@Override
	public BigDecimal calculateQuote(Kit item, Integer requiredQuantity) {
		return item.getPrice()
			.multiply(BigDecimal.valueOf(requiredQuantity));
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.KIT;
	}
}