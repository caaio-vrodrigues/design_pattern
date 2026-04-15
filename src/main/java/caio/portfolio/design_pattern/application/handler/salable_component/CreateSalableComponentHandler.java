package caio.portfolio.design_pattern.application.handler.salable_component;

import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caio.portfolio.design_pattern.application.dto.salable_component.ResponseSalableComponentDTO;
import caio.portfolio.design_pattern.application.dto.salable_component.kit.ResponseKitDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.CreateSalableComponentCommand;
import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateKitCommand;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.ConventionalComponentCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.conventional.ConventionalComponentValidator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.kit.KitComponentCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.kit.KitComponentValidator;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateSalableComponentHandler {
	
	private final Map<
		SalableComponentType, 
		ConventionalComponentValidator<
			CreateSalableComponentCommand>> salableComponentValidators;
	
	private final Map<
		SalableComponentType, 
		ConventionalComponentCreator<
			CreateSalableComponentCommand, 
			ResponseSalableComponentDTO>> salableComponentCreators;
	
	private final KitComponentValidator kitComponentValidator;
	private final KitComponentCreator kitComponentCreator;
	
	private ResponseKitDTO resolveKit(CreateKitCommand command) {
		String code = kitComponentValidator.generateAndValidateCode(); 
		return kitComponentCreator.createKit(code, command);
	}

	@Transactional
	public ResponseSalableComponentDTO createSalableComponent(
		CreateSalableComponentCommand command
	) {
		if(command.getType() == SalableComponentType.KIT)
			return resolveKit((CreateKitCommand) command);
		salableComponentValidators.get(command.getType())
			.validateSalableComponent(command);
		return salableComponentCreators.get(command.getType())
			.createSalableComponent(command);
	}
}