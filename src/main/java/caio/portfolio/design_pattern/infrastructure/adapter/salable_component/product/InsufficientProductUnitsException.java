package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.product;

public class InsufficientProductUnitsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public InsufficientProductUnitsException(String msg) {
		super(msg);
	}
}