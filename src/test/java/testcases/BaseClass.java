package testcases;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseClass {

	public static WebDriver driver;
	public XSSFWorkbook wbook;
	public XSSFSheet sheet;
	
	private ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();

	public void SetDriver(WebDriver driver) {
		threadLocalDriver.set(driver);
	}
	
	public WebDriver GetDriver() {

		return threadLocalDriver.get();

	}


	@BeforeMethod(alwaysRun = true)
	public void SetUpDriver() throws IOException {

		String browser = System.getProperty("Browser");
		//String browser = "chrome";
		//String mode = System.getProperty("Mode");

		if (browser.equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		}else if(browser.equalsIgnoreCase("remote-chrome")) {
			
			DesiredCapabilities cap = new DesiredCapabilities();
			cap.setPlatform(Platform.WIN11);
			cap.setBrowserName("chrome");

			URL hub = new URL("http://localhost:4444/");
			driver = new RemoteWebDriver(hub, cap);

			
		}
		else {
			driver = new ChromeDriver();
		}
		
		
		SetDriver(driver);
		// driver = new ChromeDriver();
		GetDriver().get("https://simplilearn.com/");
		GetDriver().manage().window().maximize();
		GetDriver().manage().timeouts().implicitlyWait(Duration.ofSeconds(5));

	}

	@AfterMethod(alwaysRun = true)
	public void TearDown() {

		GetDriver().quit();
	}

	@BeforeTest(alwaysRun = true)
	public void SetUpExcel() throws IOException {

		FileInputStream fis = new FileInputStream("exceldata.xlsx");
		wbook = new XSSFWorkbook(fis);
		sheet = wbook.getSheet("Sheet1");

	}

	@AfterTest(alwaysRun = true)
	public void CloseExcel() throws IOException {

		wbook.close();

	}

}
