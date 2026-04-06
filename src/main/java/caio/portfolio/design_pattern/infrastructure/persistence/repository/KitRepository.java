package caio.portfolio.design_pattern.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;

@Repository
public interface KitRepository extends JpaRepository<Kit, Long> {

	boolean existsByCode(String code);
	
	@Query("select max(k.id) from Kit k")
	Long findMaxId();
}