package caio.portfolio.design_pattern.infrastructure.persistence.repository.salable_component.conventional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsByNameAndBrandAndModel(String name, String brand, String model);
}