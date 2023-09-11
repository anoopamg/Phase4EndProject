package stepDefinition;

import pageObjects.LoginPage;
import pageObjects.SearchPage;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.en.*;

public class AmazonStepDef {
	AndroidDriver driver = Hooks.driver;
	LoginPage lp = new LoginPage(driver);
	SearchPage sp = new SearchPage(driver);
	
	@Given("I login to amazon application")
	public void i_login_to_amazon_application() {
	    lp.login(driver);
	}

	@Given("I search for the product {string}")
	public void i_search_for_the_product(String productname) {
		
	    sp.clickSearch(driver, productname);
	}

	@Given("choose the product {string}")
	public void choose_the_product(String string) {
		sp.Searchproducts(driver);
	    
	}

	@When("I complete Add To Cart")
	public void i_complete_add_to_cart() throws InterruptedException {
		sp.addproducttoCart(driver);
	    
	}

	@Then("the product is added to the Cart")
	public void the_product_is_added_to_the_cart() {
	    
	}

}