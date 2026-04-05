package caio.portfolio.design_pattern.domain.command.salable_component.kit;

import java.util.List;

import caio.portfolio.design_pattern.domain.command.salable_component.CreateSalableComponentCommand;
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
public class CreateKitCommand extends CreateSalableComponentCommand {
	
	private Integer units;
	private List<CreateLinkedKitItemCommand> kitItemList;
	
	@Override
	public SalableComponentType getType() {
		return SalableComponentType.KIT;
	}
}