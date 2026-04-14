package caio.portfolio.design_pattern.domain.command.salable_component.quote;

import java.util.List;

import caio.portfolio.design_pattern.domain.command.salable_component.quote.item_quantity.RequiredItemQuantityCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Builder
@Getter
public class CreateSalableComponentQuoteCommand {
	
	private List<RequiredItemQuantityCommand> requiredSalableComponentQuantityCommandList;
}