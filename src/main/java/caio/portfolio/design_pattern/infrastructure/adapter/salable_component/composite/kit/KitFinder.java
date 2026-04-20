package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.composite.kit;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.composite.kit.KitNotFoundException;
import caio.portfolio.design_pattern.domain.model.enums.salable_component.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentFinder;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.composite.kit.KitMessageCreator;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.composite.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.composite.kit.KitRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KitFinder implements SalableComponentFinder<Kit> {

	private final KitRepository repo;
	private final KitMessageCreator kitMessageCreator;

	@Override
	public Kit findSalableComponent(Long id) {
		String entityName = Kit.class.getName();
		String exceptionMsg = kitMessageCreator
			.getEntityNotFoundByIdMsg(entityName, id);
		return repo.findById(id)
			.orElseThrow(() -> new KitNotFoundException(exceptionMsg));
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.KIT;
	}
}