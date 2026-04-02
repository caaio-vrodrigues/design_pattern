package caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component;

import java.math.BigDecimal;

import caio.portfolio.design_pattern.domain.model.enums.ServiceCategory;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
@Table(name="service", uniqueConstraints = {
	@UniqueConstraint(columnNames = {
		"name", "category"
	},
	name="UK_service_name_category")
})
@EqualsAndHashCode(callSuper=false, onlyExplicitlyIncluded=true)
@SuperBuilder
@Getter
public class Service extends SalableComponent {

	@Include
	@Column(name="name", nullable=false, updatable=false)
	private String name;
	
	@Include
	@Enumerated(EnumType.STRING)
	@Column(name="category", nullable=false)
	private ServiceCategory category;
	
	@Column(name="price", nullable=false)
	private BigDecimal price;
	
	@Column(name="is_available", nullable=false)
	private Boolean isAvailable;
}