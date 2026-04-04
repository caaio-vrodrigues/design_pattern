package caio.portfolio.design_pattern.application.dto;

import java.math.BigDecimal;

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