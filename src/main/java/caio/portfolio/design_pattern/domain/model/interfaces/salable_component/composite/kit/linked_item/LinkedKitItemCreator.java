package caio.portfolio.design_pattern.domain.model.interfaces.salable_component.composite.kit.linked_item;

import caio.portfolio.design_pattern.domain.command.salable_component.composite.kit.linked_item.CreateLinkedKitItemCommand;
import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.composite.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.composite.kit.linked_item.KitItem;

public interface LinkedKitItemCreator<T extends SalableComponent, C extends CreateLinkedKitItemCommand> {

	KitItem createKitItem(Kit newKit, T salableComponent, C command, String code);
	SalableComponentType getType();
}