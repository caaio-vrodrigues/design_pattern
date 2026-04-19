package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.kit.linked_item.conventional.product;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.command.salable_component.kit.linked_item.conventional.product.CreateLinkedKitProductCommand;
import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.product.ConcurrentProductException;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.kit_item.ConcurrentKitItemException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.kit.linked_item.LinkedKitItemCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Product;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.linked_item.KitItem;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.conventional.ProductRepository;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.kit.linked_item.KitItemRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LinkedKitProductCreator implements LinkedKitItemCreator<Product, CreateLinkedKitProductCommand> {

	private final KitItemRepository kitItemRepo;
	private final ProductRepository productRepo;
	
	private Product updateProduct(Product product) {
		try {
			return productRepo.save(product);
		}
		catch(DataIntegrityViolationException e) {
			throw new ConcurrentProductException("Não foi possível completar a operação. Falha interna desconhecida.");
		}
	}
	
	private KitItem saveKitItem(KitItem kitItem) {
		try {
			return kitItemRepo.save(kitItem);
		}
		catch(DataIntegrityViolationException e) {
			throw new ConcurrentKitItemException("Não foi possível completar a operação. Falha interna desconhecida.");
		}
	}

	@Override
	public KitItem createKitItem(
		Kit newKit, Product product, CreateLinkedKitProductCommand command, String code
	) {
		Integer totalRequiredProduct = command.getProductQuantity() * newKit.getUnits();
		product.decreaseUnits(totalRequiredProduct);
		product = updateProduct(product);
		KitItem newKitItem = KitItem.builder()
			.code(code)
			.kit(newKit)
			.salableComponent(product)
			.salableComponentQuantity(command.getProductQuantity())
			.build();
		return saveKitItem(newKitItem);
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}