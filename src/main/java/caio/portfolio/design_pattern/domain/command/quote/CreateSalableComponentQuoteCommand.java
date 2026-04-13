package caio.portfolio.design_pattern.domain.command.quote;

import java.util.List;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access=AccessLevel.PRIVATE)
@NoArgsConstructor(access=AccessLevel.PROTECTED)
@Builder
@Getter
public class CreateSalableComponentQuoteCommand {
	
	private List<RequiredSalableComponentQuantityCommand> requiredSalableComponentQuantityCommandList;
}