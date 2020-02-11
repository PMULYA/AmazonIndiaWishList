package com.TestPages;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.pagesobject.WishListPage;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;

import java.util.concurrent.TimeUnit;

import javax.swing.JOptionPane;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

public class CreateWishList
{
	static String url = "https://www.amazon.in/ap/signin?openid.pape.max_auth_age=0&openid.return_to=https%3A%2F%2Fwww.amazon.in%2F%3F_encoding%3DUTF8%26ref_%3Dnav_ya_signin&openid.identity=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.assoc_handle=inflex&openid.mode=checkid_setup&openid.claimed_id=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0%2Fidentifier_select&openid.ns=http%3A%2F%2Fspecs.openid.net%2Fauth%2F2.0&";
	WebDriver driver;
	WishListPage WP;
	String explistname1 = "Private";
	String explistname2 = "Public";
	String explistname3 = "Private!!";
	String explistname4 = "Private";
	String explistname5 = "Apple iPhone 11 Pro Max (256GB) - Gold";
	
	ExtentReports extent = new ExtentReports();
	ExtentHtmlReporter reporter = new ExtentHtmlReporter("./Reports/Amazon_Wishlist_Suite.html");
	
	@BeforeClass
	public void beforeClass()
	{
		System.setProperty("webdriver.chrome.driver","C:\\Training\\Jar Files\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();	
		
		//driver.manage().window().maximize();
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
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
		
	}

	@Test(priority = 1) // -------------------------------------- Default Shopping List
	public void FirstTest() throws InterruptedException
	{
		extent.attachReporter(reporter);
		ExtentTest logger= extent.createTest("FirstTest - Default Shopping List");
		
		
		WP = new WishListPage(driver);
		WP.HoverAndClickOnCreateList();
		WP.CreateBackGroundList();	
		logger.log(Status.INFO, "Created a Default WishList");
		String DefValue = WP.AssertDefaultList();
		Assert.assertEquals(DefValue, explistname1);
		 if(DefValue.equals(explistname1)) {
			   logger.log(Status.PASS,"Completed test execution");   
			  }else
			  {
			   logger.fail("Test case FAILED");
			  }

	}

	
	@Test(dependsOnMethods = "FirstTest") // --------------------------------------Electronics
	public void SecondTest() throws InterruptedException
	{
		
		extent.attachReporter(reporter);
		ExtentTest logger= extent.createTest("SecondTest - Electronics List");
		WP.CreateElectroList();
		logger.log(Status.INFO, "Created a Electronics WishList");
		String ElecValue  = WP.AssertElecList();
		Assert.assertEquals(ElecValue, explistname2);
		if(ElecValue.equals(explistname2)) {
			   logger.log(Status.PASS,"Completed test execution");   
			  }else
			  {
			   logger.fail("Test case FAILED");
			  }
	}

	
	@Test(dependsOnMethods = "SecondTest") // -------------------------------------------Grocery
	public void ThirdTest() throws InterruptedException
	{
		extent.attachReporter(reporter);
		ExtentTest logger= extent.createTest("ThirdTest - Grocery List");
		WP.CreateGroceryList();
		logger.log(Status.INFO, "Created a Grocery WishList");
		String GrocValue  = WP.AssertGroceryList();
		Assert.assertEquals(GrocValue, explistname3);
		if(GrocValue.equals(explistname3)) {
			   logger.log(Status.PASS,"Completed test execution");   
			  }else
			  {
			   logger.fail("Test case FAILED");
			  }
	}

	
	@Test(dependsOnMethods = "ThirdTest") // ------------------------------------------------Phones
	public void FourthTest() throws InterruptedException
	{
		extent.attachReporter(reporter);
		ExtentTest logger= extent.createTest("FourthTest - Phones List");
		WP.CreateProductWish();
		WP.ClickAfterPhone();
		logger.log(Status.INFO, "Created a Phones WishList");
		String PhonesVal = WP.AssertPhones();
		Assert.assertEquals(PhonesVal, explistname4);
		if(PhonesVal.equals(explistname4)) {
			   logger.log(Status.PASS,"Completed test execution");   
			  }else
			  {
			   logger.fail("Test case FAILED");
			  }
	}

	
	@Test(dependsOnMethods = "FourthTest") // ---------------------------------------------------Phones2
	public void FifthTest() throws InterruptedException
	{
		extent.attachReporter(reporter);
		ExtentTest logger= extent.createTest("FifthTest - Phones2 List");
		WP.CreateProductTwo();		
		WP.ClickAfterItoElec();
		logger.log(Status.INFO, "Created a Phones2 WishList");
		String AssertInElec = WP.AssertInElecList();
		Assert.assertEquals(AssertInElec, explistname5);
		if(AssertInElec.equals(explistname5)) {
			   logger.log(Status.PASS,"Completed test execution");   
			  }else
			  {
			   logger.fail("Test case FAILED");
			  }
	
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		

	}

	@AfterMethod
	public void afterMethod() throws InterruptedException
	{

	}

	@AfterClass
	public void afterClass() throws InterruptedException
	{
		try 
		{ 
		while (driver.findElements(By.xpath("//*[@id='overflow-menu-popover-trigger']/div[2]")).size() > 0)
			{
				Thread.sleep(2000);
				WP.DeleteLists();
			}
		} 
		catch (Exception e)	// switching to another tab
		{ 
		}
		driver.quit();
	}

	@AfterSuite
	public void afterSuite() {
		//Report extent
		extent.flush();
		
	}
	
}
