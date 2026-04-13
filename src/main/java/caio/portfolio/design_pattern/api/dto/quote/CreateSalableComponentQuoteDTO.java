package caio.portfolio.design_pattern.api.dto.quote;

import java.util.List;

import caio.portfolio.design_pattern.domain.command.quote.CreateSalableComponentQuoteCommand;
import caio.portfolio.design_pattern.domain.command.quote.RequiredSalableComponentQuantityCommand;
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
	private List<RequiredSalableComponentQuantityDTO> requiredSalableComponentQuantityDTOList;

	public CreateSalableComponentQuoteCommand toCommand() {
		List<RequiredSalableComponentQuantityCommand> commandList = requiredSalableComponentQuantityDTOList
			.stream().map(dto -> dto.toCommand()).toList();
		return CreateSalableComponentQuoteCommand.builder()
			.requiredSalableComponentQuantityCommandList(commandList)
			.build();
	}
}