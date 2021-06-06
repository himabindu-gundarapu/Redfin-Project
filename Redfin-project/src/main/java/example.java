import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class example {
	public static WebDriver driver;
	public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();
		driver = new ChromeDriver();
				driver.get("https://www.redfin.com");
				driver.manage().deleteAllCookies();
				driver.manage().window().maximize();
				
				driver.manage().deleteAllCookies();
				driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
				driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
				WebElement searchbox =driver.findElement(By.id("search-box-input"));
				searchbox.sendKeys("Sunn");
				searchbox.sendKeys(Keys.RETURN);
				boolean b = driver.findElement(By.xpath("//h3[@aria-label ='Did You Mean']")).isDisplayed();
				System.out.println(b);
				List<WebElement> ls = driver.findElements(By.xpath("//div[@class='item-row clickable']//div"));
				for(int i =0;i< ls.size();i++) {
					System.out.println("text :" +ls.get(i).getText());
					if(ls.get(i).getText().equals("Sunnyvale, CA, USA")) 
					{
						ls.get(i).click();
						break;
					}
				}
				WebElement text = driver.findElement(By.xpath("//span[@data-content='Price']"));
				String str = text.getText();
				System.out.println("str = " + str);
				text.click();
				
				WebElement drpdwn1 = driver.findElement(By.xpath("//span[@class='rangeItemContainer'][1]"));
				drpdwn1.click();

				//WebElement drpdwnmin = driver.findElement(By.name("quickMinPrice"));
				JavascriptExecutor je = (JavascriptExecutor) driver;
				je.executeScript("document.getElementsByClassName('option')[10].click();" );
				WebElement drpdwn2 = driver.findElement(By.xpath("//span[@class='rangeItemContainer'][2]"));
				drpdwn2.click();
				je.executeScript("document.getElementsByClassName('option')[27].click();" );
				Thread.sleep(2000);
				List<WebElement> propertylist = driver.findElements(By.xpath("//div[@class ='HomeCardContainer']"));
				System.out.println("Size =" +propertylist.size());
				for(int i =0; i< propertylist.size();i++)
				{
					System.out.println("list :"+propertylist.get(i).getText());
					}
				driver.close();
				
	}

}
