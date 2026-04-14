package caio.portfolio.design_pattern.api.dto.salable_component.quote;

import java.util.List;

import caio.portfolio.design_pattern.api.dto.salable_component.quote.item_quantity.RequiredItemQuantityDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.quote.CreateSalableComponentQuoteCommand;
import caio.portfolio.design_pattern.domain.command.salable_component.quote.item_quantity.RequiredItemQuantityCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Builder
@Getter
public class CreateSalableComponentQuoteDTO {
	
	@Valid @NotEmpty
	private List<RequiredItemQuantityDTO> requiredItemQuantityDTOList;

	public CreateSalableComponentQuoteCommand toCommand() {
		List<RequiredItemQuantityCommand> commandList = requiredItemQuantityDTOList
			.stream().map(dto -> dto.toCommand())
			.toList();
		return CreateSalableComponentQuoteCommand.builder()
			.requiredItemQuantityCommandList(commandList)
			.build();
	}
}