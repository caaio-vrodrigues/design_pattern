package caio.portfolio.design_pattern.domain.model.enums.salable_component;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum SalableComponentType {

	PRODUCT("PRODUCT"),
	SERVICE("SERVICE"),
	KIT("KIT");
	
	private final String type;
}