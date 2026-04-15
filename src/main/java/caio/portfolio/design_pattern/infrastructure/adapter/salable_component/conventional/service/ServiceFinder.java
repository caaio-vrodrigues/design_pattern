package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.conventional.service;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.service.ServiceNotFoundException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentFinder;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Service;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.conventional.ServiceRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ServiceFinder implements SalableComponentFinder<Service> {

	private final ServiceRepository repo;

	@Override
	public Service findSalableComponent(Long id) {
		return repo.findById(id).orElseThrow(() -> 
			new ServiceNotFoundException("Não foi possível encontra `Service` para o id: `"+id+"`."));
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.SERVICE;
	}
}