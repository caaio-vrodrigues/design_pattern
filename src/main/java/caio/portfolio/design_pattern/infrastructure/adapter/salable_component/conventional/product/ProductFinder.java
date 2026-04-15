package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.conventional.product;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.product.ProductNotFoundException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentFinder;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Product;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.conventional.ProductRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductFinder implements SalableComponentFinder<Product> {
	
	private final ProductRepository repo;

	@Override
	public Product findSalableComponent(Long id) {
		return repo.findById(id).orElseThrow(() -> 
			new ProductNotFoundException("Não foi possível encontra `Product` para o id: `"+id+"`."));
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}