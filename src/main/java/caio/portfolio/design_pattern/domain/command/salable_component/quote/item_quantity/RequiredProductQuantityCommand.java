package caio.portfolio.design_pattern.domain.command.salable_component.quote.item_quantity;

import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class RequiredProductQuantityCommand extends RequiredItemQuantityCommand {
	
	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}