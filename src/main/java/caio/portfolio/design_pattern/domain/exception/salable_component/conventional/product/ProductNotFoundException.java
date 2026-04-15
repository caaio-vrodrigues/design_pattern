package caio.portfolio.design_pattern.domain.exception.salable_component.conventional.product;

public class ProductNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ProductNotFoundException(String msg) {
		super(msg);
	}
}