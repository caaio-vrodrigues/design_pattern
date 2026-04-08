package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.kit;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.kit.InsufficientKitUnitsException;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.kit_item.KitItemAlreadyExistsException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.LinkedKitItemValidator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.KitItemRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LinkedKitKitValidator implements LinkedKitItemValidator<Kit> {

	private final KitItemRepository repo;
	
	private void validateKitItemUniqueness(Kit newKit, Kit kit) {
		if(repo.existsByKitAndSalableComponent(newKit, kit))
			throw new KitItemAlreadyExistsException("Falha de duplicidade ao tentar criar `KitItem`: [newKitId: `"+newKit.getId()+"`; kitId: `"+kit.getId()+"`].");
	}
	
	private void validateAvailableKitUnits(Integer requiredKitQuantity, Kit kit) {
		if(requiredKitQuantity > kit.getUnits())
			throw new InsufficientKitUnitsException("Unidades de `Kit` insuficiente para realizar a operação. Unidades disponíveis: `"+kit.getUnits()+"`.");
	}

	@Override
	public void validateKitItem(Kit newKit, Kit kit, Integer requiredKitQuantity) {
		Integer totalRequiredKitQuantity = requiredKitQuantity * newKit.getUnits();
		validateKitItemUniqueness(newKit, kit);
		validateAvailableKitUnits(totalRequiredKitQuantity, kit);
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.KIT;
	}
}