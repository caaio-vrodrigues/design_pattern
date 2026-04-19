package caio.portfolio.design_pattern.api.dto.salable_component.kit;

import java.util.List;

import caio.portfolio.design_pattern.api.dto.salable_component.CreateSalableComponentDTO;
import caio.portfolio.design_pattern.api.dto.salable_component.kit.linked_item.CreateLinkedKitItemDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateKitCommand;
import caio.portfolio.design_pattern.domain.command.salable_component.kit.linked_item.CreateLinkedKitItemCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class CreateKitDTO extends CreateSalableComponentDTO {
	
	@Positive @NotNull
	private Integer units;
	
	@Valid @NotNull
	private List<CreateLinkedKitItemDTO> kitItemList;
	
	@Override
	public CreateKitCommand toCommand() {
		List<CreateLinkedKitItemCommand> commandList = kitItemList.stream()
			.map(dto -> dto.toCommand())
			.toList();
		return CreateKitCommand.builder()
			.units(units)
			.kitItemList(commandList)
			.build();
	}
}