package testcases;

import java.time.Duration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import pages.LoginPage;

@Listeners(Listener.class)
public class LoginTest extends BaseClass {

	@Test(groups= {"sanity"}, description = "Login failure test")
	public void TC01_LoginFailureTest() throws InterruptedException {

		LoginPage lp = new LoginPage();
		lp.LoginFunction("abc@xyz.com", "Abc@1234");
		lp.ValidateErrorMsg("The email or password you have entered is invalid.");

		/*
		 * System.out.println(driver.getTitle());
		 * System.out.println(driver.getCurrentUrl());
		 * 
		 * WebElement Error = driver.findElement(By.className("error_msg"));
		 * //System.out.println(Error.getText()); String ActMsg = Error.getText();
		 * String ExpMsg = "The email or password you have entered is invalid.";
		 * 
		 * Assert.assertEquals(ExpMsg, ActMsg);
		 */

		// Thread.sleep(5000);

	}

	@Test(groups= {"sanity"}, description = "Login success test")
	public void TC02_LoginSuccessTest() {

		LoginPage lp = new LoginPage();
		lp.LoginFunction("vibhavrao2495@gmail.com", "Browser@616");

	}

	@Test
	@Parameters({ "param1", "param2" })

	public void TC03_LoginSuccessTest(String Uname, String Pwd) {

		LoginPage lp = new LoginPage();
		lp.LoginFunction(Uname, Pwd);

	}

	Map<String, String> testdata = new HashMap<String, String>();

	@Test(dataProvider = "dp")
	public void TC04_LoginSuccessTest(String key) {

		LoginPage lp = new LoginPage();
		lp.LoginFunction(key, testdata.get(key));

	}

	@DataProvider(name = "dp")
	public Iterator<String> method1() {

		testdata.put("aaa@xyz.com", "Abc@1234");
		testdata.put("bbb@xyz.com", "Abc@1234");
		testdata.put("ccc@xyz.com", "Abc@1234");
		testdata.put("ddd@xyz.com", "Abc@1234");

		return testdata.keySet().iterator();

	}
  
	   @Test
        public void TC05_LoginFailureTest() {
		
		LoginPage lp = new LoginPage();
		
		String UserNameVal = sheet.getRow(1).getCell(0).getStringCellValue();
		String PasswordVal = sheet.getRow(1).getCell(1).getStringCellValue();
		
		lp.LoginFunction(UserNameVal, PasswordVal);
		lp.ValidateErrorMsg("The email or password you have entered is invalid.");


}
}
