package stepDefinitions;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;

import io.appium.java_client.AppiumDriver;
import io.cucumber.java.After;
import io.cucumber.java.AfterStep;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import utilities.Base;

@SuppressWarnings("rawtypes")
public class ScenarioHooks {
	AppiumDriver appiumDriver = Base.getAppiumDriver();

	// ==================================================|Hooks|==================================================
	@Before("@Start")
	public void scenarioBefore() throws Throwable {
		launchApplication();
	}

	@After("@End")
	public void scenarioAfter() {
		quitApplication();
	}

	@AfterStep
	public void scenarioStepAfter(Scenario scenario) {
		screenshotFailedScenario(scenario);
	}

	// ==================================================|Helpers|==================================================
	private void launchApplication() throws Throwable {
		appiumDriver = Base.initializeAppiumDriver();
	}

	private void quitApplication() {
		if (appiumDriver != null) {
			appiumDriver.quit();
		}

		if (Base.appiumDriverLocalService != null) {
			Base.appiumDriverLocalService.stop();
		}
	}

	public void screenshotFailedScenario(Scenario scenario) {
		if (scenario.isFailed()) {
			try {
				byte[] byteScreenshot = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.BYTES);

				scenario.attach(byteScreenshot, "image/png", "AttachedScreenshots");
			} catch (WebDriverException webDriverException) {
				webDriverException.printStackTrace();
			}
		}
	}
}