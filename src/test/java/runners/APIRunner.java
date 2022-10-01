package runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        //features we use to provide the path of all the features file
        features = "src/test/resources/features/",
        //glue is where we find implementations for gherkin steps
        //we provide the path of package where we defined all the steps
        glue = "APIsteps",
        //if we set dry run to true, it stops the actual execution and quickly
        //scan all the steps whether they are implemented or not
        //to execute the script, set dry run to false
        dryRun = false,
        //it cleans your console output for cucumber test if it has
        //irrelevant or unreadable character in it
        //recommended is, set it to true always
        monochrome = true,
        tags = "@dynamic",
        //when you use pretty keyword under plugins, it shows all the steps which you
        //executed in console
        plugin = {"pretty"}

        //rerun plugin is going to capture all the scenarios which were failed during execution
)

public class APIRunner {
}
