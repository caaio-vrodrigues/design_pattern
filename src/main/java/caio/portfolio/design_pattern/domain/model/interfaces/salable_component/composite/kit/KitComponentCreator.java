package caio.portfolio.design_pattern.domain.model.interfaces.salable_component.composite.kit;

import caio.portfolio.design_pattern.application.dto.salable_component.composite.kit.ResponseKitDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.composite.kit.CreateKitCommand;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentCreator;

public interface KitComponentCreator extends SalableComponentCreator {
	
	ResponseKitDTO createKit(String code, CreateKitCommand createKitCommand);
}