package caio.portfolio.design_pattern.domain.command.salable_component.conventional.service;

import java.math.BigDecimal;

import caio.portfolio.design_pattern.domain.command.salable_component.CreateSalableComponentCommand;
import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.enums.salable_component.conventional.service.ServiceCategory;
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