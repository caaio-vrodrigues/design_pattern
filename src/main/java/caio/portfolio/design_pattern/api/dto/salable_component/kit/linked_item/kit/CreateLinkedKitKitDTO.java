package caio.portfolio.design_pattern.api.dto.salable_component.kit.linked_item.kit;

import caio.portfolio.design_pattern.api.dto.salable_component.kit.linked_item.CreateLinkedKitItemDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateLinkedKitKitCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class CreateLinkedKitKitDTO extends CreateLinkedKitItemDTO {
	
	@Positive @NotNull
	private Integer requiredKitQuantity;
	
	@Override
	public CreateLinkedKitKitCommand toCommand() {
		return CreateLinkedKitKitCommand.builder()
			.salableComponentId(getSalableComponentId())
			.requiredKitQuantity(requiredKitQuantity)
			.build();
	}
}