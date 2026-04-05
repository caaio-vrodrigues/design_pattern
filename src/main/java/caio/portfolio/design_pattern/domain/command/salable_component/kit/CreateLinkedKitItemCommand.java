package caio.portfolio.design_pattern.domain.command.salable_component.kit;

import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public abstract class CreateLinkedKitItemCommand {

	private Long salableComponentId;
	private Integer quantity;
	
	public abstract SalableComponentType getType();
}