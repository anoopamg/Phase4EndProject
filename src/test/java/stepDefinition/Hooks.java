package stepDefinition;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class Hooks {
	public static AndroidDriver driver;

	@Before
	public void Setup() throws MalformedURLException {
		URL url = new URL("http://localhost:4723/wd/hub");
		DesiredCapabilities capabilities = new DesiredCapabilities();
		capabilities.setCapability("platformName", "Android");
		capabilities.setCapability("platformVersion", "11");
		capabilities.setCapability("appPackage", "in.amazon.mShop.android.shopping");
		capabilities.setCapability("appActivity", "com.amazon.mShop.splashscreen.StartupActivity");
		driver = new AndroidDriver(url, capabilities);
		System.out.println("Session ID is " + driver.getSessionId());

		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));

	}

	@After
	public void teardown() {
		driver.quit();
	}
}