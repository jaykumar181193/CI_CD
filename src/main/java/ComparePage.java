import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class ComparePage {

    private WebDriver driver;
    public ComparePage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = ".//div[text()='Choose Brand']")
    private WebElement chooseBrandDrpDown;

    @FindBy(xpath = "(.//div[text()='Choose a Product'])[1]")
    private WebElement chooseProductDrpDown;

    @FindBy(xpath = ".//div[text()='Choose a Product']/following-sibling::div")
    private WebElement productDropDownOptions;

    @FindBy(xpath = ".//div[text()='Choose a Product']/following-sibling::div/div/div[contains(text()," +
            "'Samsung 1.5 Ton 5 Star Split Triple Inverter Dura Series AC  - White')]")
    private WebElement productSelect;

    @FindBy(xpath = ".//div[@class='col col-3-12 _1ouGhW']/a")
    private List<WebElement> nameOfACs;

    @FindBy(xpath = ".//div[@class='col col-3-12 _1ouGhW']/div/div/div[1]")
    private List<WebElement> priceOfACs;

    @FindBy(xpath = ".//button[text()='ADD TO CART']")
    public List<WebElement> addToCartButtons;

    @FindBy(xpath = ".//div[@id='fk-compare-page']")
    private WebElement comparePageId;

    public void brandSelection(){
        Actions action = new Actions(driver);
        action.moveToElement(chooseBrandDrpDown).click().pause(1000).sendKeys("Samsung").build().perform();
        WebDriverWait wait=new WebDriverWait(driver, 5);
        WebElement ele= driver.findElement(By.xpath(".//div[@class='_3ozNsv']/child::div[@title='Samsung']"));
        wait.until(ExpectedConditions.visibilityOf(ele)).click();
    }

    public void productSelection()  {
        WebDriverWait wait=new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(chooseProductDrpDown))).click();
        try {
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(productSelect))).click();
        }catch (TimeoutException e){
            productSelection();
        }
    }

    public void brandAndProductSelection()  {
        brandSelection();
        productSelection();
    }

    public List<String> getPriceOfAC()  {
        List<String> price = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            WebDriverWait wait=new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.visibilityOf(priceOfACs.get(i)));
            price.add(priceOfACs.get(i).getText());
        }
        return price;
    }

    public List<String> getNameOfAC()  {
        List<String> name = new ArrayList<String>();
        for (int i = 0; i < 3; i++) {
            WebDriverWait wait=new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.numberOfElementsToBe(By.xpath(".//div[@class='col col-3-12 _1ouGhW']/a"),3));
            name.add(nameOfACs.get(i).getText());
        }
        return name;
    }

    public boolean isComparePageDisplayed()  {
        if (comparePageId.isDisplayed())
            return true;
        else
            return false;
    }


}
