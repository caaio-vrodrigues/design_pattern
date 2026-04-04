package caio.portfolio.design_pattern.domain.exception.salable_component.service;

public class ConcurrentServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConcurrentServiceException(String msg) {
		super(msg);
	}
}