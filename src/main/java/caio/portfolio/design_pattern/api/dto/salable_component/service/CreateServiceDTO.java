package caio.portfolio.design_pattern.api.dto.salable_component.service;

import java.math.BigDecimal;

import caio.portfolio.design_pattern.api.dto.salable_component.CreateSalableComponentDTO;
import caio.portfolio.design_pattern.domain.model.command.salable_component.service.CreateServiceCommand;
import caio.portfolio.design_pattern.domain.model.enums.ServiceCategory;
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
public class CreateServiceDTO extends CreateSalableComponentDTO {
	
	@NotBlank(message="O campo 'name' não pode ser vazio.")
	private String name;
	
	@NotNull(message="O campo 'category' não pode ser nulo.")
	private ServiceCategory category;
	
	@Positive(message="O campo 'price' deve receber um valor maior que '0.00'.")
	@NotNull(message="O campo 'price' não pode ser nulo.")
	private BigDecimal price;
	
	
	@NotNull(message="O campo 'isAvailable' não pode ser nulo.")
	private Boolean isAvailable;
	
	@Override
	public CreateServiceCommand toCommand() {
		return CreateServiceCommand.builder()
			.name(name)
			.category(category)
			.price(price)
			.isAvailable(isAvailable)
			.build();
	}
}