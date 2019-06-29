import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by mpizarro on 6/22/2018.
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/feature/"}
        , format = {"pretty", "html:results/cucumber", "json:target/Cucumber.json"}
        , tags = {"@Vehiculo"}

)
public class TestRunner {
}
