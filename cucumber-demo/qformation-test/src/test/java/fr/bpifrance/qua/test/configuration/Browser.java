package fr.bpifrance.qua.test.configuration;

import java.awt.AWTException;
import java.awt.Rectangle;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Map;

import javax.imageio.ImageIO;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.GeckoDriverService;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerDriverService;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.remote.service.DriverService;
import org.openqa.selenium.safari.SafariDriverService;
import org.slf4j.LoggerFactory;

/**
 * On définit ici les particularités pour chaque browser que l'on veut
 * supporter. (en corrélation avec la configuration maven (voir partie build) <br>
 * La classe attend les drivers dans les répertoires définies dans l'énumération
 * 
 * <ul>
 * <li>Un driver service local. (utilisé si on ne se connecte pas à un hub selenium)</li>
 * <li>Les options du browser</li>
 * </ul>
 *
 */
public enum Browser {

	CHROME("target/dependency/chrome_driver"), FIREFOX(""), IE("target/dependency/ie_driver"),
	EDGE("target/dependency/edge_driver"), OPERA("target/dependency/opera_driver");

	String mavenBuildPath;

	private Browser(String path) {
		this.mavenBuildPath = path;
	}

	private String getMavenPath() {
		return Browser.isUnix() ? mavenBuildPath : mavenBuildPath.concat(".exe");
	}

	private static boolean isUnix() {
		String os = System.getProperty("os.name");
		if (os == null || os.trim().length() == 0) {
			return true;
		}
		os = os.toLowerCase();
		boolean detectIfUnixLike = (os.indexOf("nix") >= 0 || os.indexOf("nux") >= 0 || os.indexOf("aix") > 0);
		LoggerFactory.getLogger(Browser.class).info("OS detected (is Unix like) ? :"+Boolean.toString(detectIfUnixLike));
		return detectIfUnixLike;
	}

	/**
	 * 
	 * @return Un service driver local évitant pour obtenir un webdriver
	 */
	@SuppressWarnings("rawtypes")
	public DriverService getDriverService() {
		DriverService.Builder builder = null;
		switch (this) {
		case CHROME:
			builder = new ChromeDriverService.Builder();
			break;
		case FIREFOX:
			builder = new GeckoDriverService.Builder();
			break;
		case IE:
			builder = new InternetExplorerDriverService.Builder();
			break;
		case OPERA:
			builder = new SafariDriverService.Builder();
			break;
		case EDGE:
			builder = new EdgeDriverService.Builder();
			break;
		default:
			return null;
		}
		return builder.usingDriverExecutable(new File(getMavenPath())).usingAnyFreePort().build();
	}

	/**
	 * 
	 * @return les Capabilities du browser courant.
	 */
	public Capabilities getCapabilitiesBrowser() {
		switch (this) {
		case CHROME:
			return getCapabilitiesChrome();
		case IE:
			return getCapabilitiesIe();
		case EDGE:
			return getCapabilitiesEdge();
		default:
			return null;
		}
	}
	
	private Capabilities getCapabilitiesChrome() {
		ChromeOptions options = new ChromeOptions();
		options.setExperimentalOption("useAutomationExtension", false);
		options.addArguments("--disable-extensions");
		options.addArguments("start-maximized");
		return options;
	}

	private Capabilities getCapabilitiesIe() {
		InternetExplorerOptions options = new InternetExplorerOptions();
		return options;
	}
	
	private Capabilities getCapabilitiesEdge() {
		EdgeOptions options = new EdgeOptions();
		return options;
	}

	/**
	 * Dans le cas où l'on n'utilise pas un hub selenium, on instancie un service local.
	 * 
	 * @param le service de driver local 
	 * @return le webdriver courant
	 */
	public RemoteWebDriver getWebDriverFor(DriverService driverService) {
		Capabilities capabilities = getCapabilitiesBrowser();
		switch (this) {
		case CHROME:
			return new BpiChromeDriver((ChromeDriverService) driverService, (ChromeOptions) capabilities);
		case IE:
			return new BpiInternetExplorerDriver((InternetExplorerDriverService) driverService,
					(InternetExplorerOptions) capabilities);
		case EDGE:
			return new BpiEdgeDriver((EdgeDriverService) driverService, (EdgeOptions) capabilities);
		default:
			return null;
		}
	}

	/**
	 * Surcharge du driver pour prendre des screenshots.
	 *
	 */
	private static class BpiChromeDriver extends ChromeDriver {

		private int numeroEtape = 0;

		public BpiChromeDriver(ChromeDriverService service, ChromeOptions options) {
			super(service, options);
		}

		@Override
		protected Response execute(String driverCommand, Map<String, ?> parameters) {
		//	Browser.takeScreenshot(numeroEtape++, driverCommand, parameters);
			return super.execute(driverCommand, parameters);
		}
	}

	/**
	 * Surcharge du driver pour prendre des screenshots.
	 *
	 */
	private static class BpiInternetExplorerDriver extends InternetExplorerDriver {

		private int numeroEtape = 0;

		public BpiInternetExplorerDriver(InternetExplorerDriverService service, InternetExplorerOptions options) {
			super(service, options);
		}

		@Override
		protected Response execute(String driverCommand, Map<String, ?> parameters) {
		//	Browser.takeScreenshot(numeroEtape++, driverCommand, parameters);
			return super.execute(driverCommand, parameters);
		}
	}

	/**
	 * Surcharge du driver pour prendre des screenshots.
	 *
	 */
	private static class BpiEdgeDriver extends EdgeDriver {

		private int numeroEtape = 0;

		public BpiEdgeDriver(EdgeDriverService service, EdgeOptions options) {
			super(service, options);
		}

		@Override
		protected Response execute(String driverCommand, Map<String, ?> parameters) {
		//	Browser.takeScreenshot(numeroEtape++, driverCommand, parameters);
			return super.execute(driverCommand, parameters);
		}
	}

	private static void takeScreenshot(int numeroEtape, String driverCommand, Map<String, ?> parameters) {
		try {
			Thread.sleep(200);
		} catch (InterruptedException ex) {
			throw new RuntimeException(ex);
		}
		System.out.println(driverCommand + ": " + parameters);
		try {
			Robot robot;
			robot = new Robot();
			BufferedImage screenShot = robot
					.createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
			ImageIO.write(screenShot, "JPG", new File("target/step" + numeroEtape + ".jpg"));
		} catch (AWTException | IOException e) {
			throw new RuntimeException(e);
		}
	}

	/**
	 * Surcharge pour ralentir l'éxécution ?
	 *
	 */
	public static final class BpiRemoteWebDriver extends RemoteWebDriver {

		public BpiRemoteWebDriver(URL sUrl, Capabilities options) {
			super(sUrl, options);
		}

		@Override
		protected Response execute(String driverCommand, Map<String, ?> parameters) {
			try {
				Thread.sleep(100);
			} catch (InterruptedException ex) {
				throw new RuntimeException(ex);
			}
			return super.execute(driverCommand, parameters);
		}
	}

}