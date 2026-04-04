package caio.portfolio.design_pattern.domain.exception.salable_component.product;

public class ConcurrentProductException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConcurrentProductException(String msg) {
		super(msg);
	}
}