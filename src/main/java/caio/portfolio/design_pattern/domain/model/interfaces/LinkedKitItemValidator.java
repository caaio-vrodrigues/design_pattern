package caio.portfolio.design_pattern.domain.model.interfaces;

import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;

public interface LinkedKitItemValidator<T extends SalableComponent> {

	void validateKitItem(Kit newKit, T linkedSalableComponent, Integer salableComponentQuantity);
	SalableComponentType getType();
}