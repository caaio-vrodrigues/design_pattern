package caio.portfolio.design_pattern.api.dto.salable_component;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import caio.portfolio.design_pattern.api.dto.salable_component.conventional.product.CreateProductDTO;
import caio.portfolio.design_pattern.api.dto.salable_component.conventional.service.CreateServiceDTO;
import caio.portfolio.design_pattern.api.dto.salable_component.kit.CreateKitDTO;
import caio.portfolio.design_pattern.domain.command.salable_component.CreateSalableComponentCommand;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(use=Id.NAME, include=As.PROPERTY, property="type")
@JsonSubTypes({
	@Type(value=CreateProductDTO.class, name="PRODUCT"),
	@Type(value=CreateServiceDTO.class, name="SERVICE"),
	@Type(value=CreateKitDTO.class, name="KIT")
})
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public abstract class CreateSalableComponentDTO {

	public abstract CreateSalableComponentCommand toCommand();
}