package caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.conventional;

import java.math.BigDecimal;

import caio.portfolio.design_pattern.domain.exception.salable_component.conventional.product.InsufficientProductUnitsException;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name="product", uniqueConstraints = {
	@UniqueConstraint(columnNames = {
		"name", "brand", "model"
	},
	name="UK_product_name_brand_model")
})
@EqualsAndHashCode(callSuper=false, onlyExplicitlyIncluded=true)
@SuperBuilder
@Getter
public class Product extends SalableComponent {
	
	@Include
	@Column(name="name", nullable=false, updatable=false)
	private String name;
	
	@Include
	@Column(name="brand", nullable=false, updatable=false)
	private String brand;
	
	@Include
	@Column(name="model", nullable=false, updatable=false)
	private String model;
	
	@Column(name="price", nullable=false)
	private BigDecimal price;
	
	@Column(name="units", nullable=false)
	private Integer units;
	
	public void decreaseUnits(Integer requiredQuantity) {
		if(requiredQuantity > units)
			throw new InsufficientProductUnitsException("Unidades de `Product` insuficiente para realizar a operação. Unidades disponíveis: `"+units+"`.");
		units -= requiredQuantity;
	}
}