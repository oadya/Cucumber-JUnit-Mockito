package fr.bpifrance.qua.qformation.domaine.test;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
		strict = true,
		features="src/test/resources/features/",
		glue = {"fr.bpifrance.qua.qformation.domaine.test"})
public class RunFeaturesTest {

}
