package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.conventional.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.application.dto.salable_component.conventional.service.ResponseServiceDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.conventional.service.CreateServiceCommand;
import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.service.ConcurrentServiceException;
import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.ConventionalComponentCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.message.service.ServiceMessageCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Service;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.conventional.ServiceRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServiceCreator implements ConventionalComponentCreator<
	CreateServiceCommand, ResponseServiceDTO
> {	
	private final ServiceRepository repo;
	private final ServiceMessageCreator serviceMessageCreator;
	
	private Service saveService(Service service) {
		try {
			return repo.save(service);
		}
		catch(DataIntegrityViolationException e) {
			String entityName = Service.class.getName();
			String exceptionMsg = serviceMessageCreator
				.getConcurrentEntityMsg(entityName);
			throw new ConcurrentServiceException(exceptionMsg);
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