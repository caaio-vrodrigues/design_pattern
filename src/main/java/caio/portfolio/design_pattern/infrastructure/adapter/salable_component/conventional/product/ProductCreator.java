package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.conventional.product;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.application.dto.salable_component.conventional.product.ResponseProductDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.conventional.product.CreateProductCommand;
import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.product.ConcurrentProductException;
import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.ConventionalComponentCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.message.product.ProductMessageCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Product;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.conventional.ProductRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductCreator implements ConventionalComponentCreator<
	CreateProductCommand, 
	ResponseProductDTO
> {	
	private final ProductRepository repo;
	private final ProductMessageCreator productMessageCreator;
	
	private Product saveProduct(Product product) {
		try {
			return repo.save(product);
		}
		catch(DataIntegrityViolationException e) {
			String entityName = product.getClass().getName();
			String exceptionMsg = productMessageCreator
				.getConcurrentEntityMsg(entityName);
			throw new ConcurrentProductException(exceptionMsg);
		}
	}
	
	private ResponseProductDTO toRespDTO(Product product) {
		return ResponseProductDTO.builder()
			.id(product.getId())
			.name(product.getName())
			.brand(product.getBrand())
			.model(product.getModel())
			.price(product.getPrice())
			.units(product.getUnits())
			.build();
	}
	
	@Override
	public ResponseProductDTO createSalableComponent(
		CreateProductCommand command
	) {
		Product newProduct = Product.builder()
			.name(command.getName())
			.brand(command.getBrand())
			.model(command.getModel())
			.price(command.getPrice())
			.units(command.getUnits())
			.build();
		saveProduct(newProduct);
		return toRespDTO(newProduct);
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}