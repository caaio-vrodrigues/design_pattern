package caio.portfolio.design_pattern.api.dto.quote;

import caio.portfolio.design_pattern.domain.command.quote.RequiredServiceQuantityCommand;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class RequiredServiceQuantityDTO extends RequiredSalableComponentQuantityDTO {

	@Override
	public RequiredServiceQuantityCommand toCommand() {
		return RequiredServiceQuantityCommand.builder()
			.requiredSalableComponentId(getRequiredSalableComponentId())
			.build();
	}
}