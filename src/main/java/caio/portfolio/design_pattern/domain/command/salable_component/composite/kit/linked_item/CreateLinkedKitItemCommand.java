package caio.portfolio.design_pattern.domain.command.salable_component.composite.kit.linked_item;

import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public abstract class CreateLinkedKitItemCommand {

	private Long salableComponentId;
	
	public abstract SalableComponentType getType();
}