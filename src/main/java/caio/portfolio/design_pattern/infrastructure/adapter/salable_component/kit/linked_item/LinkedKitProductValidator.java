package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.kit.linked_item;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.kit.kit_item.KitItemAlreadyExistsException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.kit.linked_item.LinkedKitItemValidator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Product;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.kit.linked_item.KitItemRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LinkedKitProductValidator implements LinkedKitItemValidator<Product> {
	
	private final KitItemRepository repo;
	
	private String generateCode() {
		Long maxId = repo.findMaxId();
		Long codeNumber = maxId == null ? 0 : maxId;
		return String.valueOf(codeNumber + 1);
	}
	
	@Override
	public String validateKitItemAndGenerateCode(Kit newKit, Product product) {
		if(repo.existsByKitAndSalableComponent(newKit, product))
			throw new KitItemAlreadyExistsException("Falha de duplicidade ao tentar criar `KitItem`: [kitId: `"+newKit.getId()+"`; productId: `"+product.getId()+"`].");
		return generateCode();
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}