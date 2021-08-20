package pageObjects;

import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

@SuppressWarnings("rawtypes")
public class Page_Login {
	AppiumDriver appiumDriver;

	public Page_Login(AppiumDriver appiumDriver) {
		this.appiumDriver = appiumDriver;
		PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
	}

	// ==================================================|Page_Objects|==================================================
	@FindBy(xpath = "//android.widget.Button[@text='Login']")
	public MobileElement button_Login;

	@FindBy(xpath = "//android.widget.EditText[@resource-id='Input_PasswordVal']")
	public MobileElement input_Password;

	@FindBy(xpath = "//android.widget.EditText[@resource-id='Input_UsernameVal']")
	public MobileElement input_Username;

	@FindBy(xpath = "//android.view.View[@resource-id = 'feedbackMessageContainer']/android.view.View/android.view.View[2]")
	public MobileElement label_FeedbackMessage;
}