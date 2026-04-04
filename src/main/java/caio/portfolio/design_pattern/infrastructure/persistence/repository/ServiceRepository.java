package caio.portfolio.design_pattern.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import caio.portfolio.design_pattern.domain.model.enums.ServiceCategory;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.Service;

@Repository
public interface ServiceRepository extends JpaRepository<Service, Long> {
	
	boolean existsByNameAndCategory(String name, ServiceCategory category);
}