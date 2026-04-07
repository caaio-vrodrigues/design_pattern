package caio.portfolio.design_pattern.application.handler;

import java.util.Map;

import org.springframework.stereotype.Service;

import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateLinkedKitItemCommand;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.SalableComponentFinder;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.KitItem;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateKitItemHandler {
	
	private final Map<
		SalableComponentType, 
		SalableComponentFinder<SalableComponent>> salableComponentFinders;

	public KitItem createKitItem(CreateLinkedKitItemCommand command) {
		SalableComponent salableComponent = salableComponentFinders
			.get(command.getType())
			.findSalableComponent(command.getSalableComponentId());
		return null; // to-do
	}
}