package com.todeleteLists;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class DeleteLists {

	public static void main(String[] args) throws InterruptedException {
		
		WebDriver driver;
		
		String driverLoc = "C:\\Training\\Jar Files\\Drivers\\chromedriver.exe";
		String url = "https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2F%3Fref_%3Dnav_ya_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&";
		
		System.setProperty("webdriver.chrome.driver",driverLoc);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		// the below is covered in LogintoAccount
				driver.findElement(By.id("ap_email")).sendKeys("7058408881");
				driver.findElement(By.id("continue")).click();
				driver.findElement(By.id("ap_password")).sendKeys("Muk$45la");
				driver.findElement(By.id("signInSubmit")).click();

				  String captchaText = "To better protect your account, please re-enter your password and then enter the characters as they are shown in the image below.";
				  try {
					  
					  WebElement CaptchaHeadTxt = driver.findElement(By.xpath("//span[@class= 'a-list-item']")); // Captcha msg
					
					  
				   while(captchaText.equals(CaptchaHeadTxt.getText())) { 
				    
				      //logger.trace("We are on captcha page");

				      // prompt user to enter captcha
				      String captchaVal1 = JOptionPane.showInputDialog("Please enter the captcha value : ");
				      WebElement CaptchaTextbox = driver.findElement(By.id("auth-captcha-guess"));
				      CaptchaTextbox.sendKeys(captchaVal1);
				      

				      
				      
				      WebElement Password = driver.findElement(By.id("ap_password"));
				      Password.sendKeys("Muk$45la");
				      WebElement Loginbutton = driver.findElement(By.id("signInSubmit"));
				      Loginbutton.click();
				    		    }
				  } catch (Exception e) {
				   System.out.println("Captcha didn't appear..");
				  }
				
				  Actions move3actnlist = new Actions(driver);
					WebElement AfterPhoneList = driver.findElement(By.id("nav-link-accountList"));
					Thread.sleep(2000);
					move3actnlist.moveToElement(AfterPhoneList).build().perform();
					//@FindBy (xpath="//span[text()='Create a Wish List' and @class='nav-text']")
					//private WebElement CreateWishListbtn;
					WebElement CreateWishListbtn  =driver.findElement(By.xpath("//span[text()='Shopping List' and @class='nav-text']"));
					
					
					if(CreateWishListbtn.isDisplayed()) {
					}else {
					try {
						AfterPhoneList.click();
					} catch (Exception e) {
						// TODO: handle exception
					}
					}
					Thread.sleep(2000);
					CreateWishListbtn.click(); // clicking "create list option wish
				  
				  try 
					{ 
					while (driver.findElements(By.xpath("//*[@id='overflow-menu-popover-trigger']/div[2]")).size() > 0)
						{
							Thread.sleep(2000);
							Actions moredropdown = new Actions(driver);
							WebElement MoreOption = driver.findElement(By.xpath("//*[@id=\"overflow-menu-popover-trigger\"]/div[2]")); // More Element
							moredropdown.moveToElement(MoreOption).build().perform();
							driver.findElement(By.xpath("//a[@id = 'editYourList']")).click(); // manage list element
							WebElement Element = driver.findElement(By.xpath("//*[@id=\"list-settings-container\"]/span/span/span/input"));
							JavascriptExecutor js = (JavascriptExecutor) driver;
							js.executeScript("arguments[0].scrollIntoView();", Element);
							Element.click();
							Thread.sleep(2000);
							WebElement ElementYESbutton = driver.findElement(By.xpath("//input[@name='submit.save' and @type=\"submit\"]"));
							JavascriptExecutor js1 = (JavascriptExecutor) driver;				
							js1.executeScript("arguments[0].click();", ElementYESbutton);
							//System.out.println("CLicking on Delete button");
						}
					} 
					catch (Exception e)	// switching to another tab
					{ 
					}
					driver.quit();
				}
		

	}


