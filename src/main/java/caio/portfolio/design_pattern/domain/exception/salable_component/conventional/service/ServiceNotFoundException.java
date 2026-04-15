package caio.portfolio.design_pattern.domain.exception.salable_component.conventional.service;

public class ServiceNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ServiceNotFoundException(String msg) {
		super(msg);
	}
}