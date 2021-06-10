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

import org.testng.asserts.*;

public class PropertiesPageTest extends TestBase
{
	HomePage homePageObj;
	PropertiesPage propertiesPageObj;
	Properties props;
	@BeforeMethod
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
	@DataProvider(name ="searchKey")
	public Object[][] getDataFromDataProvider()
	{
		return new Object[][] {
			{"Sunnyvale, CA, USA"},
			{"Sunnyvale, TX, USA"}
		};
	}

	@Test(dataProvider ="searchKey")
	public void DisplaypropertiesForGivenCity(String city) {
		propertiesPageObj = homePageObj.listFromPopup(city);
		//To validate page titles of given cities
		if(city.equalsIgnoreCase("Sunnyvale, CA, USA"))
		{
			String expectedTitle = "Sunnyvale Homes for Sale: Sunnyvale, CA Real Estate | Redfin";
			String actual = propertiesPageObj.validatePageTitle();
			Assert.assertEquals(actual, expectedTitle);
			System.out.println("CAlifornia state page title validated");
			test.log(LogStatus.INFO, "California Page title validated");
		}
		else if(city.equalsIgnoreCase("Sunnyvale, TX, USA"))
		{
			String expectedTitle = "Sunnyvale Homes for Sale: Sunnyvale, TX Real Estate | Redfin";
			String actualTitle = propertiesPageObj.validatePageTitle();
			Assert.assertEquals(actualTitle, expectedTitle);
			System.out.println("Texas state page title validated");
			test.log(LogStatus.INFO, "Texas Page title validated");
		}
		// select max and minium prices
		String minPrice = props.getProperty("minDropdownIndex");
		int min = Integer.parseInt(minPrice);
		String maxPrice = props.getProperty("MaxDropDownIndex");
		int max = Integer.parseInt(maxPrice);
		propertiesPageObj.selectPrice(min, max);
		// To display Listings
		propertiesPageObj.displaylisitings();
		test.log(LogStatus.PASS, "Testcase passed");
	}
	@AfterMethod
	public void tearDown() {
		driver.close();
	}
	
}
