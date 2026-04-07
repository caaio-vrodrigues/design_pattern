package caio.portfolio.design_pattern.domain.exception.salable_component.kit.kit_item;

public class KitItemAlreadyExistsException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public KitItemAlreadyExistsException(String msg) {
		super(msg);
	}
}