package caio.portfolio.design_pattern.application.handler;

import org.springframework.stereotype.Service;

import caio.portfolio.design_pattern.application.dto.ResponseSalableComponentDTO;
import caio.portfolio.design_pattern.domain.model.command.CreateSalableComponentCommand;
import jakarta.transaction.Transactional;

@Service
public class CreateSalableComponentHandler {

	@Transactional
	public ResponseSalableComponentDTO createSalableComponent(
		CreateSalableComponentCommand command
	) {
		// to-do
		return null;
	}
}