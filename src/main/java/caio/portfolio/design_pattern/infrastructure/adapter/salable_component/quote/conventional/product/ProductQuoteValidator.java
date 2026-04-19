package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.quote.conventional.product;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.product.InsufficientProductUnitsException;
import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.quote.SalableComponentQuoteValidator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Product;

@Component
public class ProductQuoteValidator implements SalableComponentQuoteValidator<Product> {

	@Override
	public void validateRequiredItemQuote(Product item, Integer requiredQuantity) {
		if(item.getUnits() - requiredQuantity < 0)
			throw new InsufficientProductUnitsException("Unidades insuficientes para realizar orçamento do Product: [id: `"+item.getId()+"`].");
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}