package regression;

import org.testng.annotations.Test;

import base.BaseClass;
import pages.AppleStorePage;
import pages.CartPage;
import pages.HomePage;
import pages.ProductPage;

public class SampleTest extends BaseClass {
	
	@Test
	public void verifyE2ETest() {
		HomePage home = new HomePage(driver);
		CartPage cart = home.navigateToCartPage();
		
		cart.validateEmptyCartPage("Your Amazon Cart is empty");
		home.selectValuefromDropDownofSearchField("search-alias=electronics");
		home.searchItem("Iphone 14");
		ProductPage product = home.selectASearchResult();
		
		product.addProductToCart();
		product.validateProductIsAddedToCart();
		product.clickOnProductImage();
		AppleStorePage appleStore = product.navigateToAppleStore();
		
		appleStore.addAppleWatchProductToCart("3");
		appleStore.validateProductIsAddedToCart();
		cart = appleStore.navigateToCartPage();
		
		home.searchItem("Dell Laptops");
		home.selectASearchResult();
		product.addProductToCart();
		product.clickOnProductImage();
		
		cart = home.navigateToCartPage();
		cart.validateCartPageHeader();
		
		///Pass the expected cart validate info
		cart.validateCartSubTotal("5", "2,43,689.00");
		
		
		//Reduce Apple watches from 3 to 2 from cart
		cart.reduceAppleItemsQuantity("2");
		
		System.out.println("After reducing Apple watches quantity.");
		
		//Pass the expected cart validate info
		cart.validateCartSubTotal("4", "2,00,789.00");
		
		
	}
}
