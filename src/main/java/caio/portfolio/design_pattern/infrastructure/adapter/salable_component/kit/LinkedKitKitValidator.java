package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.kit;

import org.springframework.stereotype.Component;

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

	@Override
	public void validateKitItem(Kit newKit, Kit kit) {
		if(repo.existsByKitAndSalableComponent(newKit, kit))
			throw new KitItemAlreadyExistsException("Falha de duplicidade ao tentar criar `KitItem`: [newKitId: `"+newKit.getId()+"`; kitId: `"+kit.getId()+"`].");
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.KIT;
	}
}