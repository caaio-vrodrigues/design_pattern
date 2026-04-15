package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.conventional.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.application.dto.salable_component.conventional.ResponseServiceDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.conventional.service.CreateServiceCommand;
import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.service.ConcurrentServiceException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.ConventionalComponentCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Service;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.conventional.ServiceRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServiceCreator implements ConventionalComponentCreator<
	CreateServiceCommand, ResponseServiceDTO
> {	
	private final ServiceRepository repo;
	
	private Service saveService(Service service) {
		try {
			return repo.save(service);
		}
		catch(DataIntegrityViolationException e) {
			throw new ConcurrentServiceException("Não foi possível completar a operação. Falha interna desconhecida.");
		}
	}
	
	private ResponseServiceDTO toRespDTO(Service service) {
		return ResponseServiceDTO.builder()
			.id(service.getId())
			.name(service.getName())
			.category(service.getCategory())
			.price(service.getPrice())
			.isAvailable(service.getIsAvailable())
			.build();
	}
	
	@Override
	public ResponseServiceDTO createSalableComponent(CreateServiceCommand command) {
		Service newService = Service.builder()
			.name(command.getName())
			.category(command.getCategory())
			.price(command.getPrice())
			.isAvailable(command.getIsAvailable())
			.build();
			newService = saveService(newService);
		return toRespDTO(newService);
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.SERVICE;
	}
}