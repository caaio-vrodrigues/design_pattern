package caio.portfolio.design_pattern.application.handler;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caio.portfolio.design_pattern.application.dto.ResponseSalableComponentQuoteDTO;
import caio.portfolio.design_pattern.domain.command.quote.CreateSalableComponentQuoteCommand;

@Service
public class CreateQuoteHandler {

	@Transactional(readOnly=true)
	public ResponseSalableComponentQuoteDTO getQuote(
		CreateSalableComponentQuoteCommand command
	) {
		// TODO 
		return null;
	}
}