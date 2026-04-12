package caio.portfolio.design_pattern.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import caio.portfolio.design_pattern.domain.exception.salable_component.product.ConcurrentProductException;
import caio.portfolio.design_pattern.domain.exception.salable_component.product.InsufficientProductUnitsException;
import caio.portfolio.design_pattern.domain.exception.salable_component.product.ProductAlreadyExistsException;
import caio.portfolio.design_pattern.domain.exception.salable_component.product.ProductNotFoundException;
import caio.portfolio.design_pattern.domain.model.interfaces.exception.ExceptionResponseFactory;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class ProductExceptionHandlerController {

	private final ExceptionResponseFactory exceptionResponseFactory;
	
	@ExceptionHandler(ConcurrentProductException.class)
	public ProblemDetail handleConcurrentProductException(
		ConcurrentProductException e
	) {
		String title = "Falha de concorrência";
		return exceptionResponseFactory
			.createProblemDetailAndLog(e, HttpStatus.CONFLICT, title);
	}
	
	@ExceptionHandler(InsufficientProductUnitsException.class)
	public ProblemDetail handleInsufficientProductUnitsException(
		InsufficientProductUnitsException e
	) {
		String title = "Unidades insuficiente";
		return exceptionResponseFactory
			.createProblemDetailAndLog(e, HttpStatus.CONFLICT, title);
	}
	
	@ExceptionHandler(ProductAlreadyExistsException.class)
	public ProblemDetail handleProductAlreadyExistsException(
		ProductAlreadyExistsException e
	) {
		String title = "Entidade duplicada";
		return exceptionResponseFactory
			.createProblemDetailAndLog(e, HttpStatus.CONFLICT, title);
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ProblemDetail handleProductNotFoundException(
		ProductNotFoundException e
	) {
		String title = "Entidade não encontrada";
		return exceptionResponseFactory
			.createProblemDetailAndLog(e, HttpStatus.NOT_FOUND, title);
	}
}