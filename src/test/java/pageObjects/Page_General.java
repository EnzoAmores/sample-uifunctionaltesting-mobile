package pageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

@SuppressWarnings("rawtypes")
public class Page_General {
	AppiumDriver appiumDriver;

	public Page_General(AppiumDriver appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
	}

	// ==================================================|Page_Objects|==================================================
	@FindBy(xpath = "//div[contains(@id, 'ApplicationTitleWrapper')]")
	public MobileElement button_Home;

	@FindBy(xpath = "//div[contains(@id, 'LoginInfo')]//a")
	public MobileElement button_Logout;

	@FindBy(xpath = "//*[contains(@resource-id, 'MenuTab')]")
	public MobileElement button_Menu;

	@FindBy(xpath = "//div[contains(@id, 'LoginInfo')]//img/following-sibling::div/span")
	public MobileElement label_Username;
}