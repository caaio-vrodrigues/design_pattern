package caio.portfolio.design_pattern.domain.command.salable_component.quote.item_quantity.conventional;

import caio.portfolio.design_pattern.domain.command.salable_component.quote.item_quantity.RequiredItemQuantityCommand;
import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class RequiredServiceQuantityCommand extends RequiredItemQuantityCommand {

	private final Integer quantity = 1;

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.SERVICE;
	}
}