package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.conventional.product;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.command.salable_component.conventional.product.CreateProductCommand;
import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.product.ProductAlreadyExistsException;
import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.ConventionalComponentValidator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.message.product.ProductMessageCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.conventional.ProductRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductValidator implements ConventionalComponentValidator<CreateProductCommand> {
	
	private final ProductRepository repo;
	private final ProductMessageCreator productMessageCreator;
	
	@Override
	public void validateSalableComponent(CreateProductCommand command) {
		boolean productAlreadyExists = repo.existsByNameAndBrandAndModel(
			command.getName(), 
			command.getBrand(), 
			command.getModel());
		if(productAlreadyExists) {
			String exceptionMsg = productMessageCreator.getProductAlreadyExistsMsg(
				command.getName(), 
				command.getBrand(), 
				command.getModel());
			throw new ProductAlreadyExistsException(exceptionMsg);
		}			
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}