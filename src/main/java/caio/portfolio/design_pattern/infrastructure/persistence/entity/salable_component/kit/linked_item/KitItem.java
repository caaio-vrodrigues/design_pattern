package caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.linked_item;

import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.SalableComponent;
import caio.portfolio.design_pattern.infrastructure.persistence.entity.salable_component.kit.Kit;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.persistence.UniqueConstraint;
import jakarta.persistence.Version;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Entity
@Table(name="kit_item", uniqueConstraints = {
	@UniqueConstraint(columnNames = {
		"kit_id", "salable_component_id"
	},
	name="UK_kit_item_kit_id_salable_component_id")
})
@EqualsAndHashCode(onlyExplicitlyIncluded=true)
@Builder
@Getter
public class KitItem {

	@Version
	private Long version;
	
	@Include
	@Transient
	private String code;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="kit_id", nullable=false, updatable=false)
	private Kit kit;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="salable_component_id", nullable=false, updatable=false)
	private SalableComponent salableComponent;
	
	@Column(name="salable_component_quantity", nullable=false)
	private Integer salableComponentQuantity;
}