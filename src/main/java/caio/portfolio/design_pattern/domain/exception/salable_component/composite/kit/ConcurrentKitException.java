package caio.portfolio.design_pattern.domain.exception.salable_component.composite.kit;

public class ConcurrentKitException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConcurrentKitException(String msg) {
		super(msg);
	}
}