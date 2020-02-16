package fr.bpifrance.qua.qformation.testihm.runners;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import fr.bpifrance.qua.test.configuration.AbstractSeleniumTest;

/**
 * 
 *
 */
@RunWith(Cucumber.class)
@CucumberOptions(
		tags = {},
		plugin = {"pretty", "html:target/cucumber", "json:target/cucumber/cucumber.json", "junit:target/cucumber/cucumber.xml"},
		dryRun=false,
		strict=true,
		monochrome=true,
		features="src/test/resources/features/",
		glue = {"fr.bpifrance.qua.qformation.testihm.definitions"})
//L'utilisation de la classe parent permet l'initialisation selenium conjointement avec la classe Hook.
//le plugin maven-failsafe-plugin utilise les tests nommés avec le pattern "IT"
public class TestsFeatureIT extends AbstractSeleniumTest {
	/*@AfterClass
	public static void generateExecutionReport() throws Exception {
		List<String> pathToCucumberJsonFiles = FileUtil.findJsonFiles("target/cucumber/");
		System.out.println("ABC: " + pathToCucumberJsonFiles);
		List<Feature> features = FeatureParser.parse(pathToCucumberJsonFiles);
		DocumentAttributes attrs = GlobalConfig.getInstance().getDocumentAttributes();
		attrs.toc("left")
				.backend("html5")
				.docType("pdf")
				.icons("font").numbered(false)
				.sourceHighlighter("coderay")
				.docTitle("Documentation Title")
				.sectAnchors(true)
				.sectLink(true);
		System.out.println("ABC: " + features.toString());
		CukedoctorConverter converter = Cukedoctor.instance(features, attrs);
		converter.setFilename("target/living_documentation/formation.adoc");

		converter.saveDocumentation();
	}
*/
	
}
