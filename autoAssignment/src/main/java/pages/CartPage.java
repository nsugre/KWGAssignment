package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonBusiness;

public class CartPage extends CommonBusiness {
	
	private WebDriver 		driver;
	private WebDriverWait	wait;
	
	private By				emptyCartText		= By.xpath("//div[@id='sc-active-cart']//h2");
	private By				signInToYourActBtn	= By.xpath("//span[@id='a-autoid-0']//a");
	private By				signUpNowBtn		= By.xpath("//span[@id='a-autoid-1']//a");
	private By				pageHeader			= By.tagName("h1");
	private By				cartSubTotalItems	= By.id("sc-subtotal-label-activecart");
	private By				cartSubTotalPrice	= By.xpath("//span[@id='sc-subtotal-amount-activecart']/span");
	private By				AppleWatchQuantity	= By.xpath("(//span[contains(text(),'Apple Watch Series 8 ')])[1]/ancestor::ul/following-sibling::div//select");
	
	
	

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public void validateEmptyCartPage(String expTxt) {
		validatePageText(emptyCartText, expTxt);
		validateElementExist(signInToYourActBtn);
		validateElementExist(signUpNowBtn);
	}
	
	public void validateCartPageHeader() {
		validatePageText(pageHeader, "Shopping Cart");
	}
	
	public void validateCartSubTotal(String items, String price) {
		validatePageContainsText(cartSubTotalItems, items);				//"6 items"
		validatePageContainsText(cartSubTotalPrice, price);				//"2,86,679.00"
	}
	
	public void reduceAppleItemsQuantity(String num) {
		selectValueFromDropDown(getElement(AppleWatchQuantity), num);
		pauseFor(2);
	}
	
	

}
