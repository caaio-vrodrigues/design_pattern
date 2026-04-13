package caio.portfolio.design_pattern.domain.command.quote;

import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class RequiredProductQuantityCommand extends RequiredSalableComponentQuantityCommand {
	
	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}