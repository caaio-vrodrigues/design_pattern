package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.service;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.command.salable_component.service.CreateServiceCommand;
import caio.portfolio.design_pattern.domain.exception.salable_component.service.ServiceAlreadyExistsException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.SalableComponentValidator;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.ServiceRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServiceValidator implements SalableComponentValidator<CreateServiceCommand> {
	
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