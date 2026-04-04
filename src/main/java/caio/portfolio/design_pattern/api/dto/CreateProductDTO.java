package caio.portfolio.design_pattern.api.dto;

import java.math.BigDecimal;

import caio.portfolio.design_pattern.domain.model.command.CreateProductCommand;
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
	
	@NotBlank(message="O campo 'name' não pode ser vazio.")
	private String name;
	
	@NotBlank(message="O campo 'brand' não pode ser vazio.")
	private String brand;
	
	@NotBlank(message="O campo 'model' não pode ser vazio.")
	private String model;
	
	@Positive(message ="O campo 'price' deve receber um valor maior que '0.00'.")
	@NotNull(message="O campo 'price' não pode ser nulo.")
	private BigDecimal price;
	
	@Positive(message ="O campo 'units' deve receber um valor maior que '0'.")
	@NotNull(message="O campo 'units' não pode ser nulo.")
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