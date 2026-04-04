package caio.portfolio.design_pattern.domain.command.salable_component.product;

import java.math.BigDecimal;

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
public class CreateProductCommand extends CreateSalableComponentCommand {
	
	private String name;
	private String brand;
	private String model;
	private BigDecimal price;
	private Integer units;

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.PRODUCT;
	}
}