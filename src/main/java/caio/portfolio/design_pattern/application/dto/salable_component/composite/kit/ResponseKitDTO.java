package caio.portfolio.design_pattern.application.dto.salable_component.composite.kit;

import java.util.List;

import caio.portfolio.design_pattern.application.dto.salable_component.ResponseSalableComponentDTO;
import caio.portfolio.design_pattern.application.dto.salable_component.composite.kit.kit_item.ResponseKitItemDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public class ResponseKitDTO extends ResponseSalableComponentDTO {

	private String code;
	private Integer units;
	private List<ResponseKitItemDTO> respKitItemDTOList;
}