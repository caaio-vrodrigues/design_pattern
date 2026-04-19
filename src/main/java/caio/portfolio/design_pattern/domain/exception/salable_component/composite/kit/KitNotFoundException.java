package caio.portfolio.design_pattern.domain.exception.salable_component.composite.kit;

public class KitNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public KitNotFoundException(String msg) {
		super(msg);
	}
}