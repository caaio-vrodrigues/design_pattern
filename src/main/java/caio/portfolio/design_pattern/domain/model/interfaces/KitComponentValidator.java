package caio.portfolio.design_pattern.domain.model.interfaces;

public interface KitComponentValidator extends SalableComponentValidator {

	String generateAndValidateCode();
}