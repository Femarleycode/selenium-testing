package org.example;



import static java.lang.Thread.*;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.NoSuchElementException;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class AppTest 
{

   private WebDriver driver;

   @Before
    public void setUp(){
       driver = new ChromeDriver();
   }

   @Test
   public void seleniumExampleTest() throws InterruptedException{
       driver.manage().window().maximize();
       sleep( 2000);
       driver.get("http://www.google.co.uk");
       sleep( 1000);
       WebElement googleSearchBar = driver.findElement(By.name("q"));
       googleSearchBar.sendKeys( "funny dog pics");
       sleep(  1000);
       googleSearchBar.submit();
       WebElement linkToPictures = driver.findElement(By.partialLinkText("Images for funny dog"));
       linkToPictures.click();
       sleep( 1000);
       WebElement imagesLink = driver.findElement(By.className("NZmxZe"));
       assertTrue(imagesLink.isDisplayed());
   }

   @Test
   public void seleniumExampleSendingMultipleKeyboardKeysTest() throws InterruptedException{
       driver.manage().window().maximize();
       sleep( 2000);
       driver.get("http://www.google.com");
       sleep( 1000);
       WebElement googleSearchBar = driver.findElement(By.name("q"));
       googleSearchBar.sendKeys(Keys.chord("funny ", "dog ", "pics", Keys.ENTER));
       sleep(1000);
       WebElement linkToPictures = driver.findElement(By.partialLinkText("Images for funny dog"));
       linkToPictures.click();
       sleep( 1000);
       WebElement imagesLink = driver.findElement(By.className("NZmxZe"));
       assertTrue(imagesLink.isDisplayed());
   }

   @Test
   public void seleniumDrawHouse() throws InterruptedException{
        driver.manage().window().maximize();
        sleep( 2000);
        driver.get("https://www.youidraw.com/apps/painter/");
        sleep ( 2000);

        Actions action = new Actions(driver);
        sleep( 2000);

       //draws house
       action.moveByOffset( 600, 400).clickAndHold().moveByOffset( 400, 0).perform();
       sleep( 2000);
       action.moveByOffset( 0, 300).perform();
       sleep( 2000);
       action.moveByOffset( -400, 0).perform();
       sleep( 2000);
       action.moveByOffset( 0, -300).perform();
       sleep( 2000);
       action.moveByOffset( 150, -150).perform();
       sleep( 2000);
       action.moveByOffset( 150, 150).perform();
       sleep( 2000);

       action.release().perform();
       sleep( 3000);
   }

    //Waiting for page to show elements
   @Test
   //bad way of doing it, it will only look for the element after a set period, poentially wasting time
   public void implicitWaitExample(){
       driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
       driver.manage().window().maximize();
       driver.get("http://www.google.com");
       WebElement searchBar = driver.findElement(By.name("q"));
       assertTrue(searchBar.isDisplayed());
   }

   @Test
   //better way of doing it, because if it finds the method, it stops waiting, wasting less time
   public void explicitWaitExample(){
       driver.get("http://www.google.com");
       WebElement searchBar = (new WebDriverWait(driver, 10))
               .until(ExpectedConditions.presenceOfElementLocated(By.name("q")));
   }

   @Test
   public void fluentWaitExample(){
       driver.get(driver.get("http://www.google.com");
       Wait<WebDriver> wait = new FluentWait<WebDriver>(driver)
               .withTimeout(30, SECONDS)
               .pollingEvery(5, SECONDS)
               .ignoring(NoSuchElementExeption.class);
       WebElement searchBar = wait.until(new Function<WebDriver, WebElement>(){
           @Override
           public WebElement apply(WebDriver webDriver){
               return driver.findElement(By.name("q"));
           }
       });
               assertTrue(searchBar.isDisplayed());
   }

   @After
   public void tearDown(){
       driver.close();
   }
}