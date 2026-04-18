package caio.portfolio.design_pattern.api.dto.salable_component.quote.item_quantity.conventional.product;

import caio.portfolio.design_pattern.api.dto.salable_component.quote.item_quantity.RequiredItemQuantityDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.quote.item_quantity.conventional.RequiredProductQuantityCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class RequiredProductQuantityDTO extends RequiredItemQuantityDTO {
	
	@Positive @NotNull
	private Integer quantity;

	@Override
	public RequiredProductQuantityCommand toCommand() {
		return RequiredProductQuantityCommand.builder()
			.requiredItemId(getRequiredItemId())
			.quantity(quantity)
			.build();
	}
}