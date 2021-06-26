package java_resources;

// Enum is a special class in Java which is a collection of constants and methods
public enum APIResources{
	LoginAPI("/api/v1/auth/login"),
	GetTaskAPI("api/v1/tasks/{id}"),
	GetAllTaskAPI("api/v1/tasks"),
	PutTaskAPI("api/v1/tasks/{id}"),
	AddTaskAPI("api/v1/tasks"),
	DeleteTaskAPI("api/v1/tasks/{id}");
	
	
	private String resource;
	
	APIResources(String resource)
	{
		this.resource = resource;
	}
	
	public String getResource()
	{
		return resource;
	}
}
