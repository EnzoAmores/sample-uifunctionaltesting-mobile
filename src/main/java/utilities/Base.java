package utilities;

import java.net.URL;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

@SuppressWarnings("rawtypes")
public class Base {
	// ==================================================|WebDriver|==================================================
	public static AppiumDriverLocalService appiumDriverLocalService;
	public static URL url;
	public static DesiredCapabilities desiredCapabilities;
	protected static ThreadLocal<AppiumDriver> threadLocalAppiumDriver = new ThreadLocal<>();

	public static synchronized AppiumDriver getAppiumDriver() {
		return threadLocalAppiumDriver.get();
	}

	public static AppiumDriver initializeAppiumDriver() throws Throwable {
		AppiumDriver appiumDriver = null;
		desiredCapabilities = new DesiredCapabilities();

		if (configurationVariables("device").equals("browserstack")) {
			url = new URL("http://hub.browserstack.com/wd/hub");

			desiredCapabilities.setCapability("browserstack.user", configurationVariables("browserStackUser"));
			desiredCapabilities.setCapability("browserstack.key", configurationVariables("browserStackKey"));
			desiredCapabilities.setCapability("app", configurationVariables("browserStackApp"));
			desiredCapabilities.setCapability("device", configurationVariables("browserStackDevice"));
			desiredCapabilities.setCapability("os_version", configurationVariables("browserStackOSVersion"));
			desiredCapabilities.setCapability("project", configurationVariables("browserStackProject"));
			desiredCapabilities.setCapability("build", configurationVariables("browserStackBuild"));
			desiredCapabilities.setCapability("name", configurationVariables("browserStackName"));
		} else if (configurationVariables("device").equals("appium")) {
			AppiumServiceBuilder appiumServiceBuilder = new AppiumServiceBuilder();

			appiumServiceBuilder.usingAnyFreePort();
			appiumServiceBuilder.withIPAddress("127.0.0.1");
			appiumServiceBuilder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");

			if (configurationVariables("appiumPlatformName").equals("Android"))
				appiumServiceBuilder.withArgument(() -> "--allow-insecure", "chromedriver_autodownload");

			appiumDriverLocalService = AppiumDriverLocalService.buildService(appiumServiceBuilder);

			appiumDriverLocalService.start();

			url = new URL(appiumDriverLocalService.getUrl().toString());

			desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME,
					configurationVariables("appiumAutomationName"));
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME,
					configurationVariables("appiumPlatformName"));
			desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION,
					configurationVariables("appiumPlatformVersion"));
			desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME,
					configurationVariables("appiumDeviceName"));
			desiredCapabilities.setCapability(MobileCapabilityType.UDID, configurationVariables("appiumUDID"));
			desiredCapabilities.setCapability(MobileCapabilityType.APP, configurationVariables("appiumApp"));
			desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET,
					configurationVariables("appiumFullReset"));

			if (configurationVariables("appiumDeviceName").contains("_"))
				desiredCapabilities.setCapability("avd", configurationVariables("appiumDeviceName"));
		}

		threadLocalAppiumDriver.set(new AppiumDriver(url, desiredCapabilities));

		appiumDriver = getAppiumDriver();

		return appiumDriver;
	}

	// ==================================================|Properties_File|==================================================
	private static void setPropertiesVariableValue(String strPropertiesFilePath, String strVariableName,
			String strVariableValue) {
		PropertiesConfiguration propertiesConfiguration = null;
		Boolean savePropertiesFile = true;

		try {
			propertiesConfiguration = new PropertiesConfiguration(strPropertiesFilePath);

			propertiesConfiguration.setProperty(strVariableName, strVariableValue);
		} catch (ConfigurationException configurationException) {
			configurationException.printStackTrace();
			savePropertiesFile = false;
		}

		try {
			if (savePropertiesFile) {
				propertiesConfiguration.save();
			}
		} catch (ConfigurationException configurationException) {
			configurationException.printStackTrace();
		}
	}

	private static String getPropertiesVariableValue(String strPropertiesFilePath, String strVariableName) {
		String strVariableValue = "";

		try {
			PropertiesConfiguration propertiesConfiguration = new PropertiesConfiguration(strPropertiesFilePath);
			strVariableValue = propertiesConfiguration.getProperty(strVariableName).toString();
		} catch (ConfigurationException configurationException) {
			configurationException.printStackTrace();
		}

		return strVariableValue;
	}

	public static void setBrowserVariableValue(String strBrowser) {
		String strPropertiesFilePath = System.getProperty("user.dir") + "/src/test/resources/configuration.properties";

		setPropertiesVariableValue(strPropertiesFilePath, "browser", strBrowser);
	}

	public static String configurationVariables(String strVariableName) {
		String strPropertiesFilePath = System.getProperty("user.dir") + "/src/test/resources/configuration.properties";

		return getPropertiesVariableValue(strPropertiesFilePath, strVariableName);
	}

	public static String dataVariables(String strVariableName) {
		String strPropertiesFilePath = System.getProperty("user.dir") + "/src/test/resources/data.properties";

		return getPropertiesVariableValue(strPropertiesFilePath, strVariableName);
	}
}