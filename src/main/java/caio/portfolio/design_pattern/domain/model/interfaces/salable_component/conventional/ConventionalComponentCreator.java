package caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional;

import caio.portfolio.design_pattern.application.dto.salable_component.ResponseSalableComponentDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.CreateSalableComponentCommand;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentCreator;

public interface ConventionalComponentCreator<
	T extends CreateSalableComponentCommand, 
	R extends ResponseSalableComponentDTO
> extends SalableComponentCreator {
	
	R createSalableComponent(T command);
}