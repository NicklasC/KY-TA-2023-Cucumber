package com.example.SeleniumCucumber.stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.AfterAll;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Stepdefs {

    public static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("incognito");
        //options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("Setting up....");

        // Here we are also taking care of initial Cookie popup on SVT site
        driver.get("https://svtplay.se");

        // Now we are removing the popup window
        WebElement acceptAllButton = driver.findElement(By.xpath("//button[contains(text(), 'Acceptera alla')]"));
        acceptAllButton.click();

        // TODO: Here we are doing an ugly wait to wait for the "saving your selection" after clicking 'acceptera alla' button above. Can be improved at some point.
        sleep(5);

    }

    @AfterAll
    public static void tearDown() {
        System.out.println("Closing down....");
        driver.quit();
    }


    @Given("user visits SVT Play")
    public void userVisitsSVTPlay() {
        driver.get("https://svtplay.se");//Before each normally navigates to page, but doing it here as well for better test understandability.
    }

    @Then("the browser title should be {string}")
    public void theBrowserTitleShouldBe(String expectedTitle) {
        assertEquals(expectedTitle, driver.getTitle(), "Page title is not as expected");
    }

    public static void sleep(int numberOfSeconds) {
        try {
            Thread.sleep(numberOfSeconds * 1000); // Sleep for number of seconds
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Then("logo should be visible")
    public void logoShouldBeVisible() {
        WebElement logo = driver.findElement(By.xpath("//a[@data-rt=\"play-logo\" and @href=\"/\"]"));
        assertEquals(true, logo.isDisplayed(), "I could not find the logo..");
    }

    @Then("start link should exist and have the text {string}")
    public void startLinkShouldExistAndHaveTheText(String expectedText) {
        WebElement startLink = driver.findElement(By.xpath("//a[@href=\"/\" and @accesskey=1]"));
        assertEquals(expectedText, startLink.getText(), "Start link did not have correct text");
    }

    @Then("program link should exist and have the text {string}")
    public void programLinkShouldExistAndHaveTheText(String expectedText) {
        WebElement startLink = driver.findElement(By.xpath("//a[@href=\"/program\" and @accesskey=2]"));
        assertEquals(expectedText, startLink.getText(), "PROGRAM link did not have correct text");

    }

    @Then("kanaler link should exist and have the text {string}")
    public void kanalerLinkShouldExistAndHaveTheText(String expectedText) {
        WebElement kanalertLink = driver.findElement(By.xpath("//a[@href=\"/kanaler\" and @accesskey=3]"));
        assertEquals(expectedText, kanalertLink.getText(), "KANALER link did not have correct text");

    }

    @Then("tillganglighet link should exist")
    public void tillgänglighetLinkShouldExist() {
        WebElement link = driver.findElement(By.xpath("//a[@href='https://kontakt.svt.se/guide/tillganglighet']"));
        assertEquals(true, link.isDisplayed(), "The link 'Tillgänglighet i SVT Play' does not display...");
    }

    @And("user clicks tillganglighet link")
    public void userClicksTillganglighetLink() {
        WebElement link = driver.findElement(By.xpath("//a[@href='https://kontakt.svt.se/guide/tillganglighet']"));
        link.click();
    }

    @Then("tillgänglighet page should display with header text {string}")
    public void tillgänglighetPageShouldDisplayWithHeaderText(String expectedHeaderText) {
        WebElement header = driver.findElement(By.xpath("//h1"));
        assertEquals(expectedHeaderText, header.getText(), "Headertexten på tillgänglighetssidan stämmer ej");
    }

    @And("user clicks on Program link")
    public void userClicksOnProgramLink() {
        WebElement startLink = driver.findElement(By.xpath("//a[@href=\"/program\" and @accesskey=2]"));
        startLink.click();
    }


    @Then("{string} categories are shown")
    public void categoriesAreShown(String expectedNumberOfCategories) {
        WebElement kategorierSection = driver.findElement(By.cssSelector("section[aria-label='Kategorier']"));
        List<WebElement> h2Elements = kategorierSection.findElements(By.tagName("h2"));

        assertEquals(Integer.parseInt(expectedNumberOfCategories), h2Elements.size(), expectedNumberOfCategories + " of categories did not show...");

    }

    @And("user search for the text {string}")
    public void userSearchForTheText(String searchForText) {
        WebElement searchField = driver.findElement(By.cssSelector("input[name='q']"));
        WebElement searchButton = driver.findElement(By.xpath("//button[@type='submit' and @title='Sök på svtplay.se']"));

        searchField.sendKeys(searchForText);
        searchButton.click();

    }

    @Then("search results header for text {string} should display")
    public void searchResultsHeaderShouldDisplay(String searchedForText) {
        // This method only verifies header, not the exact amount of hits.
        WebElement searchResult = driver.findElement(By.xpath("//h2[@data-rt='header-search-result']"));
        String searchResultHeaderText = searchResult.getText();

        String searchResultTextSubSelection = searchResultHeaderText.substring(0, 19 + searchedForText.length());

        assertEquals("Din sökning på " + searchedForText + " gav", searchResultTextSubSelection, "Some form of search results seems to display");

    }

    @Then("cookie information link should display")
    public void cookieInformationLinkShouldDisplay() {
        WebElement link = driver.findElement(By.xpath("//a[@href='https://kontakt.svt.se/guide/cookies-och-personuppgifter']"));
        String text = link.getText();
        String textSelection = text.substring(0, 30);

        assertEquals("Om cookies och personuppgifter", textSelection, "cookie information link text is not correct");
    }

    @And("user clicks cookie information link")
    public void userClicksCookieInformationLink() {
        WebElement link = driver.findElement(By.xpath("//a[@href='https://kontakt.svt.se/guide/cookies-och-personuppgifter']"));
        link.click();
    }

    @Then("cookie information page should display")
    public void cookieInformationPageShouldDisplay() {
        WebElement header = driver.findElement(By.className("text-3xl"));
        assertEquals("Om cookies och personuppgiftsbehandling på SVT", header.getText(), "cookie information header text is not correct");
    }

    @And("user clicks kanaler link")
    public void userClicksKanalerLink() {
        WebElement kanalerLink = driver.findElement(By.xpath("//a[@href=\"/kanaler\" and @accesskey=3]"));
        kanalerLink.click();
    }

    @Then("TVTablå for {string} should display")
    public void tvtablåForShouldDisplay(String TVChannel) {
        WebElement section = driver.findElement(By.cssSelector("section[aria-label='Tablå för " + TVChannel + "']"));
        assertEquals(true, section.isDisplayed(), "Tablå for " + TVChannel + " did not display");

    }

    @Then("nyhetsbrev link should display")
    public void nyhetsbrevLinkShouldDisplay() {
        WebElement link = driver.findElement(By.xpath("//a[@href='https://nyhetsbrev.svtplay.se/prenumerera/?utm_source=svtplay&utm_medium=footer-cta']"));
        String text = link.getText();
        String textSelection = text.substring(0, 10);
        assertEquals("Nyhetsbrev", textSelection, "Nyhetsbrev link text is not correct");
    }

    @And("user clicks Nyhetsbrev link")
    public void userClicksNyhetsbrevLink() {
        WebElement link = driver.findElement(By.xpath("//a[@href='https://nyhetsbrev.svtplay.se/prenumerera/?utm_source=svtplay&utm_medium=footer-cta']"));
        link.click();

    }

    @Then("nyhetsbrev page should display")
    public void nyhetsbrevPageShouldDisplay() {
        WebElement header = driver.findElement(By.xpath("//h1"));
        assertEquals("Missa inga program och serier!", header.getText(), "nyhetsbrev title page not correct");
    }

    @Then("program number {string} in the program list should be {string}")
    public void firstSearchResultShowInListIs(String position, String expectedShowName) {
        // Getting all H2 elements
        List<WebElement> h2Elements = driver.findElements(By.tagName("h2"));

        // position 0 is search result header
        String firstSearchResultText = h2Elements.get(Integer.parseInt(position)).getText();
        assertEquals(expectedShowName, firstSearchResultText, "program number " + position + " should be named " + expectedShowName + ", but was not");
    }

    @When("user clicks Pistvakt")
    public void userClicksPistvakt() {
        // Getting all H2 object
        List<WebElement> h2Elements = driver.findElements(By.tagName("h2"));

        // Clicking on Pistvakt
        WebElement pistvaktElement = h2Elements.get(1);
        pistvaktElement.click();

    }


    @And("clicks Pistvakt season one")
    public void clicksPistvaktSeasonOne() {
        WebElement selectSeason1link = driver.findElement(By.cssSelector("a[href='/pistvakt']"));
        selectSeason1link.click();
    }


    @And("clicks Pistvakt season two")
    public void clicksPistvaktSeasonTwo() {
        WebElement selectSeason2link = driver.findElement(By.cssSelector("a[href='/pistvakt?tabs=season-2-jx3za5B']"));
        selectSeason2link.click();
    }

    @Then("{string} number of episodes are displaying")
    public void episodesAreDisplaying(String expectedNumberOfEpisodes) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Waiting for the first h3 element to appear (To complete page loading)
        WebElement firstH3Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));

        // Now getting all H3 elements
        List<WebElement> season2EpisodesList = driver.findElements(By.tagName("h3"));

        // Going through all H3 elements. We are only interested in elements that contains text. I did not know how to filter that using findElements, so I did it programatically instead.
        int numberOfEpisodes = 0;
        for (WebElement episode : season2EpisodesList) {
            if (episode.getText().length() > 0) {
                numberOfEpisodes++;
            }
        }
        assertEquals(Integer.parseInt(expectedNumberOfEpisodes), numberOfEpisodes, "Incorrect number of episodes");
    }

    @Then("episode {string} is called {string}")
    public void episodeIsCalled(String episodeNumber, String expectedTitle) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Waiting for the first h3 element to appear (To complete page loading)
        WebElement firstH3Element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("h3")));

        List<WebElement> season2EpisodesList = driver.findElements(By.tagName("h3"));
        int numberOfEpisodes = 0;
        for (WebElement episode : season2EpisodesList) {
            if (episode.getText().length() > 0) {
                numberOfEpisodes++;
            }
        }
        assertEquals(expectedTitle, season2EpisodesList.get(Integer.parseInt(episodeNumber)-1).getText(), "episode " + episodeNumber + " name should be " + expectedTitle + ", but it is not");
    }

    @Then("dator link should display")
    public void datorLinkShouldDisplay() {
        WebElement link = driver.findElement(By.xpath("//a[@href='https://kontakt.svt.se/guide/svt-play-webb']"));
        String text = link.getText();
        String textSelection = text.substring(0, 5);

        assertEquals("Dator", textSelection, "Dator information link text starts with Dator, OK!");
    }


    @When("user clicks on dator link")
    public void userClicksOnDatorLink() {
        WebElement link = driver.findElement(By.xpath("//a[@href='https://kontakt.svt.se/guide/svt-play-webb']"));
        link.click();
    }

    @Then("computer requirements page displays")
    public void computerRequirementsPageDisplays() {
        WebElement header = driver.findElement(By.cssSelector("H1"));
        assertEquals("Systemkrav för SVT Play på webben", header.getText(), "Page did not display with correct text");
    }

    @When("user clicks Karen Pirie: Kalla fall")
    public void userClicksKarenPirieKallaFall() {
        WebElement link = driver.findElement(By.xpath("//em[normalize-space()='Karen Pirie: Kalla fall']"));
        link.click();
    }
}
