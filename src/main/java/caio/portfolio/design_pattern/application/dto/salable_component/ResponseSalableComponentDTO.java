package caio.portfolio.design_pattern.application.dto.salable_component;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public abstract class ResponseSalableComponentDTO {

	private Long id;
}