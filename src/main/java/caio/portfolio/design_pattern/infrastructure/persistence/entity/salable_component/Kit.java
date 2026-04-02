package caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.EqualsAndHashCode.Include;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name="kit", uniqueConstraints = {
	@UniqueConstraint(columnNames = {
		"code"
	},
	name="UK_kit_code")
})
@EqualsAndHashCode(callSuper=false, onlyExplicitlyIncluded=true)
@SuperBuilder
@Getter
public class Kit extends SalableComponent {
	
	@Include
	@Column(name="code", nullable=false, unique=true, updatable=false)
	private String code;
	
	@Column(name="units", nullable=false)
	private Integer units;

	@Override
	public BigDecimal getPrice() {
		// TODO 
		return null;
	}
}