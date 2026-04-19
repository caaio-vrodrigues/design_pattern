package caio.portfolio.design_pattern.domain.exception.salable_component.composite.kit.kit_item;

public class ConcurrentKitItemException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ConcurrentKitItemException(String msg) {
		super(msg);
	}
}