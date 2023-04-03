package com.example.SeleniumCucumber.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Stepdefs {

    public static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("incognito");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Setting up....");

    }

    @AfterAll
    public static void tearDown() {
        System.out.println("Closing down....");
        driver.quit();
    }


    @Given("SVT play is available")
    public void svtPlayIsAvailable() {
        driver.get("https://svtplay.se");
        // Remove popup window

        WebElement acceptAllButton = driver.findElement(By.xpath("//button[contains(text(), 'Acceptera alla')]"));
        acceptAllButton.click();

        // TODO: Here we are doing an ugly wait to wait for the "saving your selection" after clicking 'acceptera alla' button above. Can be improved at some point.
        sleep(5);

    }

    @When("User visits SVT Play")
    public void userVisitsSVTPlay() {
        driver.get("https://svtplay.se");//Before each normally navigates to page, but doing it here as well for better test understandability.
    }

    @Then("The title should be {string}")
    public void theTitleShouldBe(String arg0) {
        assertEquals("SVT Play", driver.getTitle(), "Page title is not as expected");
    }

    public static void sleep(int numberOfSeconds) {
        try {
            Thread.sleep(numberOfSeconds * 1000); // Sleep for number of seconds seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
