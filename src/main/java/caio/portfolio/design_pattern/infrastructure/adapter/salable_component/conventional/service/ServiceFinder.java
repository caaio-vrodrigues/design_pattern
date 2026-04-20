package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.conventional.service;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.service.ServiceNotFoundException;
import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentFinder;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.service.message.ServiceMessageCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Service;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.conventional.ServiceRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServiceFinder implements SalableComponentFinder<Service> {

	private final ServiceRepository repo;
	private final ServiceMessageCreator serviceMessageCreator;

	@Override
	public Service findSalableComponent(Long id) {
		String entityName = Service.class.getName();
		String exceptionMsg = serviceMessageCreator
			.getEntityNotFoundByIdMsg(entityName, id);
		return repo.findById(id).orElseThrow(() -> 
			new ServiceNotFoundException(exceptionMsg));
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.SERVICE;
	}
}