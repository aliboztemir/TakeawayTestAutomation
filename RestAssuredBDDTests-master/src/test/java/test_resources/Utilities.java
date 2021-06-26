package test_resources;

import java.util.List;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java_resources.APIResources;

public class Utilities {

	public static Response getResource(RequestSpecification reqSpec, String api, String requestType)
	{
		// Constructor will be called with the value of the resource you pass
		APIResources resource = APIResources.valueOf(api);
		Response taskResponse = null;
		
		switch (requestType.toLowerCase())
		{
			case "post":
				taskResponse = reqSpec.when().post(resource.getResource());
				break;
			case "get":
				taskResponse = reqSpec.when().get(resource.getResource());
				break;
			case "put":
				taskResponse = reqSpec.when().put(resource.getResource());
				break;
			case "delete":
				taskResponse = reqSpec.when().delete(resource.getResource());
				break;
		}
		
		return taskResponse;
	}
	
	public static String getJsonValue(String json, String data)
	{
		JsonPath respJson = new JsonPath(json);
		String value = respJson.getString(data);
		return value;
	}
	
	public static String getResponseValue(Response resp, String data)
	{
		return getJsonValue(resp.asString(), data);
	}
	
	public static boolean extractJsonValue(String json, String data)
	{
		JsonPath respJson = new JsonPath(json);
		List<Object> values = respJson.getList("data");
		return values.contains(data);
	}
	
	public static boolean extractValueInResponseItemList(Response resp, String data)
	{
		return extractJsonValue(resp.asString(), data);
	}
}
