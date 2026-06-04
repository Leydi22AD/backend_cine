package pe.edu.upeu.ProyectLP2;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources",
        glue = {"pe.edu.upeu.ProyectLP2.service", "pe.edu.upeu.ProyectLP2.ddb", "pe.edu.upeu.ProyectLP2"}, // 💡 Le agregamos la raíz al glue
        plugin = {"pretty", "html:target/cucumber-reports.html"}
)
public class ServiceTestRunnerTest {
}
