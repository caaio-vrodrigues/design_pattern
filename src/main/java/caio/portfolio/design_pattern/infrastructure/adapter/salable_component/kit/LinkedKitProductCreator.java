package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.kit;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateLinkedKitProductCommand;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.kit_item.ConcurrentKitItemException;
import caio.portfolio.design_pattern.domain.exception.salable_component.product.ConcurrentProductException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.LinkedKitItemCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.Product;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.KitItem;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.KitItemRepository;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.ProductRepository;
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
		Kit newKit, Product product, CreateLinkedKitProductCommand command
	) {
		Integer totalRequiredProduct = command.getQuantity() * newKit.getUnits();
		product.decreaseUnits(totalRequiredProduct);
		product = updateProduct(product);
		KitItem newKitItem = KitItem.builder()
			.kit(newKit)
			.salableComponent(product)
			.salableComponentQuantity(command.getQuantity())
			.build();
		return saveKitItem(newKitItem);
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}