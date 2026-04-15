package caio.portfolio.design_pattern.infrastructure.adapter.salable_component.kit;

import org.springframework.stereotype.Component;

import caio.portfolio.design_pattern.domain.exception.salable_component.kit.KitNotFoundException;
import caio.portfolio.design_pattern.domain.model.enums.SalableComponentType;
import caio.portfolio.design_pattern.domain.model.interfaces.salable_component.SalableComponentFinder;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.kit.KitRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class KitFinder implements SalableComponentFinder<Kit> {

	private final KitRepository repo;

	@Override
	public Kit findSalableComponent(Long id) {
		return repo.findById(id).orElseThrow(() -> 
			new KitNotFoundException("Não foi possível encontra `Kit` para o id: `"+id+"`."));
	}

	@Override
	public SalableComponentType getType() {
		return SalableComponentType.KIT;
	}
}