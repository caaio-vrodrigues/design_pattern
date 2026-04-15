package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.kit;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.kit.KitAlreadyExistsException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.kit.KitComponentValidator;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.kit.KitRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KitValidator implements KitComponentValidator {
	
	private final KitRepository repo;
	
	private String generateCode() {
		Long maxId = repo.findMaxId();
		Long codeNumber = maxId == null ? 0 : maxId;
		return String.valueOf(codeNumber + 1);
	}
	
	private void validateCode(String code) {
		if(repo.existsByCode(code))
			throw new KitAlreadyExistsException("Falha de duplicidade ao tentar criar `Kit`: [code: `"+code+"`].");
	}

	@Override
	public String generateAndValidateCode() {
		String code = generateCode();
		validateCode(code);
		return code;
	}
	
	@Override
	public SalableComponentType getType() {
		return SalableComponentType.KIT;
	}
}