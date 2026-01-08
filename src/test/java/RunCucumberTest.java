import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;


// чтобы запускать из консоли
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {"pretty", "summary"},
        features = {"src/test/resources/Transfer.feature"},
        glue = {"ru.netology.steps"})

public class RunCucumberTest {

}
