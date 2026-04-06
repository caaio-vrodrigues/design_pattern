package caio.portfolio.design_pattern.application.handler;

import org.springframework.stereotype.Service;

import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateLinkedKitItemCommand;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.KitItem;

@Service
public class CreateKitItemHandler {

	public KitItem createKitItem(CreateLinkedKitItemCommand command) {
		// TODO 
		return null;
	}
}