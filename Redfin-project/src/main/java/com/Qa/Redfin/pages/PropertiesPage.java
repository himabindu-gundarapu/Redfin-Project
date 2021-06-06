package com.Qa.Redfin.pages;

import java.util.List;
import java.util.Properties;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Qa.Redfin.Base.TestBase;
public class PropertiesPage extends TestBase {
	
	Properties props;
	@FindBy(xpath ="//span[@data-content='Price']")
	WebElement Price;
	@FindBy(xpath ="//span[@class='rangeItemContainer'][1]")
	WebElement minPrice;
	@FindBy(xpath="//span[@class='rangeItemContainer'][2]")
	WebElement maxPrice;
	@FindBy(xpath="//div[@class ='HomeCardContainer']")
	List<WebElement> PropertiesList;
	
	// Intialize properties page elements using page factory
	public PropertiesPage (WebDriver driver) 
	{
		PageFactory.initElements(driver,this);
	}
	public String 	validatePageTitle() {
		System.out.println("Title =" +driver.getTitle());
		String title = driver.getTitle();
		return title;
	}
	public void selectPrice(int minIndex,int maxIndex) {
		try {
			Price.click();
			selectMinAndMaxPrice(minIndex,maxIndex);
		}
		catch(Exception e) {
			selectMinAndMaxPrice(minIndex,maxIndex);
		}
	}
	public void selectMinAndMaxPrice(int minIndex, int maxIndex) 
	{
			minPrice.click();
			JavascriptExecutor je = (JavascriptExecutor) driver;
			je.executeScript("document.getElementsByClassName('option')["+minIndex+"].click();" );
			maxPrice.click();
			je.executeScript("document.getElementsByClassName('option')["+maxIndex+"].click();" );
	}
	public void displaylisitings() {
			if(PropertiesList.size()>0) 
			{
				for(int i =0; i< PropertiesList.size();i++) 
				{
					System.out.println("Property :" +PropertiesList.get(i).getText());
				}
			}
			else 
			{
				System.out.println("Error : no properties listed");
			}
			}
	}
	
	

