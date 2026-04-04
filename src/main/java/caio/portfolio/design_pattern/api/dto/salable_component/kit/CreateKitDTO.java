package caio.portfolio.design_pattern.api.dto.salable_component.kit;

import java.util.List;

import caio.portfolio.design_pattern.api.dto.salable_component.CreateSalableComponentDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateKitCommand;
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
public class CreateKitDTO extends CreateSalableComponentDTO {
	
	@Positive(message = "O campo 'units' deve receber um valor maior que '0'.")
	@NotNull(message="O campo 'units' não pode ser nulo.")
	private Integer units;
	
	@NotNull(message="O campo 'kitItemList' não pode ser nulo.")
	private List<Object> kitItemList; // to-do
	
	@Override
	public CreateKitCommand toCommand() {
		return CreateKitCommand.builder()
			.units(units)
			.kitItemList(kitItemList)
			.build();
	}
}