package caio.portfolio.design_pattern.domain.model.interfaces;

import caio.portfolio.design_pattern.application.dto.ResponseSalableComponentDTO;
import caio.portfolio.design_pattern.domain.model.command.salable_component.CreateSalableComponentCommand;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;

public interface SalableComponentCreator<T extends CreateSalableComponentCommand> {

	ResponseSalableComponentDTO createSalableComponent(T command);
	SalableComponentType getType();
}