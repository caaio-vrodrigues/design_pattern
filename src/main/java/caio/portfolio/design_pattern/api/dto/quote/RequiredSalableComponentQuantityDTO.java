package caio.portfolio.design_pattern.api.dto.quote;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.annotation.JsonTypeInfo.Id;

import caio.portfolio.design_pattern.domain.command.quote.RequiredSalableComponentQuantityCommand;
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
public abstract class RequiredSalableComponentQuantityDTO {

	@Positive @NotNull
	private Long requiredSalableComponentId;
	
	public abstract RequiredSalableComponentQuantityCommand toCommand();
}