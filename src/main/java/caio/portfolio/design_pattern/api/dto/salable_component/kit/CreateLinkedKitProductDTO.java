package caio.portfolio.design_pattern.api.dto.salable_component.kit;

import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateLinkedKitProductCommand;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class CreateLinkedKitProductDTO extends CreateLinkedKitItemDTO {

	@Override
	public CreateLinkedKitProductCommand toCommand() {
		return CreateLinkedKitProductCommand.builder()
			.salableComponentId(getSalableComponentId())
			.quantity(getQuantity())
			.build();
	}
}