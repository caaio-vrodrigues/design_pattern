package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.product;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.product.ProductAlreadyExistsException;
import caio.portfolio.design_pattern.domain.model.command.salable_component.product.CreateProductCommand;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.SalableComponentValidator;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductValidator implements SalableComponentValidator<CreateProductCommand> {
	
	private final ProductRepository repo;
	
	@Override
	public void validateSalableComponent(CreateProductCommand command) {
		boolean productAlreadyExists = repo.existsByNameAndBrandAndModel(
			command.getName(), 
			command.getBrand(), 
			command.getModel());
		if(productAlreadyExists)
			throw new ProductAlreadyExistsException("Falha de duplicação ao tentar salvar novo `Product`: [name: `"+command.getName()+"`; brand: `"+command.getBrand()+"`; model: `"+command.getModel()+"`].");
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}