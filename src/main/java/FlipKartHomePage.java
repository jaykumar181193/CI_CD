import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class FlipKartHomePage {
    WebDriver driver;
    public FlipKartHomePage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = ".//div[@class='_3Njdz7']/button")
    private WebElement cancelOnLoginPopup;

    @FindBy(xpath = ".//div[@class='_3Njdz7']")
    public WebElement cancelPopup;

    @FindBy(xpath = ".//span[contains(text(),'Appliances')]/parent::li")
    private WebElement tvAndAppliances;

    @FindBy(xpath = ".//a[@title='Window ACs']")
    private WebElement windowAcBtn;

    @FindBy(xpath = ".//a[@title='Window ACs']/parent::li/parent::ul")
    private WebElement tvAndAppliancesDropDownOptions;

    public void clickCancelOnPopup()
    {
        cancelOnLoginPopup.click();
    }

    public void selectWindowAcOption(){
        Actions action = new Actions(driver);
        action.moveToElement(tvAndAppliances).click().build().perform();
        WebDriverWait wait=new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(tvAndAppliancesDropDownOptions)).click();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(windowAcBtn))).click();
    }
}
