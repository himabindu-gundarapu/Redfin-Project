package com.Qa.Redfin.Utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.github.bonigarcia.wdm.WebDriverManager;

public class CommonUtils {
	public static WebDriver driver;
	public static ExtentTest test;
	public static  ExtentReports report;
	
	public static void InitializeDriver() 
	{
		
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
		
		
	}
	public static void OpenUrl (String url) 
	{
		driver.get(url);
		driver.manage().window().maximize();
	}
	
	public static void CreateReport() {
		String fileName = new SimpleDateFormat("'RedfinReport_'YYYYMMddHHmm'.html'").format(new Date());
		String path = "/Users/sayanna/work/bindu-work/Redfin-Project/Redfin-project/Reports/ExtentReports"+ fileName;
		report = new ExtentReports(path,true);
		
	}
	public static void CloseReport() {
		report.flush();
	}
	public static void CloseBrowser()
	{
		driver.quit();
	}
	
	public static void EnterText(WebElement element,String text) 
	{
		if (element == null || !element.isDisplayed())
		{
			System.out.println("Textbox is not found ");

		} else 
		{
			System.out.println("Textbox is found ");
			element.click();
			element.sendKeys(text);
		}
	}
	public static  void ClickButton(WebElement element, String objName) 
	{
		if (element == null || !element.isDisplayed()) 
		{
			//logger.log(LogStatus.ERROR, objName + " Element is not found.");
			System.out.println("element is not found ");
		} else
		{
			System.out.println("element is  found ");
			//logger.log(LogStatus.INFO, objName + " Element is found");
			element.click(); 
		}
	}
	
	public static void Text() throws InterruptedException {
		  //To handle unexpected alert on page load.
		  try{   
		   driver.switchTo().alert().dismiss();  
		  }catch(Exception e){ 
		   System.out.println("unexpected alert not present");   
		  }
	}
	public static void SwitchFrame( String id) {
		driver.switchTo().frame(id);
		System.out.println("switched to frame");
	}
	public static void SwitchFrame(WebElement element) {
		driver.switchTo().frame(element);
	}
	public static void SwtitchFrame() {
		driver.switchTo().defaultContent();
	}
	
	
	public static void SwitchWindow(String oldwindow ,String Url) {
		
		Set<String> getAllWindows = driver.getWindowHandles();
		String[] getWindow = getAllWindows.toArray(new String[getAllWindows.size()]);
		// System.out.println(driver.getCurrentUrl());
		driver.switchTo().window(getWindow[1]);
		System.out.println(driver.getCurrentUrl());
		driver.get("Url");
		driver.manage().window().maximize();

		System.out.println(driver.getCurrentUrl());
		driver.switchTo().window(oldwindow);

		driver.close();
	}
}
