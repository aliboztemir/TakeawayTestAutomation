package step_definitions;

import static org.junit.Assert.*;
import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import test_resources.Utilities;
import test_resources.TakeAwayReqSpecs;
import test_resources.TakeAwayRespSpecs;

public class TakeAwayStepDefinition {

	private RequestSpecification takeAwayReqSpec = null;
	private Response takeAwayResponse = null;
	private static String task_id = "";
	private static String access_token = "";
	
	
	
	public static String getTaskId() {
		return task_id;
	}
	
	public static String getAccessToken() {
		return access_token;
	}
	
	
	@When("{word} request is sent as {word} HTTP request")
	public void request_is_sent_as_http_request(String api, String requestType) {
		takeAwayResponse = Utilities.getResource(takeAwayReqSpec, api, requestType);
		
		if (api.equalsIgnoreCase("LoginAPI")) {
			access_token = Utilities.getResponseValue(takeAwayResponse, "access_token");
		}
		if (api.equalsIgnoreCase("AddTaskAPI")) {
			task_id = Utilities.getResponseValue(takeAwayResponse, "data.id");
		}
	}

	@Then("success/fail response is sent back with status code {int}")
	public void response_is_sent_back_with_status_code(int code) {
		// Then() part of request
		takeAwayResponse = TakeAwayRespSpecs.applyRespSpec(takeAwayResponse, code);
	}

	@Then("{word} value in response is {string}")
	public void value_in_response_is(String data, String expectedValue) {
		// Retrieve actual value from response
		String actualValue = Utilities.getResponseValue(takeAwayResponse, data);

		// Check actual data value matches expected data value in response
		assertEquals(actualValue, expectedValue);
	}

	@Then("place_id maps to {word} {string} using GetPlaceAPI")
	public void place_id_maps_to_data_using_get_place_api(String data, String expectedValue) {
		// Get data from GetUserAPI response
		String actualValue = Utilities.getResponseValue(takeAwayResponse, data);

		// Check actual data value matches expected data value in response
		assertEquals(actualValue, expectedValue);
	}

	@Then("length of {word} value in response must be greater than zero")
	public void length_of_id_value_in_response_must_be_greater_than_zero(String data) {
		// Get data from GetUserAPI response
		String actualValue = Utilities.getResponseValue(takeAwayResponse, data);
		assertTrue(actualValue.length() > 0);
	}
	
	@Then("response time must be less than {int} ms")
	public void response_time_must_be_less_than_ms(Integer responseTime) {
		long actualValue = TakeAwayRespSpecs.applyRespTimeSpec(takeAwayResponse);
		assertTrue(actualValue < responseTime);
	}
	
	@Given("Post a valid token with LoginAPI with data {string} {string}")
	public void post_a_valid_token_with_login_api_with_data(String email, String password) {
		takeAwayReqSpec = TakeAwayReqSpecs.loginApiReqSpec(email, password);
	}
	
	@Given("a valid AddTaskAPI payload with data {string}")
	public void a_valid_add_task_api_payload_with_data(String title) {
		takeAwayReqSpec = TakeAwayReqSpecs.addTaskReqSpec(title, access_token);
	}
	
	@Given("a {word} GetTaskAPI request")
	public void a_valid_get_task_api_request(String data) {
		takeAwayReqSpec = TakeAwayReqSpecs.getTaskReqSpec(task_id, data, access_token);
	}
	
	@Given("a {word} GetAllTaskAPI request")
	public void a_valid_get_all_task_api_request(String data) {
		takeAwayReqSpec = TakeAwayReqSpecs.getAllTaskReqSpec(data, access_token);
	}
	
	@Then("Validate that the {word} of the last created Task is in the list")
	public void validate_that_the_id_of_the_last_created_task_is_in_the_list(String data) {
		// Retrieve actual value from response
		Assert.assertTrue(Utilities.extractValueInResponseItemList(takeAwayResponse, data));
	}
	
	@Given("a valid PutTaskAPI payload with data {string}")
	public void a_valid_put_task_api_payload_with_data(String newTitle) {
		takeAwayReqSpec = TakeAwayReqSpecs.putTaskReqSpec(task_id, newTitle, access_token);
	}
	
	@Given("a {word} DeleteTaskAPI request")
	public void a_valid_delete_task_apı_request(String data) {
		// Given() part of DeleteUserAPI request
		takeAwayReqSpec = TakeAwayReqSpecs.deleteTaskReqSpec(task_id, data, access_token);
	}
	
}
