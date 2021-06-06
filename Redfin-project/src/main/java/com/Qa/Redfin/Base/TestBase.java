package com.Qa.Redfin.Base;

import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.Qa.Redfin.Utils.CommonUtils;
import com.Qa.Redfin.Utils.ConfigPropertyReader;
import com.relevantcodes.extentreports.ExtentTest;

import Factory.DriverFactory;

public class TestBase extends CommonUtils 
{
	public static Properties props;

	public static WebDriver driver;
	public static void launchBrowser() {
		props= new Properties();
		try {
			props = ConfigPropertyReader.ReadProperty("/Users/sayanna/work/bindu-work/Redfin-Project/Redfin-project/src/test/resources/Resources/Config.Properties");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String browser = props.getProperty("browser");
		driver = DriverFactory.initializeDriver(browser);
		driver.get(props.getProperty("url"));
	}
	@BeforeSuite
	public  static void CreateReport() {
		CommonUtils.CreateReport();
		test = report.startTest("PropertyDetailsPageTest");
	}
	@AfterSuite
	public void closeReport() {
		CommonUtils.CloseReport();
	}
	
}
