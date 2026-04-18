package caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.message;

import caio.portfolio.design_pattern.domain.model.enums.ServiceCategory;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.message.SalableComponentMessageCreator;

public interface ServiceMessageCreator extends SalableComponentMessageCreator {

	String getServiceAlreadyExistsMsg(String name, ServiceCategory category);
}