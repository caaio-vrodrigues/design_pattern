package caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit;

import java.math.BigDecimal;
import java.util.List;
import java.util.ArrayList;

import caio.portfolio.design_pattern.domain.exception.salable_component.kit.InsufficientKitUnitsException;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder.Default;
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
@SuperBuilder(toBuilder=true)
@Getter
public class Kit extends SalableComponent {
	
	@Include
	@Column(name="code", nullable=false, unique=true, updatable=false)
	private String code;
	
	@Column(name="units", nullable=false)
	private Integer units;
	
	@Default
	@OneToMany(
		mappedBy="kit", fetch=FetchType.LAZY, cascade=CascadeType.ALL, orphanRemoval=true)
	private List<KitItem> kitItemList = new ArrayList<>();

	@Override
	public BigDecimal getPrice() {
		// TODO 
		return null;
	}
	
	public void decreaseUnits(Integer requiredQuantity) {
		if(requiredQuantity > units)
			throw new InsufficientKitUnitsException("Unidades de `Kit` insuficiente para realizar a operação. Unidades disponíveis: `"+units+"`.");
		units -= requiredQuantity;
	}
}