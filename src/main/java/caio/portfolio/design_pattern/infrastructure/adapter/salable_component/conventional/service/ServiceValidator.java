package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.conventional.service;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.command.salable_component.conventional.service.CreateServiceCommand;
import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.service.ServiceAlreadyExistsException;
import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.ConventionalComponentValidator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.message.service.ServiceMessageCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.conventional.ServiceRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServiceValidator implements ConventionalComponentValidator<CreateServiceCommand> {
	
	private final ServiceRepository repo;
	private final ServiceMessageCreator serviceMessageCreator;
	
	@Override
	public void validateSalableComponent(CreateServiceCommand command) {
		boolean serviceAlreadyExists = repo.existsByNameAndCategory(
			command.getName(), 
			command.getCategory());
		if(serviceAlreadyExists) {
			String exceptionMsg = serviceMessageCreator.getServiceAlreadyExistsMsg(
				command.getName(), 
				command.getCategory());
			throw new ServiceAlreadyExistsException(exceptionMsg);
		}		
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.SERVICE;
	}
}