package caio.portfolio.design_pattern.api.controller.global.exception;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Order(Ordered.LOWEST_PRECEDENCE)
@RestControllerAdvice
public class UnexpectedExceptionHandler {
	
	private ProblemDetail setProperties(
		ProblemDetail problemDetail, String traceId
	) {
		problemDetail.setProperty("timestamp", LocalDateTime.now());
		problemDetail.setProperty("traceId", traceId);
		return problemDetail;
	}
		
	private ProblemDetail createProblemDetailAndLog(
		RuntimeException e, HttpStatus status, String title
	) {
		String traceId = UUID.randomUUID().toString();
		String detail = "Falha interna, acione o suporte.";
		ProblemDetail problemDetail = ProblemDetail
			.forStatusAndDetail(status, detail);
		problemDetail.setTitle(title);
		log.warn("traceId={} error={}", traceId, e.getMessage());
		return setProperties(problemDetail, traceId);
	}
	
	@ExceptionHandler(RuntimeException.class)
	public ProblemDetail handleRuntimeException(RuntimeException e) {
		String title = "Falha inesperada";
		return createProblemDetailAndLog(e, HttpStatus.INTERNAL_SERVER_ERROR, title);
	}
}