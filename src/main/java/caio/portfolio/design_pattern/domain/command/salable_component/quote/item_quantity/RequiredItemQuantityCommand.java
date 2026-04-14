package caio.portfolio.design_pattern.domain.command.salable_component.quote.item_quantity;

import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public abstract class RequiredItemQuantityCommand {

	private Long requiredItemId;
	private Integer quantity;
	
	public abstract SalableComponentType getType();
}