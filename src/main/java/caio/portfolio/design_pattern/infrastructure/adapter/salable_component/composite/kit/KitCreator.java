package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.composite.kit;

import java.util.ArrayList;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.application.dto.salable_component.composite.kit.ResponseKitDTO;
import caio.portfolio.design_pattern.application.dto.salable_component.composite.kit.kit_item.ResponseKitItemDTO;
import caio.portfolio.design_pattern.application.handler.salable_component.composite.kit.linked_item.CreateLinkedKitItemHandler;
import caio.portfolio.design_pattern.domain.command.salable_component.composite.kit.CreateKitCommand;
import caio.portfolio.design_pattern.domain.command.salable_component.composite.kit.linked_item.CreateLinkedKitItemCommand;
import caio.portfolio.design_pattern.domain.exception.salable_component.composite.kit.ConcurrentKitException;
import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.composite.kit.KitComponentCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.composite.kit.KitMessageCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.composite.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.composite.kit.linked_item.KitItem;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.composite.kit.KitRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KitCreator implements KitComponentCreator {
	
	private final KitRepository repo;
	private final CreateLinkedKitItemHandler createKitItemHandler;
	private final KitMessageCreator kitMessageCreator;
	
	private Kit saveKit(Kit kit) {
		try {
			return repo.save(kit);
		}
		catch(DataIntegrityViolationException e) {
			String entityName = Kit.class.getName();
			String exceptionMsg = kitMessageCreator
				.getConcurrentEntityMsg(entityName);
			throw new ConcurrentKitException(exceptionMsg);
		}
	}
	
	private List<ResponseKitItemDTO> getRespKitItemDTOList(List<KitItem> kitItemList){
		return kitItemList.stream()
			.map(kitItem -> ResponseKitItemDTO.builder()
				.id(kitItem.getId())
				.kitId(kitItem.getKit().getId())
				.salableComponentId(kitItem.getSalableComponent().getId())
				.salableComponentQuantity(kitItem.getSalableComponentQuantity())
				.build())
			.toList();
	}
	
	private ResponseKitDTO toRespDTO(Kit kit) {
		List<ResponseKitItemDTO> kitItemList = getRespKitItemDTOList(kit.getKitItemList());
		return ResponseKitDTO.builder()
			.id(kit.getId())
			.code(kit.getCode())
			.units(kit.getUnits())
			.respKitItemDTOList(kitItemList)
			.build();
	}
	
	private List<KitItem> getKitItemList(
		Kit newKit,
		List<CreateLinkedKitItemCommand> commandList
	) {
		return commandList.stream()
			.map(command -> createKitItemHandler.createKitItem(newKit, command))
			.toList();		
	}
	
	@Override
	public ResponseKitDTO createKit(
		String code, CreateKitCommand command
	) {
		Kit newKit = Kit.builder()
			.code(code)
			.units(command.getUnits())
			.kitItemList(new ArrayList<>())
			.build();
		newKit = saveKit(newKit);
		List<KitItem> kitItemList = getKitItemList(
			newKit, 
			command.getKitItemList());
		newKit = newKit.toBuilder()
			.kitItemList(kitItemList)
			.build();
		return toRespDTO(newKit);
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.KIT;
	}
}