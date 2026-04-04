package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.product;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.application.dto.ResponseProductDTO;
import caio.portfolio.design_pattern.application.dto.ResponseSalableComponentDTO;
import caio.portfolio.design_pattern.domain.exception.salable_component.product.ConcurrentProductException;
import caio.portfolio.design_pattern.domain.model.command.salable_component.product.CreateProductCommand;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.SalableComponentCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.Product;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.ProductRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ProductCreator implements SalableComponentCreator<CreateProductCommand> {
	
	private final ProductRepository repo;
	
	private Product saveProduct(Product product) {
		try {
			return repo.save(product);
		}
		catch(DataIntegrityViolationException e) {
			throw new ConcurrentProductException("Não foi possível completar a operação. Falha interna desconhecida.");
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
	public ResponseSalableComponentDTO createSalableComponent(
		CreateProductCommand command
	) {
		Product newProduct = Product.builder()
			.name(command.getName())
			.brand(command.getBrand())
			.model(command.getModel())
			.price(command.getPrice())
			.units(command.getUnits())
			.build();
		newProduct = saveProduct(newProduct);
		return toRespDTO(newProduct);
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}