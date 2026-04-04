package caio.portfolio.design_pattern.application.dto;

import java.math.BigDecimal;

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
public class ResponseServiceDTO extends ResponseSalableComponentDTO {

	private String name;
	private ServiceCategory category;
	private BigDecimal price;
	private Boolean isAvailable;
}