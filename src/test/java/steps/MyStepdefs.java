package steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.java.Scenario;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import pages.PageSoundSlice;
import pages.PageMaps;
import utils.Driver;
import utils.DriverWeb;

import java.io.IOException;


public class MyStepdefs {

    AppiumDriver<MobileElement> driver;
    WebDriver webDriver;
    Scenario s;
    PageMaps maps;
    PageSoundSlice webMap;

    @Given("the google maps app on appium")
    public void theGoogleMapsAppOnAppium() throws Exception {
        driver = Driver.getDriver();
        driver.launchApp();
        Driver.changeLocation( 41.902652, -87.701975);
    }

    @When("search {string}")
    public void search(String arg0) throws Exception {
        Thread.sleep(5000);
        maps = new PageMaps(driver);
        maps.goToMyLocation();
    }

    @Then("the maps open in the correct place")
    public void theMapsOpenInTheCorrectPlace() {
        Driver.screenshot(s);
        driver.closeApp();
    }

    @Given("emulated google maps in selenium")
    public void emulatedGoogleMapsInSelenium() throws IOException {
        webDriver = DriverWeb.getDriver();
        webDriver.get("https://www.soundslice.com/");

    }

    @And("emulated network conection")
    public void emulatedNetworkConection() throws InterruptedException, IOException {
        DriverWeb.setUpBandwidth(10000,10000,true);
        webMap = new PageSoundSlice(webDriver);
        webMap.demoButton.click();

    }

    @Then("the app works in offline mode")
    public void theAppWorksInOfflineMode() {
    }
}
