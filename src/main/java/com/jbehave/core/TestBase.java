package com.jbehave.core;

import java.io.File;
import java.net.URL;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.phantomjs.PhantomJSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;

public class TestBase {
	
	protected WebDriver driver = null;
	
	public void initializeBrowser(String value, String executionFlag) throws Exception {
		DesiredCapabilities caps = null;
			if (value.equals("chrome")) {
				System.setProperty("webdriver.chrome.driver",
						System.getProperty("user.dir") + "\\MyDrivers\\chromedriver.exe");
				if(executionFlag.equals("local")) {
					System.out.println("Local Chrome constructed");
					driver = new ChromeDriver();
				}
				else {
					System.out.println("Remote Webdriver Constructed");
					caps = DesiredCapabilities.chrome();
					caps.setBrowserName("chrome");
					caps.setVersion("45.0");
					caps.setJavascriptEnabled(true);
					caps.setCapability("os.version", Platform.WINDOWS);
					driver = new RemoteWebDriver(new URL("http://192.168.128.219:8080/wd/hub"), caps);
				}
				driver.manage().window().maximize();
			} else if (value.equals("firefox")) {
				if(executionFlag.equals("local")) {
					System.out.println("Local Firefox constructed");
					driver = new FirefoxDriver();
				}
				else {
					System.out.println("Remote Webdriver Constructed");
					caps = DesiredCapabilities.firefox();
					caps.setBrowserName("firefox");
					caps.setVersion("41.0");
					caps.setJavascriptEnabled(true);
					caps.setCapability("os.version", Platform.WINDOWS);
					driver = new RemoteWebDriver(new URL("http://192.168.128.219:8080/wd/hub"), caps);
				}
				driver.manage().window().maximize();
			} else if (value.equals("ie")) {
				System.setProperty("webdriver.ie.driver",
						System.getProperty("user.dir") + "\\MyDrivers\\IEDriverServer.exe");
				if(executionFlag.equals("local")) {
					System.out.println("Local IE constructed");
					driver = new InternetExplorerDriver();
				}
				else {
					System.out.println("Remote Webdriver Constructed");
					caps = DesiredCapabilities.internetExplorer();
					caps.setBrowserName("internet explorer");
					caps.setVersion("11");
					caps.setJavascriptEnabled(true);
					caps.setCapability("os.version", Platform.WINDOWS);
					driver = new RemoteWebDriver(new URL("http://192.168.128.219:8080/wd/hub"), caps);
				}
				driver.manage().window().maximize();
			} else if (value.equals("phantom")) {
				System.out.println("Local Phantom constructed");
				File file = new File(System.getProperty("user.dir")
						+ "\\MyDrivers\\phantomjs-2.1.1-windows\\phantomjs-2.1.1-windows\\bin\\phantomjs.exe");
				System.setProperty("phantomjs.binary.path", file.getAbsolutePath());
				driver = new PhantomJSDriver();
				driver.manage().window().maximize();
			} else {
				Assert.assertTrue(false, "Invalid browser");
			}
	}
	
	public WebElement getElement(String locator) throws Exception {
		Thread.sleep(2000L);
		return driver.findElement(By.cssSelector(locator));
	}

	public List<WebElement> getElements(String locator) throws Exception {
		return driver.findElements(By.cssSelector(locator));
	}

	public void waitForPageToLoad() throws Exception {
		((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
	}	
	
	public String getTitle() throws Exception{
		return driver.getTitle().trim();
	}
	public void getURL(String url) throws Exception{
		driver.get(url);
		waitForPageToLoad();
	}
	
	public void tearDown() throws Exception{
		driver.quit();
	}
}
