package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.touch.offset.PointOption;
import junit.framework.Assert;

public class SearchPage extends BasePage {

	public SearchPage(AndroidDriver driver) {
		super(driver);

	}

	public void clickSearch(AndroidDriver driver, String productname) {
		List<WebElement> skip_signin = driver
				.findElements(By.id("in.amazon.mShop.android.shopping:id/skip_sign_in_button"));
		if (skip_signin.size() > 0) {
			driver.findElement(By.id("in.amazon.mShop.android.shopping:id/skip_sign_in_button")).click();

		}

		driver.findElement(By.id("in.amazon.mShop.android.shopping:id/chrome_search_hint_view")).click();
		WebDriverWait wc = new WebDriverWait(driver, Duration.ofSeconds(2));
		wc.until(ExpectedConditions
				.visibilityOfElementLocated(By.id("in.amazon.mShop.android.shopping:id/rs_search_src_text")));
		WebElement search = driver.findElement(By.id("in.amazon.mShop.android.shopping:id/rs_search_src_text"));
		search.sendKeys(productname);
		Actions keyboard = new Actions(driver);
		keyboard.keyDown(search, Keys.ENTER).build().perform();
		driver.hideKeyboard();

	}

	public void Searchproducts(AndroidDriver driver) {

		List<WebElement> allproducts = driver.findElements(By.xpath("//android.widget.Button[@index='1']"));

		try {
			Thread.sleep(10000);
		} catch (InterruptedException e1) {

			e1.printStackTrace();
		}

		for (WebElement product : allproducts) {

			if (product.getText().equals("airpods pro")) {
				product.click();
				break;

			}

		}
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

	}

	public void addproducttoCart(AndroidDriver driver) throws InterruptedException {
		Thread.sleep(10000);
		scrollUp(driver);
		List<WebElement> searchelement = driver.findElements(By.xpath("//android.view.View[contains(@text,'MICRODIXD')]"));
		while (searchelement.size() == 0) {
			scrollUp(driver);
		}

		driver.findElement(By.xpath("//android.view.View[contains(@text,'RAVIAD')]")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		String expectedproductname = driver
				.findElement(By.xpath("//android.view.View[contains(@text,'RAVIAD Wireless')]")).getText();
		try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}
		scrollUp(driver);
		driver.findElement(By.xpath("//android.widget.Button[@text='Add to Cart']")).click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		driver.findElement(By.xpath(
				"//android.widget.FrameLayout[@content-desc=\"Cart 1 item Tab 3 of 4\"]/android.widget.TextView"))
				.click();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		String actualproductadded = driver.findElement(By.xpath("//android.widget.TextView[contains(@text,'RAVIAD')]"))
				.getText();
		System.out.println("Actual added product " + actualproductadded);
		Assert.assertEquals(expectedproductname, actualproductadded);

	}

	public void scrollUp(AndroidDriver driver) {
		int height = driver.manage().window().getSize().getHeight();
		int width = driver.manage().window().getSize().getWidth();

		int startX = (int) (0.5 * width);
		int startY = (int) (0.9 * height);

		int endX = startX;
		int endY = (int) (0.2 * height);
		
		TouchAction action = new TouchAction(driver);
		System.out.println(height+","+width+","+startX+","+startY+","+endX+","+endY);
		action.longPress(PointOption.point(startX, startY)).moveTo(PointOption.point(endX, endY)).release().perform();
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
}
