package caio.portfolio.design_pattern.domain.exception.salable_component.conventional.product;

public class ProductAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductAlreadyExistsException(String msg) {
		super(msg);
	}
}