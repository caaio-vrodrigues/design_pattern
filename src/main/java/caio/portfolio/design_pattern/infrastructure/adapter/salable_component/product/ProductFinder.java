package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.product;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.product.ProductNotFoundException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.SalableComponentFinder;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.Product;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.ProductRepository;
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