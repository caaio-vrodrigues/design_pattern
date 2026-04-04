package caio.portfolio.design_pattern.domain.model.command;

import java.math.BigDecimal;

import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.enums.ServiceCategory;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class CreateServiceCommand extends CreateSalableComponentCommand {
	
	private String name;
	private ServiceCategory category;
	private BigDecimal price;
	private Boolean isAvailable;
	
	@Override
	public SalableComponentType getType() {
		return SalableComponentType.SERVICE;
	}
}