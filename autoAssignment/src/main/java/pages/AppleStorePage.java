package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonBusiness;

public class AppleStorePage extends CommonBusiness {
	
	private WebDriver 		driver;
	private WebDriverWait	wait;
	
	private By		appleWatchMenuLink		= By.xpath("//ul[contains(@class,'Navigation__navList')]/descendant::span[text()='Apple Watch'][1]/..");
	private By		appleWatchSeries8Link	= By.xpath("//span[text()='Apple Watch Series 8 (GPS)']/..");
	private By		watchImage				= By.xpath("//div[@id='lszvjckk52']/div/div/div/a");
	private By 		addToCartBtn			= By.id("add-to-cart-button");
	private By		quanitySelectTag		= By.id("quantity");
	private By		addedToCartTxt			= By.xpath("//div[@id='attachDisplayAddBaseAlert']//span[contains(text(),'Added to Cart')]");
	private By		cartSubTotalItem		= By.id("attach-accessory-cart-total-string");
	private By		cartSubTotalPrice		= By.id("attach-accessory-cart-subtotal");
	private By		cartBtn					= By.id("attach-sidesheet-view-cart-button");
	

	public AppleStorePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public void addAppleWatchProductToCart(String quantity) {
		pauseFor(3);
		clickOn(appleWatchMenuLink);
		clickOn(appleWatchSeries8Link);
		clickOn(watchImage);
		selectValueFromDropDown(getElement(quanitySelectTag), quantity);
		clickOn(addToCartBtn);
	}
	
	public void validateProductIsAddedToCart() {
		validatePageContainsText(addedToCartTxt, "Added to Cart");
		validatePageContainsText(cartSubTotalItem, "4 items");
		validatePageContainsText(cartSubTotalPrice, "₹2,00,699.00");
	}
	
	public CartPage navigateToCartPage() {
		clickOn(cartBtn);
		return new CartPage(driver);
	}
}
