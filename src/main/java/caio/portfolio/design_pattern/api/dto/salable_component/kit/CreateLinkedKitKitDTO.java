package caio.portfolio.design_pattern.api.dto.salable_component.kit;

import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateLinkedKitKitCommand;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class CreateLinkedKitKitDTO extends CreateLinkedKitItemDTO {
	
	@Override
	public CreateLinkedKitKitCommand toCommand() {
		return CreateLinkedKitKitCommand.builder()
			.salableComponentId(getSalableComponentId())
			.quantity(getQuantity())
			.build();
	}
}