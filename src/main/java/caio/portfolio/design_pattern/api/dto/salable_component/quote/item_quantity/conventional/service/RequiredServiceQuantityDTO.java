package caio.portfolio.design_pattern.api.dto.salable_component.quote.item_quantity.conventional.service;

import caio.portfolio.design_pattern.api.dto.salable_component.quote.item_quantity.RequiredItemQuantityDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.quote.item_quantity.conventional.RequiredServiceQuantityCommand;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class RequiredServiceQuantityDTO extends RequiredItemQuantityDTO {

	@Override
	public RequiredServiceQuantityCommand toCommand() {
		return RequiredServiceQuantityCommand.builder()
			.requiredItemId(getRequiredItemId())
			.build();
	}
}