package com.Qa.Redfin.PageTests;

import java.io.IOException;
import java.util.Properties;

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

import org.testng.asserts.*;

public class PropertiesPageTest extends TestBase
{
	HomePage homePageObj;
	PropertiesPage propertiesPageObj;
	Properties props;
	@BeforeClass()
	public void initializeBrowser() 
	{
		launchBrowser();
		test = report.startTest("PropertiesPageTestCase");
		try {
			props = ConfigPropertyReader.ReadProperty("/Users/sayanna/work/bindu-work/Redfin-Project/Redfin-project/src/test/resources/TestData/testdata.properties");
			} catch (IOException e) 
				{
			// TODO Auto-generated catch block
				e.printStackTrace();
				}
		homePageObj = new HomePage(driver);
		homePageObj.EnterText(props.getProperty("searchboxText"));
	}
	@Test(priority =1, dataProvider="searchKey")
	public void enterCityname(String city) 
	{
		propertiesPageObj = homePageObj.listFromPopup(city);
		
	}
	@DataProvider(name ="searchKey")
	public Object[][] getDataFromDataProvider()
	{
		String s1 = props.getProperty("city1");
		String s2 = props.getProperty("city2");
		return new Object[][] {
			{s1},
			{s2}
		};
	}
	@Test(priority =2)
	public void validatepage() {
		String city = props.getProperty("city1");
		if(city.equalsIgnoreCase("Sunnyvale, CA, USA"))
		{
			String expectedTitle = "Sunnyvale Homes for Sale: Sunnyvale, CA Real Estate | Redfin";
			String actual = propertiesPageObj.validatePageTitle();
			Assert.assertEquals(actual, expectedTitle);
			System.out.println("CAlifornia state page title validated");
			test.log(LogStatus.PASS, "California Page title validated");
		}
		else {
			String expectedTitle = "Sunnyvale Homes for Sale: Sunnyvale, Tx Real Estate | Redfin";
			String actualTitle = propertiesPageObj.validatePageTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
			System.out.println("Texas state page title validated");
			test.log(LogStatus.PASS, "Texas Page title validated");
		}
		
	}
	@Test(priority =3)
	public void SelectMinMaxPrices() {
		String minPrice = props.getProperty("minDropdownIndex");
		int min = Integer.parseInt(minPrice);
		String maxPrice = props.getProperty("MaxDropDownIndex");
		int max = Integer.parseInt(maxPrice);
		propertiesPageObj.selectPrice(min, max);
		}
	@Test(priority =4)
	public  void verifyHomes() {
		propertiesPageObj.displaylisitings();
	}
	@AfterClass
	public void tearDown() {
		driver.quit();
	}
	
}
