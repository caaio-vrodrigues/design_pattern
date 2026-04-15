package caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional;

import caio.portfolio.design_pattern.domain.command.salable_component.CreateSalableComponentCommand;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentValidator;

public interface ConventionalComponentValidator<T extends CreateSalableComponentCommand> extends SalableComponentValidator {
	
	void validateSalableComponent(T command);
}