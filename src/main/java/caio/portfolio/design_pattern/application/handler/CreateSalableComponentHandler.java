package caio.portfolio.design_pattern.application.handler;

import java.util.Map;

import org.springframework.stereotype.Service;

import caio.portfolio.design_pattern.application.dto.ResponseSalableComponentDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.CreateSalableComponentCommand;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.ConventionalComponentCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.ConventionalComponentValidator;
import caio.portfolio.design_pattern.domain.model.interfaces.KitComponentCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.KitComponentValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateSalableComponentHandler {
	
	private final Map<
		SalableComponentType, 
		ConventionalComponentValidator<CreateSalableComponentCommand>> salableComponentValidators;
	
	private final Map<
		SalableComponentType, 
		ConventionalComponentCreator<CreateSalableComponentCommand>> salableComponentCreators;
	
	private final KitComponentValidator kitComponentValidator;
	private final KitComponentCreator<CreateSalableComponentCommand> kitComponentCreator;

	@Transactional
	public ResponseSalableComponentDTO createSalableComponent(
		CreateSalableComponentCommand command
	) {
		if(command.getType() == SalableComponentType.KIT) {
			String code = kitComponentValidator.generateAndValidateCode(); 
			return kitComponentCreator.createKit(code, command);
		}		
		salableComponentValidators.get(command.getType())
			.validateSalableComponent(command);
		return salableComponentCreators.get(command.getType())
			.createSalableComponent(command);
	}
}