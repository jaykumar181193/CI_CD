import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WindowACPage {
    WebDriver driver;
    public WindowACPage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = ".//span[text()='Add to Compare']/parent::label/preceding-sibling::span/div/label/input")
    private List<WebElement> compareCheckBoxes;

    @FindBy(xpath = ".//span[text()='COMPARE']/ancestor::a")
    private WebElement compareButton;

    public void clickCompareFor2And3AC()
    {
        Actions action = new Actions(driver);
        for (int i=1;i<3;i++) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,100)");
            action.moveToElement(compareCheckBoxes.get(i)).click().build().perform();
        }
    }

    public void compareAC()
    {
        clickCompareFor2And3AC();
        compareButton.click();
    }

}
