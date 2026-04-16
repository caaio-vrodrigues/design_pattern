package caio.portfolio.design_pattern.api.controller.global.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.log4j.Log4j2;

@Log4j2
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class MethodArgumentExceptionHandlerController {
	
	private ProblemDetail setProperties(
		ProblemDetail problemDetail, String traceId, List<String> errorList
	) {
		problemDetail.setProperty("timestamp", LocalDateTime.now());
		problemDetail.setProperty("traceId", traceId);
		problemDetail.setProperty("errors", errorList);
		return problemDetail;
	}
	
	private List<String> getBindingResultList(List<FieldError> fieldErrors){
		return fieldErrors.stream().map(error -> {					
			String msg = switch(error.getCode()) {
				case "NotBlank" -> "não pode ser vazio";
				case "NotNull" -> "não pode ser nulo";
				case "NotEmpty" -> "não pode ser vazia";
				case "Positive" -> "deve ser maior que zero";
				default -> error.getDefaultMessage();
			};
			String msgList = "A lista: `"+error.getField()+"` "+msg+".";
			String msgField = "O campo: `"+error.getField()+"` "+msg+".";
			return error.getCode().equals("NotEmpty") ? msgList : msgField;
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
		ProblemDetail problemDetail = ProblemDetail
			.forStatus(HttpStatusCode.valueOf(400));
		problemDetail.setTitle("Argumento inválido");	
		log.warn("traceId={} fieldErrors={}", traceId, fieldErrors);
		return setProperties(problemDetail, traceId, errorList);
	}
}