package caio.portfolio.design_pattern.domain.model.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceCategory {

	CONSULTING("CONSULTING"),
	SUPPORT("SUPPORT"),
	TRAINING("TRAINING"),
	IMPLEMENTATION("IMPLEMENTATION"),
	INTEGRATION("INTEGRATION"),
	DEVELOPMENT("DEVELOPMENT");
	
	private final String type;
}