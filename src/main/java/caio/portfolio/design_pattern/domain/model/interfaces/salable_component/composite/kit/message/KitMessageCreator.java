package caio.portfolio.design_pattern.domain.model.interfaces.salable_component.composite.kit.message;

import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentMessageCreator;

public interface KitMessageCreator extends SalableComponentMessageCreator {

	String getKitAlreadyExistsMsg(String code);
}