package caio.portfolio.design_pattern.domain.model.interfaces.salable_component.composite.kit;

import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentValidator;

public interface KitComponentValidator extends SalableComponentValidator {

	String generateAndValidateCode();
}