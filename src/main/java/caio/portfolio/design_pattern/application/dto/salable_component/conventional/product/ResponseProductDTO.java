package caio.portfolio.design_pattern.application.dto.salable_component.conventional.product;

import java.math.BigDecimal;

import caio.portfolio.design_pattern.application.dto.salable_component.ResponseSalableComponentDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class ResponseProductDTO extends ResponseSalableComponentDTO {

	private String name;
	private String brand;
	private String model;
	private BigDecimal price;
	private Integer units;
}