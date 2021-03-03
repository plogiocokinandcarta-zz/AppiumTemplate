package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class PageMaps extends BasePage{


    public PageMaps(AppiumDriver driver) {
        super(driver);
    }

    @AndroidFindBy(xpath = "//*[@text=\"SKIP\"]")
    public AndroidElement skip;

    @AndroidFindBy(xpath = "//*[@content-desc='Re-center map to your location']")
    public AndroidElement centerButon;

    @AndroidFindBy(xpath = "//*[[@content-desc='Location services disabled. Enable location services to re-center map to your location.']]")
    public AndroidElement findLocation;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_one_time_button")
    public AndroidElement permision;




}
