package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

import io.appium.java_client.android.AndroidDriver;

public class LoginPage extends BasePage {
	public LoginPage(AndroidDriver driver) {
		super(driver);

	}

	By btncancel = By.id("in.amazon.mShop.android.shopping:id/btn_cancel");
	
	@CacheLookup
	@FindBy(id = "in.amazon.mShop.android.shopping:id/btn_cancel")
	private WebElement buttoncancel,language,contLang;

	public void login(AndroidDriver driver) {
		language = driver.findElement(By.xpath("//android.widget.ImageView[@content-desc=\"Select English\"]"));
		language.click();
		
		contLang = driver.findElement(By.id("in.amazon.mShop.android.shopping:id/continue_button"));
		contLang.click();
		
			List<WebElement> skip_signin = driver
					.findElements(By.id("in.amazon.mShop.android.shopping:id/skip_sign_in_button"));

			if (skip_signin.size() > 0) {
				driver.findElement(By.id("in.amazon.mShop.android.shopping:id/skip_sign_in_button")).click();

			}		

	}
}