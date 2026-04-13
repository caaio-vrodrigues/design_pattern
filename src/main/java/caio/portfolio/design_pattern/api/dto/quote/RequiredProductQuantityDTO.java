package caio.portfolio.design_pattern.api.dto.quote;

import caio.portfolio.design_pattern.domain.command.quote.RequiredProductQuantityCommand;
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
public class RequiredProductQuantityDTO extends RequiredSalableComponentQuantityDTO {
	
	@Positive @NotNull
	private Integer quantity;

	@Override
	public RequiredProductQuantityCommand toCommand() {
		return RequiredProductQuantityCommand.builder()
			.requiredSalableComponentId(getRequiredSalableComponentId())
			.quantity(quantity)
			.build();
	}
}