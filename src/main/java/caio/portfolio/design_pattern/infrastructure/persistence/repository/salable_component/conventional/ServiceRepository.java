package caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.conventional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import caio.portfolio.design_pattern.domain.model.enums.salable_component.conventional.service.ServiceCategory;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
	
	boolean existsByNameAndCategory(String name, ServiceCategory category);
}