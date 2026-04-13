package caio.portfolio.design_pattern.api.dto.quote;

import caio.portfolio.design_pattern.domain.command.quote.RequiredKitQuantityCommand;
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
public class RequiredKitQuantityDTO extends RequiredSalableComponentQuantityDTO {
	
	@Positive @NotNull
	private Integer quantity;
	
	@Override
	public RequiredKitQuantityCommand toCommand() {
		return RequiredKitQuantityCommand.builder()
			.requiredSalableComponentId(getRequiredSalableComponentId())
			.quantity(quantity)
			.build();
	}
}