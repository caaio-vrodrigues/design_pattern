package caio.portfolio.design_pattern.domain.model.interfaces;

import caio.portfolio.design_pattern.application.dto.ResponseKitDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateKitCommand;

public interface KitComponentCreator extends SalableComponentCreator {
	
	ResponseKitDTO createKit(String code, CreateKitCommand createKitCommand);
}