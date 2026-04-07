package caio.portfolio.design_pattern.domain.model.interfaces;

import caio.portfolio.design_pattern.application.dto.ResponseSalableComponentDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.CreateSalableComponentCommand;

public interface ConventionalComponentCreator<
	T extends CreateSalableComponentCommand, 
	R extends ResponseSalableComponentDTO
> extends SalableComponentCreator {
	
	R createSalableComponent(T command);
}