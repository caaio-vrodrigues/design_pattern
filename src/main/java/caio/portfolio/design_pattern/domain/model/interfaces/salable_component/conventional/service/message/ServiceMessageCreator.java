package caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.service.message;

import caio.portfolio.design_pattern.domain.model.enums.salable_component.conventional.service.ServiceCategory;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentMessageCreator;

public interface ServiceMessageCreator extends SalableComponentMessageCreator {

	String getServiceAlreadyExistsMsg(String name, ServiceCategory category);
}