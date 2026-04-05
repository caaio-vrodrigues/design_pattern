package caio.portfolio.design_pattern.domain.model.interfaces;

import caio.portfolio.design_pattern.domain.command.salable_component.CreateSalableComponentCommand;

public interface ConventionalComponentValidator<T extends CreateSalableComponentCommand> extends SalableComponentValidator {
	
	void validateSalableComponent(T command);
}