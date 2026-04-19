package caio.portfolio.design_pattern.domain.exception.salable_component.composite.kit;

public class KitAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public KitAlreadyExistsException(String msg) {
		super(msg);
	}
}