package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.conventional.service;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.command.salable_component.conventional.service.CreateServiceCommand;
import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.service.ServiceAlreadyExistsException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.ConventionalComponentValidator;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.conventional.ServiceRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServiceValidator implements ConventionalComponentValidator<CreateServiceCommand> {
	
	private final ServiceRepository repo;
	
	@Override
	public void validateSalableComponent(CreateServiceCommand command) {
		boolean serviceAlreadyExists = repo.existsByNameAndCategory(
			command.getName(), command.getCategory());
		if(serviceAlreadyExists)
			throw new ServiceAlreadyExistsException("Falha de duplicidade ao tentar salvar novo `Service`: [name: `"+command.getName()+"`; category: `"+command.getCategory()+"`].");
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.SERVICE;
	}
}