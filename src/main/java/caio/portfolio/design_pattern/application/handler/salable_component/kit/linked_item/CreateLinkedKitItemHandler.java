package caio.portfolio.design_pattern.application.handler.salable_component.kit.linked_item;

import java.util.Map;

import org.springframework.stereotype.Service;

import caio.portfolio.design_pattern.domain.command.salable_component.kit.linked_item.CreateLinkedKitItemCommand;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentFinder;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.kit.linked_item.LinkedKitItemCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.kit.linked_item.LinkedKitItemValidator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.linked_item.KitItem;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateLinkedKitItemHandler {
	
	private final Map<
		SalableComponentType, 
		SalableComponentFinder<SalableComponent>> salableComponentFinders;
	
	private final Map<
		SalableComponentType, 
		LinkedKitItemValidator<SalableComponent>> linkedKitItemValidators;
	
	private final Map<
		SalableComponentType, 
		LinkedKitItemCreator<SalableComponent, CreateLinkedKitItemCommand>> linkedKitItemCreators;

	public KitItem createKitItem(Kit newKit, CreateLinkedKitItemCommand command) {
		SalableComponent salableComponent = salableComponentFinders
			.get(command.getType())
			.findSalableComponent(command.getSalableComponentId());
		String code = linkedKitItemValidators
			.get(command.getType())
			.validateKitItemAndGenerateCode(newKit, salableComponent);
		return linkedKitItemCreators
			.get(command.getType())
			.createKitItem(newKit, salableComponent, command, code);
	}
}