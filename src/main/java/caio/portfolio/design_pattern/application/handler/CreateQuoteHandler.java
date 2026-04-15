package caio.portfolio.design_pattern.application.handler;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caio.portfolio.design_pattern.application.dto.ResponseSalableComponentQuoteDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.quote.CreateSalableComponentQuoteCommand;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.SalableComponentFinder;
import caio.portfolio.design_pattern.domain.model.interfaces.SalableComponentQuoteCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.SalableComponentQuoteValidator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CreateQuoteHandler {
	
	private final Map<
		SalableComponentType, 
		SalableComponentFinder<SalableComponent>> salableComponentFinders;
	
	private final Map<
		SalableComponentType, 
		SalableComponentQuoteValidator<SalableComponent>> salableComponentQuoteValidators;
	
	private final Map<
		SalableComponentType, 
		SalableComponentQuoteCreator<SalableComponent>> salableComponentQuoteCreators;

	@Transactional(readOnly=true)
	public ResponseSalableComponentQuoteDTO getQuote(
		CreateSalableComponentQuoteCommand command
	) {
		BigDecimal totalQuote = command.getRequiredItemQuantityCommandList()
			.stream().map(itemCommand -> {
				Integer quantity = itemCommand.getQuantity();
				SalableComponent item = salableComponentFinders
					.get(itemCommand.getType())
					.findSalableComponent(itemCommand.getRequiredItemId());
				salableComponentQuoteValidators
					.get(itemCommand.getType())
					.validateRequiredItemQuote(item, quantity);
				return salableComponentQuoteCreators
					.get(itemCommand.getType())
					.calculateQuote(item, quantity);
			})
			.reduce(BigDecimal.ZERO, BigDecimal::add);
		return ResponseSalableComponentQuoteDTO.builder()
			.totalQuote(totalQuote)	
			.build();
	}
}