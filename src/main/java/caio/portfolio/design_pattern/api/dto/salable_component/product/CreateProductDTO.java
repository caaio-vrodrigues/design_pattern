package caio.portfolio.design_pattern.api.dto.salable_component.product;

import java.math.BigDecimal;

import org.springframework.context.MessageSource;

import caio.portfolio.design_pattern.api.dto.salable_component.CreateSalableComponentDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.product.CreateProductCommand;
import jakarta.validation.constraints.NotBlank;
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
public class CreateProductDTO extends CreateSalableComponentDTO {
	
	private MessageSource messageSource;
	
	@NotBlank 
	private String name;	
	
	@NotBlank 
	private String brand;
	
	@NotBlank 
	private String model;
	
	@Positive @NotNull 
	private BigDecimal price;	
	
	@Positive @NotNull 
	private Integer units;
	
	@Override
	public CreateProductCommand toCommand() {
		return CreateProductCommand.builder()
			.name(name)
			.brand(brand)
			.model(model)
			.price(price)
			.units(units)
			.build();
	}
}