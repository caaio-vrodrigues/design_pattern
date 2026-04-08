package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.kit;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.kit.kit_item.KitItemAlreadyExistsException;
import caio.portfolio.design_pattern.domain.exception.salable_component.product.InsufficientProductUnitsException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.LinkedKitItemValidator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.Product;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.KitItemRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LinkedKitProductValidator implements LinkedKitItemValidator<Product> {
	
	private final KitItemRepository repo;
	
	private void validateUniqueness(Kit kit, Product product) {
		if(repo.existsByKitAndSalableComponent(kit, product))
			throw new KitItemAlreadyExistsException("Falha de duplicidade ao tentar criar `KitItem`: [kitId: `"+kit.getId()+"`; productId: `"+product.getId()+"`].");
	}
	
	private void validateAvailableProductUnits(Integer requiredProductQuantity, Product product) {
		if(requiredProductQuantity > product.getUnits())
			throw new InsufficientProductUnitsException("Unidades de `Product` insuficiente para realizar a operação. Unidades disponíveis: `"+product.getUnits()+"`.");
	}

	@Override
	public void validateKitItem(
		Kit newKit, Product product, Integer requiredProductQuantity
	) {
		Integer totalQuantity = requiredProductQuantity * newKit.getUnits();
		validateUniqueness(newKit, product);
		validateAvailableProductUnits(totalQuantity, product);
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}