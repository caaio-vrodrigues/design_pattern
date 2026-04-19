package caio.portfolio.design_pattern.domain.model.interfaces.salable_component;

import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;

public interface SalableComponentCreator {

	SalableComponentType getType();
}