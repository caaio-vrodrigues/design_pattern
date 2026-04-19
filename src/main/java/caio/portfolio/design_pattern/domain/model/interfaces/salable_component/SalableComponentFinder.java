package caio.portfolio.design_pattern.domain.model.interfaces.salable_component;

import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;

public interface SalableComponentFinder<R extends SalableComponent> {

	R findSalableComponent(Long id);
	SalableComponentType getType();
}