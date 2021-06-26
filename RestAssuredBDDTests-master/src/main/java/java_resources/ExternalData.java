package java_resources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ExternalData {

	private static String javaResFolder = System.getProperty("user.dir") + "/src/main/java/java_resources";
	private static Properties data = new Properties();
	
	public static String getGlobalData(String globalData)
	{
		String value = "";
		
		try
		{
			FileInputStream inputFile = new FileInputStream(javaResFolder + "/global_data.properties");
			data.load(inputFile);
			value = data.getProperty(globalData);
			inputFile.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		
		return value;
	}
}
