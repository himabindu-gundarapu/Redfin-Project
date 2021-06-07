package com.Qa.Redfin.pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.Qa.Redfin.Base.TestBase;
import com.Qa.Redfin.Utils.CommonUtils;

public class HomePage extends TestBase {

	@FindBy(xpath ="//input[@title ='City, Address, School, Agent, ZIP']")
	WebElement searchBox;
	@FindBy(xpath ="//h3[contains(text(),'Did You Mean')]")
	WebElement popup;
	@FindBy(xpath ="//div[@class='item-row clickable']//div")
	List<WebElement> listFromPopup;
	//To initialize page elements
	public HomePage(WebDriver driver) 
	{
		PageFactory.initElements(driver, this);
	}
	//validate home page title
	 public String validatePageTitle() {
		return  driver.getTitle(); 
		 
	 }
	 // To validate searchbox is displayed or not
	 public boolean Is_searchboxDisplay() {
		 System.out.println("searchbox :"+searchBox.isDisplayed());
		 return searchBox.isDisplayed();
	 }
	 //To check Did you mean popup appeared or not
	public boolean popup()
	{
		String str = popup.getText();	
		System.out.println(str);
		System.out.println("popup"+popup.getText());
		boolean b = popup.isDisplayed();
		System.out.println("b "+b);
		return b;
	}
	// To get all lists from did you mean pop up
	public PropertiesPage listFromPopup(String text)
	{
		System.out.println("size : "+listFromPopup.size());
		for(int i =0; i< listFromPopup.size();i++) 
		{
			
			if(listFromPopup.get(i).getText().equalsIgnoreCase(text)) 
			{
				System.out.println("list from did you mean popup:" +listFromPopup.get(i).getText());
				listFromPopup.get(i).click();
				
			}
		}
		return  new PropertiesPage(driver);
	}
	//Enter text in search box
	public void EnterText(String text) {
		CommonUtils.EnterText(searchBox, text);
		searchBox.sendKeys(Keys.RETURN);
	}
	
}
