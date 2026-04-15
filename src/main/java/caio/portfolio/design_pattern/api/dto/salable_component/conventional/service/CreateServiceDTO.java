package caio.portfolio.design_pattern.api.dto.salable_component.conventional.service;

import java.math.BigDecimal;

import caio.portfolio.design_pattern.api.dto.salable_component.CreateSalableComponentDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.conventional.service.CreateServiceCommand;
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
	
	@NotBlank
	private String name;
	
	@NotNull
	private ServiceCategory category;
	
	@Positive @NotNull
	private BigDecimal price;
	
	@NotNull
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