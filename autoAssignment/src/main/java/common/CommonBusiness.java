package common;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class CommonBusiness {
	
	
	protected WebDriver driver;
	protected WebDriverWait wait;
	
	public CommonBusiness(WebDriver driver) {
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(15));
	}
	
	
	protected WebElement getElement(By loc) {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(loc));
	}
	
	protected WebElement getElement(WebElement element, By childEle) {
		wait.until(ExpectedConditions.visibilityOf(element));
		return element.findElement(childEle);
	}
	
	protected List<WebElement> getElements(By loc) {
		pauseFor(2);
		return driver.findElements(loc);
	}
	
	protected void enter(By box, String text) {
		getElement(box).sendKeys(text);
	}
	
	protected void enter(WebElement ele, String text) {
		ele.sendKeys(text);
	}
	
	protected void clickOn(By button) {
		getElement(button).click();
	}
	
	protected void clickOn(WebElement element) {
		element.click();
	}
	
	//Method to validate actual page title with expected one. This method accept one string parameter.
	protected void validatePageTitle(String expected) {
		String actual = driver.getTitle();
		Assert.assertEquals(actual, expected);
		System.out.println("Validation point passed. Expected: " + expected + " matches with Actual: " + actual);
	}
	
	//Method to validate actual page title with expected one. This method accept one string parameter.
	protected void validatePageURL(String expected) {
		String actual = driver.getCurrentUrl();
		Assert.assertEquals(actual, expected);
		System.out.println("Validation point passed. Expected: " + expected + " matches with Actual: " + actual);
	}
	
	//Method to validate actual page text with expected one. This method accept a string parameter and By locator.
	protected void validatePageText(By loc, String expected) {
		String actual = getElement(loc).getText();
		Assert.assertEquals(actual, expected);
		System.out.println("Validation point passed. Expected: " + expected + " matches with Actual: " + actual);
	}
	
	//Method to validate if page contains expected text. This method accept a string parameter and By locator.
	protected void validatePageContainsText(By loc, String expected) {
		String actual = getElement(loc).getText();
		if(actual.contains(expected)) {
			Assert.assertTrue(true);
			System.out.println("Validation point passed. Expected: " + expected + " contains in Actual: " + actual);
		} else {
			System.out.println("Validation point failed. Expected: " + expected + " not contains in Actual: " + actual);
			Assert.fail();
		}
	}
	
	//Method to validate given element exist on the page
	protected void validateElementExist(By loc) {
		List<WebElement> elements = getElements(loc);
		Assert.assertTrue(elements.size()>0);
		System.out.println("Validation point passed. Element "+ loc.toString() +" is present on the screen.");
	}
	
	//Method to Select value from the drop down
	protected void selectValueFromDropDown(WebElement element, String val) {
		Select selObj = new Select(element);
		selObj.selectByValue(val);
		System.out.println("Succesfully selected element from the drop down.");
	}
	
	//Method to check if two strings are equal
	protected boolean isEqualByIgnoringCase(String s1, String s2) {
		return (s1.equalsIgnoreCase(s2)) ? true : false;
	}
	
	protected void selectACheckBoxORRadio(WebElement element) {
		if(!element.isSelected()) {
			element.click();
		}
	}
	
	// Method responsible for switching control to new window. It takes parent window id as parameter.
	protected void switchToNewTab(String parent) {
		Set<String> handles = driver.getWindowHandles();
		if (handles.size() < 2) {
			System.out.println("There is only one window open. Can not perform switch operation.");
		} else {
			for (String handle : handles) {
				if (!handle.equals(parent)) {
					driver.switchTo().window(handle);
					System.out.print("Switched to new Tab/Window with ");
					System.out.println("Page Title:- "+ driver.getTitle());
				}
			}
		}
	}
	
	protected void pauseFor(int sec) {
		try {
			Thread.sleep((sec * 1000));
		} catch (Exception e) {

		}
	}
	
	protected void switchInToFrame(WebElement iFrame) {
		driver.switchTo().frame(iFrame);
	}
	
	protected void switchOutOfFrame() {
		driver.switchTo().defaultContent();
	}
	
	
	protected void hoverOver(WebElement target) {
		new Actions(driver).moveToElement(target).build().perform();
	}


}
