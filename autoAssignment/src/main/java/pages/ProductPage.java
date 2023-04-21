package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import common.CommonBusiness;

public class ProductPage extends CommonBusiness {

	private WebDriver 		driver;
	private WebDriverWait	wait;
	
	private By 				addToCartBtn		= By.id("add-to-cart-button");
	private By				addedToCartTxt		= By.xpath("//div[@id='attachDisplayAddBaseAlert']//span[contains(text(),'Added to Cart')]");
	private By				cartSubTotalItem	= By.id("attach-accessory-cart-total-string");
	private By				cartSubTotalPrice	= By.id("attach-accessory-cart-subtotal");
	private By				productImage		= By.id("attach-popover-lgtbox");
	private By				visitToAppleStoreLnk= By.linkText("Visit the Apple Store");
	private By				quanitySelectTag	= By.id("quantity");
	private By				sideSheetCartButton	= By.id("attach-sidesheet-view-cart-button");
	
	public ProductPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public void addProductToCart(String quantity) {
		selectValueFromDropDown(getElement(quanitySelectTag), quantity);
		clickOn(addToCartBtn);
	}
	
	public void addProductToCart() {
		clickOn(addToCartBtn);
	}
	
	public void clickOnCartbutton() {
		clickOn(sideSheetCartButton);
	}
	
	public void validateProductIsAddedToCart() {
		validatePageContainsText(addedToCartTxt, "Added to Cart");
		validatePageContainsText(cartSubTotalItem, "1 item");
		validatePageContainsText(cartSubTotalPrice, "₹71,999.00");
	}
	
	public void clickOnProductImage() {
		clickOn(productImage);
		pauseFor(2);
	}
	
	public AppleStorePage navigateToAppleStore() {
		clickOn(visitToAppleStoreLnk);
		return new AppleStorePage(driver);
	}
	
	
	
	
	
	

}
