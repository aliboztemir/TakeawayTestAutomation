package java_resources;


import pojo.AddTask;
import pojo.LoginApi;
import pojo.PutTask;

public class Payloads {

	public static AddTask addTaskPayload(String title)
	{
		AddTask addTask = new AddTask();
		
		addTask.setTitle(title);
		
		return addTask;
	}
	
	public static PutTask putTaskPayload(String title)
	{
		PutTask putTask = new PutTask();
		
		putTask.setTitle(title);
		
		return putTask;
	}
	
	public static LoginApi loginApiPayload(String email, String password)
	{
		LoginApi loginApi = new LoginApi();
		
		loginApi.setEmail(email);
		loginApi.setPassword(password);
		
		return loginApi;
	}
}
