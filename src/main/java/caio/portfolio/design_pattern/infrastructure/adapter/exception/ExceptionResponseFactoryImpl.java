package caio.portfolio.design_pattern.infrastructure.adapter.exception;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.model.interfaces.exception.ExceptionResponseFactory;
import lombok.extern.log4j.Log4j2;

@Log4j2
@Component
public class ExceptionResponseFactoryImpl implements ExceptionResponseFactory {

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
			traceId, 
			List.of(e.getMessage())
		);
	}
}