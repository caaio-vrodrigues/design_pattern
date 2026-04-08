package caio.portfolio.design_pattern.domain.exception.salable_component.kit;

public class InsufficientKitUnitsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InsufficientKitUnitsException(String msg) {
		super(msg);
	}
}