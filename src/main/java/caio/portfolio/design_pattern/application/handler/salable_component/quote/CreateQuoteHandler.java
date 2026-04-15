package caio.portfolio.design_pattern.application.handler.salable_component.quote;

import java.math.BigDecimal;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import caio.portfolio.design_pattern.application.dto.salable_component.quote.ResponseSalableComponentQuoteDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.quote.CreateSalableComponentQuoteCommand;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentFinder;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.quote.SalableComponentQuoteCreator;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.quote.SalableComponentQuoteValidator;
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