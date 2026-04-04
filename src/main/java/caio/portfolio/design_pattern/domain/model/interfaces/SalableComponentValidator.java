package caio.portfolio.design_pattern.domain.model.interfaces;

import caio.portfolio.design_pattern.domain.model.command.salable_component.CreateSalableComponentCommand;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;

public interface SalableComponentValidator<T extends CreateSalableComponentCommand> {

	void validateSalableComponent(T command);
	SalableComponentType getType();
}