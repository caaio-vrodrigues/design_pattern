package caio.portfolio.design_pattern.api.controller.global.exception;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.log4j.Log4j2;
import tools.jackson.databind.exc.InvalidTypeIdException;

@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class HttpMessageNotReadableExceptionController {

	private ProblemDetail setProperties(
		ProblemDetail problemDetail, String traceId
	) {
		problemDetail.setProperty("timestamp", LocalDateTime.now());
		problemDetail.setProperty("traceId", traceId);
		return problemDetail;
	}

	private ProblemDetail createProblemDetailAndLog(
		RuntimeException e, HttpStatus status, String title, String detail
	) {
		String traceId = UUID.randomUUID().toString();
		ProblemDetail problemDetail = ProblemDetail
			.forStatusAndDetail(status, detail);
		problemDetail.setTitle(title);
		log.warn("traceId={} error={}", traceId, e.getMessage());
		return setProperties(problemDetail, traceId);
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ProblemDetail handleHttpMessageNotReadableException(
		HttpMessageNotReadableException e
	) {
		String title = "Corpo da requisição inválido";
		String detail = "O JSON enviado possui sintaxe inválida. Verifique detalhes.";
		Throwable cause = e.getMostSpecificCause();	
		if(cause instanceof InvalidTypeIdException) {
			title = "Tipo inválido";
			detail = "O campo 'type' deve conter um valor válido para o enum 'SalableComponentType'.";
			return createProblemDetailAndLog(e, HttpStatus.BAD_REQUEST, title, detail);
		}	
		return createProblemDetailAndLog(e, HttpStatus.BAD_REQUEST, title, detail);
	}
}