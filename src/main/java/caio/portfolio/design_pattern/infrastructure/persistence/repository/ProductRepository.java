package caio.portfolio.design_pattern.infrastructure.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

	boolean existsByNameAndBrandAndModel(String name, String brand, String model);
}