package caio.portfolio.design_pattern.application.dto.salable_component.quote;

import java.math.BigDecimal;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Builder
@Getter
public class ResponseSalableComponentQuoteDTO {

	private BigDecimal totalQuote;
}