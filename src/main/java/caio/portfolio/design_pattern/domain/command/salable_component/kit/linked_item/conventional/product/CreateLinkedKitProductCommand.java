package caio.portfolio.design_pattern.domain.command.salable_component.kit.linked_item.conventional.product;

import caio.portfolio.design_pattern.domain.command.salable_component.kit.linked_item.CreateLinkedKitItemCommand;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class CreateLinkedKitProductCommand extends CreateLinkedKitItemCommand {
	
	private Integer productQuantity;
	
	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}