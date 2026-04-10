package caio.portfolio.design_pattern.api.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.log4j.Log4j2;

@Log4j2
@RestControllerAdvice
public class ExceptionHandlerController {
	
	private ProblemDetail setProperties(ProblemDetail problemDetail, String traceId) {
		problemDetail.setProperty("timestamp", LocalDateTime.now());
		problemDetail.setProperty("traceId", traceId);
		return problemDetail;
	}
	
	private List<String> getBindingResultList(List<FieldError> fieldErrors){
		return fieldErrors.stream().map(error -> {
			String field = error.getField();			
			String msg = switch(error.getCode()) {
				case "NotBlank" -> "não pode ser vazio";
				case "NotNull" -> "não pode ser nulo";
				case "Positive" -> "deve ser maior que zero";
				default -> error.getDefaultMessage();
			};			
			return "O campo: `"+field+"` "+msg+".";
		})
		.toList();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ProblemDetail handleMethodArgumentNotValidException(
		MethodArgumentNotValidException e
	) {
		String traceId = UUID.randomUUID().toString();
		List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
		List<String> errorList = getBindingResultList(fieldErrors);
		ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatusCode.valueOf(400));
		problemDetail.setTitle("Argumento inválido");	
		problemDetail.setProperty("errors", errorList);
		log.warn("traceId={} fieldErrors={}", traceId, fieldErrors);
		return setProperties(problemDetail, traceId);
	}
}