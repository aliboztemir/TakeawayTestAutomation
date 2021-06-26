package test_resources;

import static io.restassured.RestAssured.given;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import java_resources.ExternalData;
import java_resources.Payloads;

public class TakeAwayReqSpecs {

	private static String baseUri = ExternalData.getGlobalData("baseUri");
	private static ContentType contentType = ContentType.JSON;
	private static DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	private static LocalDateTime time;
	
	public static RequestSpecification takeAwayReqSpec()
	{
		PrintStream log = null;
		try
		{
			log = new PrintStream(new FileOutputStream("logging.txt", true));
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		
		time = LocalDateTime.now();
		log.append("\n******************************************************\n");
		log.append("***   Takeaway Request Sent at: " + dtf.format(time).toString() + "   ***\n");
		log.append("******************************************************\n\n");
		
		// Create a generic RequestSpecification that can be used for all Takeaway API requests (and not just for AddUserAPI)
		RequestSpecification takeAwayReqSpec =	new RequestSpecBuilder()
												.addFilter(RequestLoggingFilter.logRequestTo(log))
												.addFilter(ResponseLoggingFilter.logResponseTo(log))
												.setBaseUri(baseUri)
												.setContentType(contentType)
												.build();
		
		return takeAwayReqSpec;
	}
	public static RequestSpecification loginApiReqSpec(String email, String password)
	{
		RequestSpecification loginApiReqSpec = given().spec(TakeAwayReqSpecs.takeAwayReqSpec()).body(Payloads.loginApiPayload(email, password));
		return loginApiReqSpec;
	}
	
	public static RequestSpecification addTaskReqSpec(String title, String access_token)
	{
		RequestSpecification addTaskReqSpec = given().headers("Authorization", "Bearer " + access_token).spec(TakeAwayReqSpecs.takeAwayReqSpec()).body(Payloads.addTaskPayload(title));
		return addTaskReqSpec;
	}
	
	public static RequestSpecification putTaskReqSpec(String task_id, String title, String access_token)
	{
		RequestSpecification putTaskReqSpec = given().headers("Authorization", "Bearer " + access_token).spec(TakeAwayReqSpecs.takeAwayReqSpec()).body(Payloads.putTaskPayload(title)).pathParam("id", task_id);
		return putTaskReqSpec;
	}
	
	public static RequestSpecification getUserReqSpec(String user_id, String data)
	{
		if (data.equals("invalid")) {
			user_id = "invaliduserid";
		} 
		
		RequestSpecification getUserReqSpec = given().spec(TakeAwayReqSpecs.takeAwayReqSpec().pathParam("id", user_id));
		return getUserReqSpec;
		
	}
	
	public static RequestSpecification getTaskReqSpec(String task_id, String data, String access_token)
	{
		if (data.equals("invalid")) {
			task_id = "invaliduserid";
		} 
		
		RequestSpecification getTaskReqSpec = given().headers("Authorization", "Bearer " + access_token).spec(TakeAwayReqSpecs.takeAwayReqSpec().pathParam("id", task_id));
		return getTaskReqSpec;
		
	}
	
	public static RequestSpecification deleteTaskReqSpec(String task_id, String data, String access_token)
	{
		if (data.equals("invalid")) {
			task_id = "invaliduserid";
		} 
		
		RequestSpecification deleteTaskReqSpec = given().headers("Authorization", "Bearer " + access_token).spec(TakeAwayReqSpecs.takeAwayReqSpec().pathParam("id", task_id));
		return deleteTaskReqSpec;
		
	}
	
	public static RequestSpecification getAllTaskReqSpec(String data, String access_token)
	{
		RequestSpecification getAllTaskReqSpec = given().headers("Authorization", "Bearer " + access_token).spec(TakeAwayReqSpecs.takeAwayReqSpec());
		return getAllTaskReqSpec;
		
	}
}
