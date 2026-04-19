package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.composite.kit.linked_item.conventional.service;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.command.salable_component.composite.kit.linked_item.conventional.service.CreateLinkedKitServiceCommand;
import caio.portfolio.design_pattern.domain.exception.salable_component.composite.kit.kit_item.ConcurrentKitItemException;
import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.composite.kit.linked_item.LinkedKitItemCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.composite.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.composite.kit.linked_item.KitItem;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Service;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.composite.kit.linked_item.KitItemRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LinkedKitServiceCreator implements LinkedKitItemCreator<Service, CreateLinkedKitServiceCommand> {
	
	private final KitItemRepository repo;
	
	private KitItem saveKitItem(KitItem kitItem) {
		try {
			return repo.save(kitItem);
		}
		catch(DataIntegrityViolationException e){
			throw new ConcurrentKitItemException("Não foi possível completar a operação. Falha interna desconhecida.");
		}
	}

	@Override
	public KitItem createKitItem(
		Kit newKit, Service service, CreateLinkedKitServiceCommand command, String code
	) {
		KitItem newKitItem = KitItem.builder()
			.code(code)
			.kit(newKit)
			.salableComponent(service)
			.salableComponentQuantity(1)
			.build();
		return saveKitItem(newKitItem);
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.SERVICE;
	}
}