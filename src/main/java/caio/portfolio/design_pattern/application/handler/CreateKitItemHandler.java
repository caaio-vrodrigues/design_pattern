package caio.portfolio.design_pattern.application.handler;

import java.util.Map;

import org.springframework.stereotype.Service;

import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateLinkedKitItemCommand;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.LinkedKitItemCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.LinkedKitItemValidator;
import caio.portfolio.design_pattern.domain.model.interfaces.SalableComponentFinder;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.KitItem;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateKitItemHandler {
	
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
		linkedKitItemValidators
			.get(command.getType())
			.validateKitItem(newKit, salableComponent);
		return linkedKitItemCreators
			.get(command.getType())
			.createKitItem(newKit, salableComponent, command);
	}
}