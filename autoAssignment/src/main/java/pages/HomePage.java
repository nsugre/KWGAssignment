package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BaseClass;
import common.CommonBusiness;


public class HomePage extends CommonBusiness {
	
	private WebDriver 		driver;
	private WebDriverWait	wait;
	
	private By				cartLink				= By.id("nav-cart");
	private By				searchDDSelectTag		= By.tagName("select");
	private By				searchField				= By.id("twotabsearchtextbox");
	private By				searchButton			= By.id("nav-search-submit-button");
	private By				headerDDDiv				= By.id("nav-search-dropdown-card");
	private By 				productLink				= By.xpath("(//*[@id='search']//h2/a)[1]");
		
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}
	
	public CartPage navigateToCartPage() {
		clickOn(cartLink);
		pauseFor(3);
		return new CartPage(driver);
	}
	
	public void selectValuefromDropDownofSearchField(String value) {
		WebElement selectTag = getElement(getElement(headerDDDiv), searchDDSelectTag);
		selectValueFromDropDown(selectTag,value);	
	}
	
	public void searchItem(String item) {
		enter(searchField, item);
		clickOn(searchButton);
	}
	
	public ProductPage selectASearchResult() {
		clickOn(productLink);
		pauseFor(3);
		switchToNewTab(driver.getWindowHandle());
		return new ProductPage(driver);
	}
	
}
