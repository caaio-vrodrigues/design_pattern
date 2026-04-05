package caio.portfolio.design_pattern.domain.model.interfaces;

import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;

public interface SalableComponentCreator {

	SalableComponentType getType();
}