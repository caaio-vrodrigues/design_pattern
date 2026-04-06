package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.kit;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.application.dto.ResponseKitDTO;
import caio.portfolio.design_pattern.application.dto.ResponseKitItemDTO;
import caio.portfolio.design_pattern.application.handler.CreateKitItemHandler;
import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateKitCommand;
import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateLinkedKitItemCommand;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.ConcurrentKitException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.KitComponentCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.KitItem;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.KitRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KitCreator implements KitComponentCreator<CreateKitCommand> {
	
	private final KitRepository repo;
	private final CreateKitItemHandler createKitItemHandler;
	
	private Kit saveKit(Kit kit) {
		try {
			return repo.save(kit);
		}
		catch(DataIntegrityViolationException e) {
			throw new ConcurrentKitException("Não foi possível salvar novo `Kit`: [code: `"+kit.getCode()+"`]. Falha interna desconhecida.");
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
		List<CreateLinkedKitItemCommand> commandList
	) {
		return commandList.stream()
			.map(createKitItemHandler::createKitItem)
			.toList();		
	}
	
	@Override
	public ResponseKitDTO createKit(
		String code, CreateKitCommand command
	) {
		List<KitItem> kitItemList = getKitItemList(command.getKitItemList());
		Kit newKit = Kit.builder()
			.code(code)
			.units(command.getUnits())
			.kitItemList(kitItemList)
			.build();
		newKit = saveKit(newKit);
		return toRespDTO(newKit);
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.KIT;
	}
}