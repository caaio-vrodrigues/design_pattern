package caio.portfolio.design_pattern.domain.model.interfaces;

import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateLinkedKitItemCommand;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.KitItem;

public interface LinkedKitItemCreator<T extends SalableComponent, C extends CreateLinkedKitItemCommand> {

	KitItem createKitItem(Kit newKit, T salableComponent, C command, String code);
	SalableComponentType getType();
}