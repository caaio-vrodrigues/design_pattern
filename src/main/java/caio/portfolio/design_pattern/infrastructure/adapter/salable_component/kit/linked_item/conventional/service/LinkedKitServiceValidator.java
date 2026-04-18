package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.kit.linked_item.conventional.service;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.service.UnavailableServiceException;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.kit_item.KitItemAlreadyExistsException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.kit.linked_item.LinkedKitItemValidator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Service;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.kit.linked_item.KitItemRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LinkedKitServiceValidator implements LinkedKitItemValidator<Service> {

	private final KitItemRepository repo;
	
	private void validateUniqueness(Kit newKit, Service service) {
		if(repo.existsByKitAndSalableComponent(newKit, service))
			throw new KitItemAlreadyExistsException("Falha de duplicidade ao tentar criar `KitItem`: [kitId: `"+newKit.getId()+"`; servicetId: `"+service.getId()+"`].");
	}
	
	private void validateAvailability(Service service) {
		if(!service.getIsAvailable())
			throw new UnavailableServiceException("O serviço solicitado está indisponível. `Service`: [name: `"+service.getName()+"`; category: `"+service.getCategory()+"`].");
	}
	
	private String generateCode() {
		Long maxId = repo.findMaxId();
		Long codeNumber = maxId == null ? 0 : maxId;
		return String.valueOf(codeNumber + 1);
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.SERVICE;
	}

	@Override
	public String validateKitItemAndGenerateCode(Kit newKit, Service service) {
		validateUniqueness(newKit, service);
		validateAvailability(service);
		return generateCode();
	}
}