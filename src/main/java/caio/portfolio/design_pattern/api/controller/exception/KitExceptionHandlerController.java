package caio.portfolio.design_pattern.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import caio.portfolio.design_pattern.domain.exception.salable_component.kit.ConcurrentKitException;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.InsufficientKitUnitsException;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.KitAlreadyExistsException;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.KitNotFoundException;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.kit_item.ConcurrentKitItemException;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.kit_item.KitItemAlreadyExistsException;
import caio.portfolio.design_pattern.domain.model.interfaces.exception.ExceptionResponseFactory;
import lombok.RequiredArgsConstructor;

@RestControllerAdvice
@RequiredArgsConstructor
public class KitExceptionHandlerController {

	private final ExceptionResponseFactory exceptionResponseFactory;
	
	@ExceptionHandler(ConcurrentKitException.class)
	public ProblemDetail handleConcurrentKitException(
		ConcurrentKitException e
	) {
		String title = "Falha de concorrência";
		return exceptionResponseFactory
			.createProblemDetailAndLog(e, HttpStatus.CONFLICT, title);
	}
	
	@ExceptionHandler(InsufficientKitUnitsException.class)
	public ProblemDetail handleInsufficientKitUnitsException(
		InsufficientKitUnitsException e
	) {
		String title = "Unidades insuficiente";
		return exceptionResponseFactory
			.createProblemDetailAndLog(e, HttpStatus.CONFLICT, title);
	}
	
	@ExceptionHandler(KitAlreadyExistsException.class)
	public ProblemDetail handleKitAlreadyExistsException(
		KitAlreadyExistsException e
	) {
		String title = "Entidade duplicada";
		return exceptionResponseFactory
			.createProblemDetailAndLog(e, HttpStatus.CONFLICT, title);
	}
	
	@ExceptionHandler(KitNotFoundException.class)
	public ProblemDetail handleKitNotFoundException(
		KitNotFoundException e
	) {
		String title = "Entidade não encontrada";
		return exceptionResponseFactory
			.createProblemDetailAndLog(e, HttpStatus.NOT_FOUND, title);
	}
	
	@ExceptionHandler(ConcurrentKitItemException.class)
	public ProblemDetail handleConcurrentKitItemException(
		ConcurrentKitItemException e
	) {
		String title = "Falha de concorrência";
		return exceptionResponseFactory
			.createProblemDetailAndLog(e, HttpStatus.CONFLICT, title);
	}
	
	@ExceptionHandler(KitItemAlreadyExistsException.class)
	public ProblemDetail handleKitItemAlreadyExistsException(
		KitItemAlreadyExistsException e
	) {
		String title = "Entidade duplicada";
		return exceptionResponseFactory
			.createProblemDetailAndLog(e, HttpStatus.CONFLICT, title);
	}
}