package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PageSoundSlice extends BaseWebPage {


    public PageSoundSlice(WebDriver driver) {
        super(driver);
    }

    @FindBy(xpath="//*[@class=\"standard-button hero-button\"]")
    public WebElement demoButton;
}
