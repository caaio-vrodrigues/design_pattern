package caio.portfolio.design_pattern.domain.model.interfaces;

import caio.portfolio.design_pattern.application.dto.ResponseSalableComponentDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.CreateSalableComponentCommand;

public interface KitComponentCreator<T extends CreateSalableComponentCommand> extends SalableComponentCreator {

	ResponseSalableComponentDTO createKit(String code, T command);
}