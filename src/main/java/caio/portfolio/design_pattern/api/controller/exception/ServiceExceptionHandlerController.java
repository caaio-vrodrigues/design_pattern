package caio.portfolio.design_pattern.api.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import caio.portfolio.design_pattern.domain.exception.salable_component.service.ConcurrentServiceException;
import caio.portfolio.design_pattern.domain.exception.salable_component.service.ServiceAlreadyExistsException;
import caio.portfolio.design_pattern.domain.exception.salable_component.service.ServiceNotFoundException;
import caio.portfolio.design_pattern.domain.exception.salable_component.service.UnavailableServiceException;
import caio.portfolio.design_pattern.domain.model.interfaces.exception.ExceptionResponseFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
@RequiredArgsConstructor
public class ServiceExceptionHandlerController {
	
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