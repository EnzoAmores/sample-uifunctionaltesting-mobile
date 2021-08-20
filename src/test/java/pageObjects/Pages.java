package pageObjects;

import io.appium.java_client.AppiumDriver;

@SuppressWarnings("rawtypes")
public class Pages {
	AppiumDriver appiumDriver;

	public Pages(AppiumDriver appiumDriver) {
		this.appiumDriver = appiumDriver;
	}

	// ==================================================INITIALIZE==================================================
	private Page_General page_General;
	private Page_Login page_Login;

	// ==================================================RETURN==================================================
	public Page_General getPageGeneral() {
		return (page_General == null) ? page_General = new Page_General(appiumDriver) : page_General;
	}

	public Page_Login getPageLogin() {
		return (page_Login == null) ? page_Login = new Page_Login(appiumDriver) : page_Login;
	}
}