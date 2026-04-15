package caio.portfolio.design_pattern.domain.exception.salable_component.conventional.service;

public class UnavailableServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UnavailableServiceException(String msg) {
		super(msg);
	}
}