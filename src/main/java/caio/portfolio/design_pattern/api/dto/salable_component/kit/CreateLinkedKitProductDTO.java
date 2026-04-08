package caio.portfolio.design_pattern.api.dto.salable_component.kit;

import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateLinkedKitProductCommand;
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
	
	@Positive(message="O campo 'quantity' deve receber um valor maior que '0'.")
	@NotNull(message="O campo 'quantity' não pode ser nulo.")
	private Integer quantity;

	@Override
	public CreateLinkedKitProductCommand toCommand() {
		return CreateLinkedKitProductCommand.builder()
			.salableComponentId(getSalableComponentId())
			.quantity(getQuantity())
			.build();
	}
}