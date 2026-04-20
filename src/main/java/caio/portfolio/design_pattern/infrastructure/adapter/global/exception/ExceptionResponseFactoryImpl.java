package caio.portfolio.design_pattern.infrastructure.adapter.global.exception;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.model.interfaces.global.exception.ExceptionResponseFactory;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ExceptionResponseFactoryImpl implements ExceptionResponseFactory {

	private ProblemDetail setProperties(
		ProblemDetail problemDetail, 
		String traceId
	) {
		problemDetail.setProperty("timestamp", LocalDateTime.now());
		problemDetail.setProperty("traceId", traceId);
		return problemDetail;
	}
		
	@Override
	public ProblemDetail createProblemDetailAndLog(
		RuntimeException e, HttpStatus status, String title
	) {
		String traceId = UUID.randomUUID().toString();
		ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(
			status, 
			e.getMessage()
		);
		problemDetail.setTitle(title);
		log.warn("traceId={} error={}", traceId, e.getMessage());
		return setProperties(
			problemDetail, 
			traceId
		);
	}
}