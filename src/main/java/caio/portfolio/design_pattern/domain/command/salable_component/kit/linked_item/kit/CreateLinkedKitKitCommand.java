package caio.portfolio.design_pattern.domain.command.salable_component.kit.linked_item.kit;

import caio.portfolio.design_pattern.domain.command.salable_component.kit.linked_item.CreateLinkedKitItemCommand;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class CreateLinkedKitKitCommand extends CreateLinkedKitItemCommand {
	
	private Integer requiredKitQuantity;

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.KIT;
	}
}