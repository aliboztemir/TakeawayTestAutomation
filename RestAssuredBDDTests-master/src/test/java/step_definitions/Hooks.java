package step_definitions;

import io.cucumber.java.Before;

public class Hooks {

	@Before("not (@Smoke and @Stress)")
	public void addPlaceStub()
	{
		TakeAwayStepDefinition stepDefs = new TakeAwayStepDefinition();
		
		if (TakeAwayStepDefinition.getAccessToken().isEmpty())
		{
			stepDefs.post_a_valid_token_with_login_api_with_data("test@test.com", "4nak1n");
		}
	}
}
