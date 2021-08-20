package stepDefinitions;

import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.Pages;
import utilities.Base;
import utilities.Keywords;

public class StepDefinitions {
	Keywords keywords = new Keywords(Base.getAppiumDriver());
	Pages pages = new Pages(Base.getAppiumDriver());

	// ==================================================|Given|==================================================
	@Given("the user is on the login page")
	public void the_user_is_on_the_login_page() throws Throwable {
		try {
			if (keywords.isMobileElementDisplayed(pages.getPageGeneral().button_Menu)) {
				keywords.mobileElementTap(pages.getPageGeneral().button_Menu);
				keywords.mobileElementTap(pages.getPageGeneral().button_Logout);
			}

			keywords.waitUntilMobileElementVisible(pages.getPageLogin().input_Username);
			keywords.waitUntilMobileElementVisible(pages.getPageLogin().input_Password);
			keywords.waitUntilMobileElementVisible(pages.getPageLogin().button_Login);

			Assert.assertTrue(keywords.isMobileElementDisplayed(pages.getPageLogin().input_Username));
			Assert.assertTrue(keywords.isMobileElementDisplayed(pages.getPageLogin().input_Password));
			Assert.assertTrue(keywords.isMobileElementDisplayed(pages.getPageLogin().button_Login));
		} catch (AssertionError assertionError) {
			throw new Exception(assertionError.getMessage());
		} catch (Exception exception) {
			throw new Exception(exception.getMessage());
		}
	}

	@Given("the user is logged in")
	public void the_user_is_logged_in() throws Throwable {
		try {
			if (!keywords.isMobileElementDisplayed(pages.getPageGeneral().button_Menu)) {
				loginWithCredentials(Base.dataVariables("username"), Base.dataVariables("password"));
			}

			keywords.waitUntilMobileElementVisible(pages.getPageGeneral().button_Menu);
			keywords.mobileElementTap(pages.getPageGeneral().button_Menu);
			keywords.switchToWebview();
			keywords.waitUntilMobileElementVisible(pages.getPageGeneral().label_Username);
			keywords.waitUntilMobileElementVisible(pages.getPageGeneral().button_Logout);

			Assert.assertTrue(keywords.isMobileElementDisplayed(pages.getPageGeneral().label_Username));
			Assert.assertEquals(Base.dataVariables("user"),
					keywords.getMobileElementText(pages.getPageGeneral().label_Username));
			Assert.assertTrue(keywords.isMobileElementDisplayed(pages.getPageGeneral().button_Logout));

			keywords.mobileElementClick(pages.getPageGeneral().button_Home);
			keywords.switchToNative();
		} catch (AssertionError assertionError) {
			throw new Exception(assertionError.getMessage());
		} catch (Exception exception) {
			throw new Exception(exception.getMessage());
		}
	}

	// ==================================================|When|==================================================
	@When("the user logs in with the credentials (.*) and (.*)$")
	public void the_user_logs_in_with_the_credentials_username_and_password(String strUsername, String strPassword)
			throws Throwable {
		try {
			loginWithCredentials(strUsername, strPassword);
		} catch (Exception exception) {
			throw new Exception(exception.getMessage());
		}
	}

	@When("the user logs out")
	public void the_user_logs_out() throws Throwable {
		try {
			keywords.waitUntilMobileElementVisible(pages.getPageGeneral().button_Menu);
			keywords.mobileElementTap(pages.getPageGeneral().button_Menu);
			keywords.switchToWebview();
			keywords.waitUntilMobileElementVisible(pages.getPageGeneral().button_Logout);
			keywords.mobileElementClick(pages.getPageGeneral().button_Logout);
			keywords.switchToNative();
		} catch (Exception exception) {
			throw new Exception(exception.getMessage());
		}
	}

	// ==================================================|Then|==================================================
	@Then("^the user (.*) will be logged in successfully$")
	public void the_user_will_be_logged_in_successfully(String strUser) throws Throwable {
		try {
			keywords.waitUntilMobileElementVisible(pages.getPageGeneral().button_Menu);
			keywords.mobileElementTap(pages.getPageGeneral().button_Menu);
			keywords.switchToWebview();
			keywords.waitUntilMobileElementVisible(pages.getPageGeneral().label_Username);
			keywords.waitUntilMobileElementVisible(pages.getPageGeneral().button_Logout);

			Assert.assertTrue(keywords.isMobileElementDisplayed(pages.getPageGeneral().label_Username));
			Assert.assertEquals(strUser, keywords.getMobileElementText(pages.getPageGeneral().label_Username));
			Assert.assertTrue(keywords.isMobileElementDisplayed(pages.getPageGeneral().button_Logout));

			keywords.mobileElementClick(pages.getPageGeneral().button_Home);
			keywords.switchToNative();
		} catch (AssertionError assertionError) {
			throw new Exception(assertionError.getMessage());
		} catch (Exception exception) {
			throw new Exception(exception.getMessage());
		}
	}

	@Then("the user will be back on the login page")
	public void the_user_will_be_back_on_the_login_page() throws Throwable {
		try {
			keywords.waitUntilMobileElementVisible(pages.getPageLogin().input_Username);
			keywords.waitUntilMobileElementVisible(pages.getPageLogin().input_Password);
			keywords.waitUntilMobileElementVisible(pages.getPageLogin().button_Login);

			Assert.assertTrue(keywords.isMobileElementDisplayed(pages.getPageLogin().input_Username));
			Assert.assertTrue(keywords.isMobileElementDisplayed(pages.getPageLogin().input_Password));
			Assert.assertTrue(keywords.isMobileElementDisplayed(pages.getPageLogin().button_Login));
		} catch (AssertionError assertionError) {
			throw new Exception(assertionError.getMessage());
		} catch (Exception exception) {
			throw new Exception(exception.getMessage());
		}
	}

	@Then("the feedback message (.*) will be displayed$")
	public void the_feedback_message_will_be_displayed(String strFeedbackMessage) throws Throwable {
		try {
			strFeedbackMessage = strFeedbackMessage.replace("\"", "");

			keywords.waitUntilMobileElementVisible(pages.getPageLogin().label_FeedbackMessage);

			Assert.assertTrue(keywords.isMobileElementDisplayed(pages.getPageLogin().label_FeedbackMessage));
			Assert.assertEquals(strFeedbackMessage,
					keywords.getMobileElementText(pages.getPageLogin().label_FeedbackMessage));
		} catch (AssertionError assertionError) {
			throw new Exception(assertionError.getMessage());
		} catch (Exception exception) {
			throw new Exception(exception.getMessage());
		}
	}

	// ==================================================|Reusable_Functions|==================================================
	private void loginWithCredentials(String strUsername, String strPassword) throws Throwable {
		keywords.mobileElementClear(pages.getPageLogin().input_Username);
		keywords.mobileElementSendKeys(pages.getPageLogin().input_Username, strUsername);
		keywords.mobileElementClear(pages.getPageLogin().input_Password);
		keywords.mobileElementSendKeys(pages.getPageLogin().input_Password, strPassword);
		keywords.mobileElementTap(pages.getPageLogin().button_Login);
	}
}