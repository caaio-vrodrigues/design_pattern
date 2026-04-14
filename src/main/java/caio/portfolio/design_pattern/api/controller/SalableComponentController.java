package caio.portfolio.design_pattern.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import caio.portfolio.design_pattern.api.dto.salable_component.CreateSalableComponentDTO;
import caio.portfolio.design_pattern.api.dto.salable_component.quote.CreateSalableComponentQuoteDTO;
import caio.portfolio.design_pattern.application.dto.ResponseSalableComponentDTO;
import caio.portfolio.design_pattern.application.dto.ResponseSalableComponentQuoteDTO;
import caio.portfolio.design_pattern.application.handler.CreateQuoteHandler;
import caio.portfolio.design_pattern.application.handler.CreateSalableComponentHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/salable-component")
public class SalableComponentController {
	
	private final CreateSalableComponentHandler createSalableComponentHandler;
	private final CreateQuoteHandler createQuoteHandler;

	@PostMapping("/create")
	public ResponseEntity<ResponseSalableComponentDTO> createSalableComponent(
		@Valid @RequestBody CreateSalableComponentDTO dto	
	) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(createSalableComponentHandler
					.createSalableComponent(dto.toCommand()));
	}
	
	@PostMapping("/quote")
	public ResponseEntity<ResponseSalableComponentQuoteDTO> getSalableComponentQuote(
		@Valid @RequestBody CreateSalableComponentQuoteDTO dto
	) {
		return ResponseEntity.ok(createQuoteHandler.getQuote(dto.toCommand()));
	}
}