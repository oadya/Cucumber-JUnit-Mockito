package fr.bpifrance.qua.qformation.dao.test.ti;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = {"pretty", "html:target/cucumber", "json:target/cucumber.json"},
		strict = true,
		features="src/test/resources/features/",
		glue = {"fr.bpifrance.qua.qformation.dao.test.ti"})
public class RunFeaturesTest {

}
