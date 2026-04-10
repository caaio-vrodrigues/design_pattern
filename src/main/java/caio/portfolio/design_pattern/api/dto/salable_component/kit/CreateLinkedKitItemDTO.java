package caio.portfolio.design_pattern.api.dto.salable_component.kit;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import caio.portfolio.design_pattern.domain.command.salable_component.kit.CreateLinkedKitItemCommand;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonTypeInfo(use=Id.NAME, include=As.PROPERTY, property="type")
@JsonSubTypes({
	@Type(value=CreateLinkedKitProductDTO.class, name="PRODUCT"),
	@Type(value=CreateLinkedKitServiceDTO.class, name="SERVICE"),
	@Type(value=CreateLinkedKitKitDTO.class, name="KIT")
})
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@SuperBuilder
@Getter
public abstract class CreateLinkedKitItemDTO {
	
	@Positive @NotNull
	private Long salableComponentId;
	
	public abstract CreateLinkedKitItemCommand toCommand();
}