package com.Qa.Redfin.PageTests;
import java.io.IOException;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.Qa.Redfin.Base.TestBase;
import com.Qa.Redfin.Utils.ConfigPropertyReader;
import com.Qa.Redfin.pages.HomePage;
import com.Qa.Redfin.pages.PropertiesPage;
import com.relevantcodes.extentreports.LogStatus;
public class HomePageTest extends TestBase
{	
	//WebDriver driver;
	HomePage homePageObj;
	Properties props;
	public HomePageTest() 
	{
		super();
	}
	@BeforeClass()
	public void initializeBrowser() 
	{
		launchBrowser();
		test = report.startTest("HomePageTestCase");
		homePageObj = new HomePage(driver);
		try {
			props = ConfigPropertyReader.ReadProperty("/Users/sayanna/work/bindu-work/Redfin-Project/Redfin-project/src/test/resources/TestData/testdata.properties");
			} catch (IOException e) 
				{
			// TODO Auto-generated catch block
				e.printStackTrace();
				}
	}
	@Test(priority =1)
	public void ValidatePageTitle() 
	{
		String actual = homePageObj.validatePageTitle();
		String expected  = "Real Estate, Homes for Sale, MLS Listings, Agents | Redfin";
		Assert.assertEquals(actual,expected);
		System.out.println("homepage title is :"+actual);
		test.log(LogStatus.PASS, "home page title validated");
	}
	@Test(priority =2)
	public void enterTextInSearchBox() throws InterruptedException 
	{
		
			System.out.println(props.getProperty("searchboxText"));
			homePageObj.EnterText(props.getProperty("searchboxText"));
			test.log(LogStatus.INFO, "Text entered in searchBox");
	}
	@Test(priority=3)
	public void validatePopUp() 
	{
		boolean b = homePageObj.popup();
		Assert.assertTrue(b);
		test.log(LogStatus.INFO, "popup displayed");
	}
	@Test(priority =4,dataProvider="searchKey")
	
	public void clickOncity(String city) 
	{	
		homePageObj.listFromPopup(city);
		test.log(LogStatus.INFO, " clicked on Sunnyvale,CA,USA from");
	}
	@DataProvider(name ="searchKey",parallel=true)
	public Object[][] getDataFromDataProvider()
	{
		String s1 = props.getProperty("city1");
		String s2 = props.getProperty("city2");
		return new Object[][] {
			{s1},
			{s2}
		};
	}
	
	@AfterClass
	public void teardown() 
	{
		driver.quit();
	}
}

