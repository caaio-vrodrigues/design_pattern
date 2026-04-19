package caio.portfolio.design_pattern.api.dto.salable_component.composite.kit.linked_item.conventional.product;

import caio.portfolio.design_pattern.api.dto.salable_component.composite.kit.linked_item.CreateLinkedKitItemDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.composite.kit.linked_item.conventional.product.CreateLinkedKitProductCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class CreateLinkedKitProductDTO extends CreateLinkedKitItemDTO {
	
	@Positive @NotNull
	private Integer productQuantity;

	@Override
	public CreateLinkedKitProductCommand toCommand() {
		return CreateLinkedKitProductCommand.builder()
			.salableComponentId(getSalableComponentId())
			.productQuantity(productQuantity)
			.build();
	}
}