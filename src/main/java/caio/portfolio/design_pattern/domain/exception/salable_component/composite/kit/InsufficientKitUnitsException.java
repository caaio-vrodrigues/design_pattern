package caio.portfolio.design_pattern.domain.exception.salable_component.composite.kit;

public class InsufficientKitUnitsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InsufficientKitUnitsException(String msg) {
		super(msg);
	}
}