package com.Qa.Redfin.PageTests;
import java.io.IOException;
import java.util.Properties;


import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
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
	@BeforeMethod
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
	@Test(dataProvider ="searchKey")
	public void selectGivenCity(String city)
	{
		String actual = homePageObj.validatePageTitle();
		String expected  = "Real Estate, Homes for Sale, MLS Listings, Agents | Redfin";
		Assert.assertEquals(actual,expected);
		System.out.println("homepage title is :"+actual);
		test.log(LogStatus.PASS, "home page title validated");
		// To enter text in searchbox
		homePageObj.EnterText(props.getProperty("searchboxText"));
		test.log(LogStatus.INFO, "Text entered in searchBox");
		// to Ensure popup displayed
		boolean b = homePageObj.popup();
		Assert.assertTrue(b);
		test.log(LogStatus.INFO, "popup displayed");
		//To display and click on given city
		homePageObj.listFromPopup(city);
		test.log(LogStatus.INFO, " clicked on Sunnyvale,CA,USA from");
	}
	@DataProvider(name ="searchKey")
	public Object[][] getDataFromDataProvider()
	{
		return new Object[][] {
			{"Sunnyvale, CA, USA"},
			{"Sunnyvale, TX, USA"}
		};
	}
	
	@AfterMethod
	public void teardown() 
	{
		driver.close();
	}
}

