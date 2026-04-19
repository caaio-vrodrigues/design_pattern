package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.kit.linked_item.kit;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.command.salable_component.kit.linked_item.kit.CreateLinkedKitKitCommand;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.ConcurrentKitException;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.kit_item.ConcurrentKitItemException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.kit.linked_item.LinkedKitItemCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.linked_item.KitItem;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.kit.KitRepository;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.kit.linked_item.KitItemRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LinkedKitKitCreator implements LinkedKitItemCreator<Kit, CreateLinkedKitKitCommand> {
	
	private final KitItemRepository kitItemRepo;
	private final KitRepository kitRepo;
	
	private Kit updateRequiredKit(Kit requiredKit) {
		try {
			return kitRepo.save(requiredKit);
		}
		catch(DataIntegrityViolationException e) {
			throw new ConcurrentKitException("Não foi possível completar a operação. Falha interna desconhecida.");
		}
	}
	
	private KitItem saveKitItem(KitItem kitItem) {
		try {
			return kitItemRepo.save(kitItem);
		}
		catch(DataIntegrityViolationException e){
			throw new ConcurrentKitItemException("Não foi possível completar a operação. Falha interna desconhecida.");
		}
	}
	
	@Override
	public KitItem createKitItem(
		Kit newKit, Kit requiredKit, CreateLinkedKitKitCommand command, String code
	) {
		Integer totalRequiredQuantity = command.getRequiredKitQuantity() * newKit.getUnits();
		requiredKit.decreaseUnits(totalRequiredQuantity);
		requiredKit = updateRequiredKit(requiredKit);
		KitItem newKitItem = KitItem.builder()
			.code(code)
			.kit(newKit)
			.salableComponent(requiredKit)
			.salableComponentQuantity(command.getRequiredKitQuantity())
			.build();
		return saveKitItem(newKitItem);
	}
	
	@Override
	public SalableComponentType getType() {
		return SalableComponentType.KIT;
	}
}