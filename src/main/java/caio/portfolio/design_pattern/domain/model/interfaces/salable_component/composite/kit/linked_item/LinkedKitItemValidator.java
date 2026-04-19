package caio.portfolio.design_pattern.domain.model.interfaces.salable_component.composite.kit.linked_item;

import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.composite.kit.Kit;

public interface LinkedKitItemValidator<T extends SalableComponent> {

	String validateKitItemAndGenerateCode(Kit newKit, T linkedSalableComponent);
	SalableComponentType getType();
}