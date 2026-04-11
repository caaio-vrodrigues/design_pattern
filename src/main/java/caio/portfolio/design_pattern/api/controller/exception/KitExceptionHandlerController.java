package caio.portfolio.design_pattern.api.controller.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import caio.portfolio.design_pattern.domain.exception.salable_component.kit.ConcurrentKitException;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.InsufficientKitUnitsException;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.KitAlreadyExistsException;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.KitNotFoundException;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.kit_item.ConcurrentKitItemException;
import caio.portfolio.design_pattern.domain.exception.salable_component.kit.kit_item.KitItemAlreadyExistsException;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class KitExceptionHandlerController {

	private ProblemDetail setProperties(
		ProblemDetail problemDetail, 
		String traceId,
		List<String> errorList
	) {
		problemDetail.setProperty("timestamp", LocalDateTime.now());
		problemDetail.setProperty("traceId", traceId);
		problemDetail.setProperty("errors", errorList);	
		return problemDetail;
	}
	
	private ProblemDetail createProblemDetailAndLog(
		RuntimeException e, Integer status, String title
	) {
		String traceId = UUID.randomUUID().toString();
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
			HttpStatusCode.valueOf(status), 
			e.getMessage()
		);
		problemDetail.setTitle(title);
		log.warn("traceId={} error={}", traceId, e.getMessage());
		return setProperties(
			problemDetail, 
			traceId, 
			List.of(e.getMessage())
		);
	}
	
	@ExceptionHandler(ConcurrentKitException.class)
	public ProblemDetail handleConcurrentKitException(
		ConcurrentKitException e
	) {
		String title = "Falha de concorrência";
		Integer httpStatusCode = 409;
		return createProblemDetailAndLog(e, httpStatusCode, title);
	}
	
	@ExceptionHandler(InsufficientKitUnitsException.class)
	public ProblemDetail handleInsufficientKitUnitsException(
		InsufficientKitUnitsException e
	) {
		String title = "Unidades insuficiente";
		Integer httpStatusCode = 409;
		return createProblemDetailAndLog(e, httpStatusCode, title);
	}
	
	@ExceptionHandler(KitAlreadyExistsException.class)
	public ProblemDetail handleKitAlreadyExistsException(
		KitAlreadyExistsException e
	) {
		String title = "Entidade duplicada";
		Integer httpStatusCode = 409;
		return createProblemDetailAndLog(e, httpStatusCode, title);
	}
	
	@ExceptionHandler(KitNotFoundException.class)
	public ProblemDetail handleKitNotFoundException(
		KitNotFoundException e
	) {
		String title = "Entidade não encontrada";
		Integer httpStatusCode = 404;
		return createProblemDetailAndLog(e, httpStatusCode, title);
	}
	
	@ExceptionHandler(ConcurrentKitItemException.class)
	public ProblemDetail handleConcurrentKitItemException(
		ConcurrentKitItemException e
	) {
		String title = "Falha de concorrência";
		Integer httpStatusCode = 409;
		return createProblemDetailAndLog(e, httpStatusCode, title);
	}
	
	@ExceptionHandler(KitItemAlreadyExistsException.class)
	public ProblemDetail handleKitItemAlreadyExistsException(
		KitItemAlreadyExistsException e
	) {
		String title = "Entidade duplicada";
		Integer httpStatusCode = 409;
		return createProblemDetailAndLog(e, httpStatusCode, title);
	}
}