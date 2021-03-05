package pages;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import utils.Driver;

import java.time.Duration;
public class BasePage {

    protected final AppiumDriver<MobileElement> driver;
    private final int KEYBOARD_ANIMATION_DELAY;
    private final  int XML_REFRESH_DELAY ;

    public BasePage(AppiumDriver<MobileElement> driver) {
        this.driver = driver;
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)),this);
        this.KEYBOARD_ANIMATION_DELAY = Integer.parseInt(Driver.getProperties().getProperty("keyboardDelay"));
        this.XML_REFRESH_DELAY = Integer.parseInt(Driver.getProperties().getProperty("xmlRefreshDelay"));


    }

    protected boolean sendKeysToElement(String input, MobileElement element, boolean appendNewLine) throws InterruptedException {
        final int MAX_ATTEMPTS = 3;
        int attempts = 0;

        do {
            element.clear();
            element.clear();
            Thread.sleep(KEYBOARD_ANIMATION_DELAY);

            if (appendNewLine) {
                element.sendKeys(input + "\n");
            } else {
                element.sendKeys(input);
            }

            Thread.sleep(XML_REFRESH_DELAY);
        } while (!element.getText().contains(input) && ++attempts < MAX_ATTEMPTS);

        return element.getText().contains(input);
    }
}
