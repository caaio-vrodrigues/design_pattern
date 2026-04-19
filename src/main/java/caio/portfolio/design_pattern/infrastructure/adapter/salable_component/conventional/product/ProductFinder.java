package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.conventional.product;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.product.ProductNotFoundException;
import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentFinder;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.message.product.ProductMessageCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Product;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.conventional.ProductRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductFinder implements SalableComponentFinder<Product> {
	
	private final ProductRepository repo;
	private final ProductMessageCreator productMessageCreator;

	@Override
	public Product findSalableComponent(Long id) {
		String entityName = Product.class.getName();
		String exceptionMsg = productMessageCreator
			.getEntityNotFoundByIdMsg(entityName, id);
		return repo.findById(id).orElseThrow(() -> 
			new ProductNotFoundException(exceptionMsg));
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}