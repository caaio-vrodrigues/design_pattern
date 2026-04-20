package caio.portfolio.design_pattern.api.controller.salable_component.conventional.service.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.service.ConcurrentServiceException;
import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.service.ServiceAlreadyExistsException;
import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.service.ServiceNotFoundException;
import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.service.UnavailableServiceException;
import caio.portfolio.design_pattern.domain.model.interfaces.global.exception.ExceptionResponseFactory;
import lombok.RequiredArgsConstructor;

@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
@RequiredArgsConstructor
public class ServiceExceptionHandler {
	
	private final ExceptionResponseFactory exceptionResponseFactory;

	@ExceptionHandler(ConcurrentServiceException.class)
	public ProblemDetail handleConcurrentServiceException(
		ConcurrentServiceException e
	) {
		String title = "Falha de concorrência";
		return exceptionResponseFactory
			.createProblemDetailAndLog(e, HttpStatus.CONFLICT, title);
	}
	
	@ExceptionHandler(ServiceAlreadyExistsException.class)
	public ProblemDetail handleServiceAlreadyExistsException(
		ServiceAlreadyExistsException e
	) {
		String title = "Entidade duplicada";
		return exceptionResponseFactory
			.createProblemDetailAndLog(e, HttpStatus.CONFLICT, title);
	}
	
	@ExceptionHandler(ServiceNotFoundException.class)
	public ProblemDetail handleServiceNotFoundException(
		ServiceNotFoundException e
	) {
		String title = "Entidade não encontrada";
		return exceptionResponseFactory
			.createProblemDetailAndLog(e, HttpStatus.NOT_FOUND, title);
	}
	
	@ExceptionHandler(UnavailableServiceException.class)
	public ProblemDetail handleUnavailableServiceException(
		UnavailableServiceException e
	) {
		String title = "Serviço indisponível";
		return exceptionResponseFactory
			.createProblemDetailAndLog(e, HttpStatus.SERVICE_UNAVAILABLE, title);
	}
}