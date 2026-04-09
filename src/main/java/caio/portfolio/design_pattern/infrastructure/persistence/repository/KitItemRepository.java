package caio.portfolio.design_pattern.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.KitItem;

@Repository
public interface KitItemRepository extends JpaRepository<KitItem, Long> {

	boolean existsByKitAndSalableComponent(Kit kit, SalableComponent salableComponent);
	
	@Query("select max(k.id) from KitItem k")
	Long findMaxId();
}