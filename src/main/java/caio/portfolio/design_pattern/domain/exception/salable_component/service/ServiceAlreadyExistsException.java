package caio.portfolio.design_pattern.domain.exception.salable_component.service;

public class ServiceAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceAlreadyExistsException(String msg) {
		super(msg);
	}
}