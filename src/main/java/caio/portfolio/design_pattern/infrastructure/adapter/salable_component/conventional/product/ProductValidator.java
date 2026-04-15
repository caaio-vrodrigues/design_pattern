package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.conventional.product;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.command.salable_component.conventional.product.CreateProductCommand;
import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.product.ProductAlreadyExistsException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.ConventionalComponentValidator;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.conventional.ProductRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductValidator implements ConventionalComponentValidator<CreateProductCommand> {
	
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