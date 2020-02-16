package fr.bpifrance.qua.qformation.testihm.definitions;

import static fr.bpifrance.qua.test.configuration.AbstractSeleniumTest.SELENIUM_HUB_URL;
import static fr.bpifrance.qua.test.configuration.AbstractSeleniumTest.currentBrowser;
import static fr.bpifrance.qua.test.configuration.AbstractSeleniumTest.currentDriver;
import static fr.bpifrance.qua.test.configuration.AbstractSeleniumTest.isEmpty;
import static fr.bpifrance.qua.test.configuration.AbstractSeleniumTest.localService;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.Capabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import fr.bpifrance.qua.test.configuration.Browser;

/**
 * Afin d'avoir de supprimer/recréér le web driver entre chaque scénario.
 * https://www.toolsqa.com/cucumber/cucumber-hooks/
 *
 */
public class Hooks {

	private static final Logger logger = LoggerFactory.getLogger(Hooks.class);
	
	@Before
	public void setUp() throws MalformedURLException {
		if (isEmpty(SELENIUM_HUB_URL)) {
			//local Service est instancié dans AbstractSeleniumTest
			currentDriver = currentBrowser.getWebDriverFor(localService);
			logger.info("CREATE LOCAL WEB DRIVER for "+currentBrowser.toString()+" "+currentDriver.toString());
			if (currentDriver == null) {
				throw new RuntimeException(
						"Impossible d'obtenir un web driver pour le navigateur " + currentBrowser.toString());
			}
		} else {
			//On utilise le Bpi remote Web driver avec le hub selenium
			Capabilities cap= currentBrowser.getCapabilitiesBrowser();
			currentDriver = new Browser.BpiRemoteWebDriver(new URL(SELENIUM_HUB_URL),
					currentBrowser.getCapabilitiesBrowser());
			logger.info("CREATE REMOTE WEB DRIVER "+currentDriver.toString()+" WITH CAPABILITIES "+cap.toString());
		}
	}

	@After
	public void setDown() {
		logger.info("DELETE WEB DRIVER :"+currentDriver.toString());
		currentDriver.quit();
		currentDriver=null;
	}
}
