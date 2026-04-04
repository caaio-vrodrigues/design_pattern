package caio.portfolio.design_pattern.application.handler;

import java.util.Map;

import org.springframework.stereotype.Service;

import caio.portfolio.design_pattern.application.dto.ResponseSalableComponentDTO;
import caio.portfolio.design_pattern.domain.model.command.salable_component.CreateSalableComponentCommand;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.SalableComponentCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.SalableComponentValidator;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateSalableComponentHandler {
	
	private final Map<
		SalableComponentType, 
		SalableComponentValidator<CreateSalableComponentCommand>> salableComponentValidators;
	
	private final Map<
		SalableComponentType, 
		SalableComponentCreator<CreateSalableComponentCommand>> salableComponentCreators;

	@Transactional
	public ResponseSalableComponentDTO createSalableComponent(
		CreateSalableComponentCommand command
	) {
		salableComponentValidators.get(command.getType())
			.validateSalableComponent(command);
		return salableComponentCreators.get(command.getType())
			.createSalableComponent(command);
	}
}