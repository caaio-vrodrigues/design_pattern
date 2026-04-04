package caio.portfolio.design_pattern.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import caio.portfolio.design_pattern.api.dto.CreateSalableComponentDTO;
import caio.portfolio.design_pattern.application.dto.ResponseSalableComponentDTO;
import caio.portfolio.design_pattern.application.handler.CreateSalableComponentHandler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class SalableComponentController {
	
	private final CreateSalableComponentHandler createSalableComponentHandler;

	@PostMapping
	public ResponseEntity<ResponseSalableComponentDTO> createSalableComponent(
		@Valid @RequestBody CreateSalableComponentDTO dto	
	) {
		return ResponseEntity.status(HttpStatus.CREATED)
			.body(createSalableComponentHandler
					.createSalableComponent(dto.toCommand()));
	}
}