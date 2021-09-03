package utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.TapOptions;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.ElementOption;
import io.appium.java_client.touch.offset.PointOption;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Keywords {
	AppiumDriver appiumDriver;

	public Keywords(AppiumDriver appiumDriver) {
		this.appiumDriver = appiumDriver;
	}

	// ==================================================|Do|==================================================
	/**
	 * Submit a Rest API Delete Request and will return a response output. If
	 * requestBody is not needed just leave the parameter as blank a string.
	 * 
	 * @param requestURL  - Set Rest request URL.
	 * @param requestBody - Set Json body as string.
	 * @return Response type as output.
	 */
	public Response restDelete(String requestURL, String requestBody) {
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.body(requestBody);

		Response response = request.delete(requestURL);

		return response;
	}

	/**
	 * Submit a Rest API Get Request and will return a response output. If
	 * requestBody is not needed just leave the parameter as blank a string.
	 * 
	 * @param requestURL  - Set Rest request URL.
	 * @param requestBody - Set Json body as string.
	 * @return Response type as output.
	 */
	public Response restGet(String requestURL, String requestBody) {
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.body(requestBody);

		Response response = request.get(requestURL);

		return response;
	}

	/**
	 * Submit a Rest API Post Request and will return a response output. If
	 * requestBody is not needed just leave the parameter as blank a string.
	 * 
	 * @param requestURL  - Set Rest request URL.
	 * @param requestBody - Set Json body as string.
	 * @return Response type as output.
	 */
	public Response restPost(String requestURL, String requestBody) {
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.body(requestBody);

		Response response = request.post(requestURL);

		return response;
	}

	/**
	 * Submit a Rest API Put Request and will return a response output. If
	 * requestBody is not needed just leave the parameter as blank a string.
	 * 
	 * @param requestURL  - Set Rest request URL.
	 * @param requestBody - Set Json body as string.
	 * @return Response type as output.
	 */
	public Response restPut(String requestURL, String requestBody) {
		RequestSpecification request = RestAssured.given();

		request.header("Content-Type", "application/json");
		request.body(requestBody);

		Response response = request.put(requestURL);

		return response;
	}

	/**
	 * Converts the color RGBA to hexadecimal value.
	 * 
	 * @param strColor - the colors RGBA value to be converted.
	 * @return Returns the Hexadecimal value of the color.
	 */
	public String convertColorToHexadecimal(String strColor) {
		String strHex = Color.fromString(strColor).asHex();

		return strHex;
	}

	/**
	 * Waits for the mobile element to be visible then clears the value in the web
	 * element.
	 * 
	 * @param mobileElement - mobile element to perform the action to.
	 */
	public void mobileElementClear(MobileElement mobileElement) throws Exception {
		waitUntilMobileElementVisible(mobileElement);

		if (isMobileElementDisplayed(mobileElement) && isMobileElementEnabled(mobileElement))
			mobileElement.clear();
	}

	/**
	 * Waits for the mobile element to be visible then clicks the mobile element.
	 * 
	 * @param mobileElement - mobile element to perform the action to.
	 */
	public void mobileElementClick(MobileElement mobileElement) throws Exception {
		waitUntilMobileElementVisible(mobileElement);

		if (isMobileElementDisplayed(mobileElement) && isMobileElementEnabled(mobileElement))
			mobileElement.click();
	}

	/**
	 * Waits for the mobile element to be visible then presses the mobile element.
	 * 
	 * @param mobileElement - mobile element to perform the action to.
	 * @param longSeconds   - seconds on how long the press is.
	 */
	public void mobileElementPress(MobileElement mobileElement, long longSeconds) throws Exception {
		waitUntilMobileElementVisible(mobileElement);

		if (isMobileElementDisplayed(mobileElement) && isMobileElementEnabled(mobileElement))
			new TouchAction<>(appiumDriver).press(ElementOption.element(mobileElement))
					.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(longSeconds))).release().perform();
	}

	/**
	 * Waits for the mobile element to be visible then sends the keys/characters to
	 * the mobile element.
	 * 
	 * @param mobileElement - mobile element to perform the action to.
	 * @param strKeys       - keys/characters to be sent.
	 */
	public void mobileElementSendKeys(MobileElement mobileElement, String strKeys) throws Exception {
		waitUntilMobileElementVisible(mobileElement);

		if (isMobileElementDisplayed(mobileElement) && isMobileElementEnabled(mobileElement))
			mobileElement.sendKeys(strKeys);
	}

	/**
	 * Waits for the mobile element to be visible then taps the mobile element.
	 * 
	 * @param mobileElement - mobile element to perform the action to.
	 */
	public void mobileElementTap(MobileElement mobileElement) throws Exception {
		waitUntilMobileElementVisible(mobileElement);

		if (isMobileElementDisplayed(mobileElement) && isMobileElementEnabled(mobileElement))
			new TouchAction<>(appiumDriver)
					.tap(TapOptions.tapOptions().withElement(ElementOption.element(mobileElement)))
					.waitAction(WaitOptions.waitOptions(Duration.ofMillis(250))).perform();
	}

	/**
	 * Presses on the given coordinates.
	 * 
	 * @param intX        - X coordinate.
	 * @param intY        - Y coordinate.
	 * @param longSeconds - seconds on how long the press is.
	 */
	public void pressOnCoordinates(int intX, int intY, long longSeconds) {
		new TouchAction<>(appiumDriver).press(PointOption.point(intX, intY))
				.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(longSeconds))).release().perform();
	}

	/**
	 * Scrolls to the bottom of the current frame.
	 */
	public void scrollToBottom() {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) appiumDriver;

		javascriptExecutor.executeScript("window.scrollTo(0, document.body.scrollHeight)");
	}

	/**
	 * Scrolls to the given coordinates of the current frame.
	 * 
	 * @param intX - X coordinate.
	 * @param intY - Y coordinate.
	 */
	public void scrollToCoordinates(int intX, int intY) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) appiumDriver;

		javascriptExecutor.executeScript("window.scrollBy(" + intX + "," + intY + ")");
	}

	/**
	 * Scrolls to the mobile element into view of the current frame.
	 * 
	 * @param mobileElement - mobile element to perform the action to.
	 */
	public void scrollToMobileElement(MobileElement mobileElement) {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) appiumDriver;

		javascriptExecutor.executeScript("arguments[0].scrollIntoView();", mobileElement);
	}

	/**
	 * Scrolls to the top of the current frame.
	 */
	public void scrollToTop() {
		JavascriptExecutor javascriptExecutor = (JavascriptExecutor) appiumDriver;

		javascriptExecutor.executeScript("window.scrollTo(0, 0)");
	}

	/**
	 * Switch to Native view.
	 */
	public void switchToNative() {
		appiumDriver.context("NATIVE_APP");
	}

	/**
	 * Switch to Web view.
	 */
	public void switchToWebview() {
		Set<String> contextNames = appiumDriver.getContextHandles();

		for (String contextName : contextNames) {
			if (contextName.contains("WEBVIEW"))
				appiumDriver.context(contextName);
		}
	}

	/**
	 * Swipe by mobile elements.
	 * 
	 * @param startMobileElement - start mobile element.
	 * @param endMobileElement   - end mobile element.
	 */
	public void swipeByMobileElements(MobileElement startMobileElement, MobileElement endMobileElement) {
		int intStartX = startMobileElement.getLocation().getX() + (startMobileElement.getSize().getWidth() / 2);
		int intStartY = startMobileElement.getLocation().getY() + (startMobileElement.getSize().getHeight() / 2);
		int intEndX = endMobileElement.getLocation().getX() + (endMobileElement.getSize().getWidth() / 2);
		int intEndY = endMobileElement.getLocation().getY() + (endMobileElement.getSize().getHeight() / 2);

		new TouchAction<>(appiumDriver).press(PointOption.point(intStartX, intStartY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				.moveTo(PointOption.point(intEndX, intEndY)).release().perform();
	}

	/**
	 * Swipe horizontally by percentage.
	 * 
	 * @param dblStartPercentage  - start percentage.
	 * @param dblEndPercentage    - end percentage.
	 * @param dblAnchorPercentage - anchor percentage.
	 */
	public void swipeHorizontalByPercentages(double dblStartPercentage, double dblEndPercentage,
			double dblAnchorPercentage) {
		Dimension size = appiumDriver.manage().window().getSize();
		int intStartPoint = (int) (size.width * dblStartPercentage);
		int intEndPoint = (int) (size.width * dblEndPercentage);
		int intAnchor = (int) (size.height * dblAnchorPercentage);

		new TouchAction<>(appiumDriver).press(PointOption.point(intStartPoint, intAnchor))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				.moveTo(PointOption.point(intEndPoint, intAnchor)).release().perform();
	}

	/**
	 * Swipe vertically by percentage.
	 * 
	 * @param dblStartPercentage  - start percentage.
	 * @param dblEndPercentage    - end percentage.
	 * @param dblAnchorPercentage - anchor percentage.
	 */
	public void swipeVerticalByPercentages(double dblStartPercentage, double dblEndPercentage,
			double dblAnchorPercentage) {
		Dimension size = appiumDriver.manage().window().getSize();
		int intStartPoint = (int) (size.height * dblStartPercentage);
		int intEndPoint = (int) (size.height * dblEndPercentage);
		int intAnchor = (int) (size.width * dblAnchorPercentage);

		new TouchAction<>(appiumDriver).press(PointOption.point(intAnchor, intStartPoint))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
				.moveTo(PointOption.point(intAnchor, intEndPoint)).release().perform();
	}

	/**
	 * Taps on the given coordinates.
	 * 
	 * @param intX - X coordinate.
	 * @param intY - Y coordinate.
	 */
	public void tapOnCoordinates(int intX, int intY) {
		new TouchAction<>(appiumDriver).tap(PointOption.point(intX, intY))
				.waitAction(WaitOptions.waitOptions(Duration.ofMillis(250))).perform();
	}

	/**
	 * Take screenshot.
	 * 
	 * @param filePath - string where the image is saved.
	 */
	public void takeScreenshot(String strFilePath) throws Exception {
		TakesScreenshot screenshot = ((TakesScreenshot) appiumDriver);
		File fileSource = screenshot.getScreenshotAs(OutputType.FILE);
		File fileDestination = new File(strFilePath);

		FileUtils.copyFile(fileSource, fileDestination);
	}

	/**
	 * Take screenshot of web element. Width and Height percentage optional.
	 * 
	 * @param mobileElement       - mobile element to screenshot.
	 * @param intWidthPercentage  - width percentage of web element.
	 * @param intHeightPercentage - height percentage of web element.
	 * @param strFilePath         - string where the image is saved.
	 */
	public void takeScreenshotOfMobileElement(MobileElement mobileElement, int intWidthPercentage,
			int intHeightPercentage, String strFilePath) throws Exception {
		File screenshot = ((TakesScreenshot) appiumDriver).getScreenshotAs(OutputType.FILE);
		BufferedImage fullImage = ImageIO.read(screenshot);
		Point point = mobileElement.getLocation();
		int intWebElementWidth = mobileElement.getSize().getWidth();
		int intWebElementHeight = mobileElement.getSize().getHeight();

		if (intWidthPercentage != 0) {
			intWebElementWidth = intWebElementWidth * (intWidthPercentage / 100);
		}

		if (intHeightPercentage != 0) {
			intWebElementHeight = intWebElementHeight * (intHeightPercentage / 100);
		}

		BufferedImage webElementScreenshot = fullImage.getSubimage(point.getX(), point.getY(), intWebElementWidth,
				intWebElementHeight);

		ImageIO.write(webElementScreenshot, "png", screenshot);

		File screenshotLocation = new File(strFilePath);

		FileUtils.copyFile(screenshot, screenshotLocation);
	}

	// ==================================================|See|==================================================
	/**
	 * Get all data from the specified row in the Excel file and return a array of
	 * String.
	 * 
	 * @param strFilePath    - Excel file path.
	 * @param intSheetNumber - Excel sheet number starts from 0.
	 * @param intRowNumber   - Excel row number starts from 0.
	 * @return Returns a array list of String.
	 */
	public ArrayList<String> getExcelRowData(String strFilePath, int intSheetNumber, int intRowNumber)
			throws Exception {
		FileInputStream fileInputStream = new FileInputStream(strFilePath);
		XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workBook.getSheetAt(intSheetNumber);
		Row row = sheet.getRow(intRowNumber);
		ArrayList<String> arrayListStrRowData = new ArrayList<String>();

		for (Cell cell : row) {
			DataFormatter dataFormatter = new DataFormatter();
			String strCellData = dataFormatter.formatCellValue(cell);

			arrayListStrRowData.add(strCellData);
		}

		workBook.close();
		fileInputStream.close();

		return arrayListStrRowData;
	}

	/**
	 * Checks if the mobile element is displayed or not.
	 * 
	 * @param mobileElement - mobile element to check.
	 * @return True if the options are displayed, false otherwise.
	 * @implNote Mostly used in Asserts.assertTrue, or in IF ELSE conditions.
	 */
	public boolean isMobileElementDisplayed(MobileElement mobileElement) throws Exception {
		try {
			return mobileElement.isDisplayed();
		} catch (NoSuchElementException e) {
			return false;
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Checks if the mobile element is enabled or not.
	 * 
	 * @param mobileElement - mobile element to check.
	 * @return True if the options are displayed, false otherwise.
	 * @implNote Mostly used in Asserts.assertTrue, or in IF ELSE conditions.
	 */
	public boolean isMobileElementEnabled(MobileElement mobileElement) throws Exception {
		try {
			return mobileElement.isEnabled();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Checks if the mobile element is focused or not.
	 * 
	 * @param mobileElement - mobile element to check.
	 * @return True if the options are displayed, false otherwise.
	 * @implNote Mostly used in Asserts.assertTrue, or in IF ELSE conditions.
	 */
	public boolean isMobileElementFocused(MobileElement mobileElement) throws Exception {
		try {
			return mobileElement.equals(appiumDriver.switchTo().activeElement());
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Checks if the mobile element is selected/checked or not.
	 * 
	 * @param mobileElement - mobile element to check.
	 * @return True if the options are displayed, false otherwise.
	 * @implNote Mostly used in Asserts.assertTrue, or in IF ELSE conditions.
	 */
	public boolean isMobileElementSelected(MobileElement mobileElement) throws Exception {
		try {
			return mobileElement.isSelected();
		} catch (Exception e) {
			return false;
		}
	}

	/**
	 * Get single cell data from Excel file and return the value as String.
	 * 
	 * @param strFilePath     - Excel file path.
	 * @param intSheetNumber  - Excel sheet number starts from 0.
	 * @param intRowNumber    - Excel row number starts from 0.
	 * @param intColumnNumber - Excel cell number starts from 0.
	 * @return Returns a string output.
	 */
	public String getExcelCellData(String strFilePath, int intSheetNumber, int intRowNumber, int intColumnNumber)
			throws Exception {
		FileInputStream fileInputStream = new FileInputStream(strFilePath);
		XSSFWorkbook workBook = new XSSFWorkbook(fileInputStream);
		Sheet sheet = workBook.getSheetAt(intSheetNumber);
		Row row = sheet.getRow(intRowNumber);
		Cell cell = row.getCell(intColumnNumber);
		DataFormatter dataFormatter = new DataFormatter();
		String strCellData = dataFormatter.formatCellValue(cell);

		workBook.close();
		fileInputStream.close();

		return strCellData;
	}

	/**
	 * Gets the pseudo element value.
	 * 
	 * @param strSelector      - example is "label.MandatoryLabel".
	 * @param strPseudoElement - example is ":after".
	 * @param strCSSProperty   - example is "content".
	 * @return Returns the value of the pseudo element.
	 * @implNote Mostly used in Asserts.assertEquals, or use in IF ELSE conditions.
	 */
	public String getPseudoElementValue(String strSelector, String strPseudoElement, String strCSSProperty) {
		String strScript = "return window.getComputedStyle(document.querySelector('" + strSelector + "'),'"
				+ strPseudoElement + "').getPropertyValue('" + strCSSProperty + "')";
		JavascriptExecutor js = (JavascriptExecutor) appiumDriver;
		String strContent = (String) js.executeScript(strScript);

		return strContent;
	}

	/**
	 * Gets the current date and time and returns it depending on the given format.
	 * 
	 * @param strFormat - the format of the date and time string. Example is
	 *                  "_ddMMyy_HHmm".
	 * @return Returns the formatted current date and time.
	 */
	public String getStringDateTimeNow(String strFormat) {
		String strFormattedDateTimeNow = "";
		Calendar calendar = Calendar.getInstance();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(strFormat);
		strFormattedDateTimeNow = simpleDateFormat.format(calendar.getTime());

		return strFormattedDateTimeNow;
	}

	/**
	 * Gets the mobile element attribute value.
	 * 
	 * @param mobileElement - mobile element to check.
	 * @param strAttribute  - attribute of the mobile element to get the value from.
	 * @return Returns the value of the mobile element attribute.
	 * @implNote Mostly used in Asserts.assertEquals, or use in IF ELSE conditions.
	 */
	public String getMobileElementAttributeValue(MobileElement mobileElement, String strAttribute) {
		return mobileElement.getAttribute(strAttribute).toString();
	}

	/**
	 * Gets the mobile element CSS value.
	 * 
	 * @param mobileElement - mobile element to check.
	 * @param strCSS        - CSS of the mobile element to get the value from.
	 * @return Returns the value of the mobile element CSS.
	 * @implNote Mostly used in Asserts.assertEquals, or use in IF ELSE conditions.
	 */
	public String getMobileElementCssValue(MobileElement mobileElement, String strCSS) {
		return mobileElement.getCssValue(strCSS).toString();
	}

	/**
	 * Gets the mobile element text.
	 * 
	 * @param mobileElement - mobile element to check.
	 * @return Returns the text of the mobile element.
	 * @implNote Mostly used in Asserts, or use in IF ELSE conditions.
	 */
	public String getMobileElementText(MobileElement mobileElement) {
		return mobileElement.getText();
	}

	/**
	 * Gets the mobile element xpath.
	 * 
	 * @param mobileElement - mobile element to check.
	 * @return Returns the text of the mobile element.
	 * @implNote Mostly used in Asserts, or use in IF ELSE conditions.
	 */
	public String getMobileElementXPath(MobileElement mobileElement) {
		return mobileElement.toString().substring(mobileElement.toString().indexOf("/"),
				mobileElement.toString().lastIndexOf("'"));
	}

	// ==================================================|Wait|==================================================
	/**
	 * Wait until the time duration runs out.
	 * 
	 * @param intDuration  - number of minutes or milliseconds.
	 * @param blnIsMinutes - flag if duration is in minutes or in milliseconds.
	 * @implNote Mostly used in special conditions. Avoid using at all cost.
	 */
	public void wait(Integer intDuration, Boolean blnIsMinutes) throws Exception {
		if (blnIsMinutes)
			intDuration = intDuration * 60000;

		Thread.sleep(intDuration);
	}

	/**
	 * Wait until text is present in the mobile element.
	 * 
	 * @param mobileElement - mobile element to check.
	 * @param strText       - text to expect in the mobile element.
	 */
	public void waitUntilTextPresentInMobileElement(MobileElement mobileElement, String strText) {
		WebDriverWait webDriverWait = new WebDriverWait(appiumDriver,
				Long.parseLong(Base.configurationVariables("waitTimeout")));

		webDriverWait.until(ExpectedConditions.textToBePresentInElement(mobileElement, strText));
	}

	/**
	 * Wait until web browser title contains the expected string.
	 * 
	 * @param strTitle - string to be expected.
	 */
	public void waitUntilWebBrowserTitleContains(String strTitle) {
		WebDriverWait webDriverWait = new WebDriverWait(appiumDriver, 60);

		webDriverWait.until(ExpectedConditions.titleContains(strTitle));
	}

	/**
	 * Wait until mobile element attribute value contains the expected value.
	 * 
	 * @param mobileElement - mobile element to check.
	 * @param strAttribute  - attribute of the mobile element to check.
	 * @param strValue      - expected attribute value.
	 */
	public void waitUntilMobileElementAttributeValueContains(MobileElement mobileElement, String strAttribute,
			String strValue) {
		WebDriverWait webDriverWait = new WebDriverWait(appiumDriver,
				Long.parseLong(Base.configurationVariables("waitTimeout")));

		webDriverWait.until(ExpectedConditions.attributeContains(mobileElement, strAttribute, strValue));
	}

	/**
	 * Wait until mobile element attribute value does not contain the expected
	 * value.
	 * 
	 * @param mobileElement - mobile element to check.
	 * @param strAttribute  - attribute of the mobile element to check.
	 * @param strValue      - value to expect not in the attribute.
	 */
	public void waitUntilMobileElementAttributeValueDoesNotContains(MobileElement mobileElement, String strAttribute,
			String strValue) {
		WebDriverWait webDriverWait = new WebDriverWait(appiumDriver,
				Long.parseLong(Base.configurationVariables("waitTimeout")));

		webDriverWait.until(
				ExpectedConditions.not(ExpectedConditions.attributeContains(mobileElement, strAttribute, strValue)));
	}

	/**
	 * Wait until mobile element can be clicked.
	 * 
	 * @param mobileElement - mobile element to check.
	 */
	public void waitUntilMobileElementClickable(MobileElement mobileElement) {
		WebDriverWait webDriverWait = new WebDriverWait(appiumDriver,
				Long.parseLong(Base.configurationVariables("waitTimeout")));

		webDriverWait.until(ExpectedConditions.elementToBeClickable(mobileElement));
	}

	/**
	 * Wait until mobile element is invisible.
	 * 
	 * @param mobileElement - mobile element to check.
	 */
	public void waitUntilMobileElementInvisible(MobileElement mobileElement) {
		WebDriverWait webDriverWait = new WebDriverWait(appiumDriver,
				Long.parseLong(Base.configurationVariables("waitTimeout")));

		webDriverWait.until(ExpectedConditions.invisibilityOf(mobileElement));
	}

	/**
	 * Wait until mobile element is not existing in the page.
	 * 
	 * @param strMobileElementXPath - mobile element XPath to check.
	 */
	public void waitUntilMobileElementXPathNotExisting(String strMobileElementXPath) {
		WebDriverWait webDriverWait = new WebDriverWait(appiumDriver,
				Long.parseLong(Base.configurationVariables("waitTimeout")));

		webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(strMobileElementXPath)));
	}

	/**
	 * Wait until mobile element is selected or not.
	 * 
	 * @param mobileElement - mobile element to check.
	 * @param blnIsSelected - selection state of the mobile element.
	 */
	public void waitUntilMobileElementSelectionStateToBe(MobileElement mobileElement, Boolean blnIsSelected) {
		WebDriverWait webDriverWait = new WebDriverWait(appiumDriver,
				Long.parseLong(Base.configurationVariables("waitTimeout")));

		webDriverWait.until(ExpectedConditions.elementSelectionStateToBe(mobileElement, blnIsSelected));
	}

	/**
	 * Wait until mobile element is visible.
	 * 
	 * @param mobileElement - mobile element to check.
	 */
	public void waitUntilMobileElementVisible(MobileElement mobileElement) {
		WebDriverWait webDriverWait = new WebDriverWait(appiumDriver,
				Long.parseLong(Base.configurationVariables("waitTimeout")));

		webDriverWait.until(ExpectedConditions.visibilityOf(mobileElement));
	}
}