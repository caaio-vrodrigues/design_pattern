package caio.portfolio.design_pattern.domain.model.interfaces.salable_component.message;

public interface SalableComponentMessageCreator {

	String getConcurrentEntityMsg(String entityName);
	String getNotFoundEntityById(String entityName, Long id);
}