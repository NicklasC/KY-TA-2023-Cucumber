import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources")
//@CucumberOptions(features = "src/test/java/com/example/selenium_demo/resources")
public class RunCucumberTest {
}
