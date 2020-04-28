package org.example;



import static java.lang.Thread.*;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

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

   @After
   public void tearDown(){
       driver.close();
   }
}
