package caio.portfolio.design_pattern.domain.model.interfaces.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public interface ExceptionResponseFactory {

	ProblemDetail createProblemDetailAndLog(RuntimeException e, HttpStatus status, String title);
}