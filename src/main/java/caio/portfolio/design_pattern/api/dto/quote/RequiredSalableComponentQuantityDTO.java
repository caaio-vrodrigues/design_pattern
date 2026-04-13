package caio.portfolio.design_pattern.api.dto.quote;

import caio.portfolio.design_pattern.domain.command.quote.RequiredSalableComponentQuantityCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Builder
@Getter
public class RequiredSalableComponentQuantityDTO {

	@Positive @NotNull
	private Long requiredSalableComponentId;
	
	@Positive @NotNull
	private Integer units;
	
	public RequiredSalableComponentQuantityCommand toCommand() {
		return RequiredSalableComponentQuantityCommand.builder()
			.requiredSalableComponentId(requiredSalableComponentId)
			.units(units)
			.build();
	}
}