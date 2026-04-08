package caio.portfolio.design_pattern.domain.exception.salable_component.product;

public class InsufficientProductUnitsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InsufficientProductUnitsException(String msg) {
		super(msg);
	}
}