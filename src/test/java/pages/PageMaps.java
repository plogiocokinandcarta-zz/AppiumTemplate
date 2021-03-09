package pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.iOSXCUITFindBy;

public class PageMaps extends BasePage{


    public PageMaps(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    @iOSXCUITFindBy(accessibility = "")
    @AndroidFindBy(xpath = "//*[@text=\"SKIP\"]")
    public MobileElement skip;

    @iOSXCUITFindBy(accessibility = "")
    @AndroidFindBy(xpath = "//*[@content-desc='Re-center map to your location']")
    public MobileElement centerButon;

    @iOSXCUITFindBy(accessibility = "")
    @AndroidFindBy(xpath = "//*[[@content-desc='Location services disabled. Enable location services to re-center map to your location.']]")
    public MobileElement findLocation;

    @iOSXCUITFindBy(accessibility = "")
    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_one_time_button")
    public MobileElement permision;

    @iOSXCUITFindBy(accessibility = "")
    @AndroidFindBy(id = "//*[@text=\"Search here\"]")
    public MobileElement searchInput;


    public void goToMyLocation() throws Exception {
        try{

            skip.click();
            Thread.sleep(3000);

            if (isElementPresent(findLocation)){
                findLocation.click();
            }else{
                centerButon.click();
            }

            Thread.sleep(3000);
            permision.click();
            Thread.sleep(10000);
        }
        catch(Exception e)
        {
            throw new Exception("Element not found :" + e.getMessage());
        }
    }


}
