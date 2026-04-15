package caio.portfolio.design_pattern.domain.model.interfaces.salable_component.kit;

import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentValidator;

public interface KitComponentValidator extends SalableComponentValidator {

	String generateAndValidateCode();
}