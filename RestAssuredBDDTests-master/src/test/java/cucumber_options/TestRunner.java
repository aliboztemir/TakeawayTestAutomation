package cucumber_options;

import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(
					features = {"src/test/java/features"},
					glue = {"step_definitions"},
					tags = "@Smoke or @Stress",
					plugin = { "pretty", "html:target/cucumber-reports.html" },
					monochrome = true
					// Can also be run from Maven using the following command:
					// mvn test -Dcucumber.options="--tags @Smoke or @Stress"
				)
public class TestRunner {

	
}