package caio.portfolio.design_pattern.domain.model.interfaces.salable_component.kit;

import caio.portfolio.design_pattern.application.dto.salable_component.kit.ResponseKitDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateKitCommand;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentCreator;

public interface KitComponentCreator extends SalableComponentCreator {
	
	ResponseKitDTO createKit(String code, CreateKitCommand createKitCommand);
}