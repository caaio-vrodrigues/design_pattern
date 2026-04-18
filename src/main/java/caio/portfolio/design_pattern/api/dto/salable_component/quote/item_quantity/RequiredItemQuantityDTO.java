package caio.portfolio.design_pattern.api.dto.salable_component.quote.item_quantity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import caio.portfolio.design_pattern.api.dto.salable_component.quote.item_quantity.conventional.product.RequiredProductQuantityDTO;
import caio.portfolio.design_pattern.api.dto.salable_component.quote.item_quantity.conventional.service.RequiredServiceQuantityDTO;
import caio.portfolio.design_pattern.api.dto.salable_component.quote.item_quantity.kit.RequiredKitQuantityDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.quote.item_quantity.RequiredItemQuantityCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(use=Id.NAME, include=As.PROPERTY, property="type")
@JsonSubTypes({
	@Type(value=RequiredProductQuantityDTO.class, name="PRODUCT"),
	@Type(value=RequiredServiceQuantityDTO.class, name="SERVICE"),
	@Type(value=RequiredKitQuantityDTO.class, name="KIT")
})
@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public abstract class RequiredItemQuantityDTO {

	@Positive @NotNull
	private Long requiredItemId;
	
	public abstract RequiredItemQuantityCommand toCommand();
}