package caio.portfolio.design_pattern.application.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Builder
@Getter
public class ResponseKitItemDTO {

	private Long id;
	private Long kitId;
	private Long salableComponentId;
	private Integer salableComponentQuantity;
}