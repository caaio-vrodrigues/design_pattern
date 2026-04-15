package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.quote.conventional.product;

import java.math.BigDecimal;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.quote.SalableComponentQuoteCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Product;

@Component
public class ProductQuoteCreator implements SalableComponentQuoteCreator<Product> {

	@Override
	public BigDecimal calculateQuote(Product item, Integer requiredQuantity) {
		return item.getPrice()
			.multiply(BigDecimal.valueOf(requiredQuantity));
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}