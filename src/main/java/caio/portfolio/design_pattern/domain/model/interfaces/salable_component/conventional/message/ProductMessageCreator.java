package caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.message;

import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentMessageCreator;

public interface ProductMessageCreator extends SalableComponentMessageCreator {

	String getProductAlreadyExistsMsg(String name, String brand, String model);
}