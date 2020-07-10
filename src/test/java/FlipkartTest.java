import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.*;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class FlipkartTest {

    public WebDriver driver;
    Logger log = Logger.getLogger(FlipkartTest.class.getName());

    private GooglePage googlePage;
    private FlipKartHomePage homePage;
    private WindowACPage windowACPage;
    private ComparePage comparePage;
    private CommonPage commonPage;
    private CartPage cartPage;

    @BeforeClass
    @Parameters({"browserType"})
    public void initiateBrowser(String browser)
    {
        if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        }
        else if (browser.equalsIgnoreCase("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        googlePage = PageFactory.initElements(driver,GooglePage.class);
        homePage =PageFactory.initElements(driver,FlipKartHomePage.class);
        windowACPage=PageFactory.initElements(driver,WindowACPage.class);
        comparePage=PageFactory.initElements(driver,ComparePage.class);
        commonPage=PageFactory.initElements(driver,CommonPage.class);
        cartPage=PageFactory.initElements(driver,CartPage.class);
    }

    @Test()
    public void FlipkartScenario()  {
        WebDriverWait wait=new WebDriverWait(driver, 5);
        driver.get("https://google.com");
        googlePage.provideValueInTxtBox("Flipkart");
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(googlePage.optionsForTextBox)));
        printMessage(googlePage.fetchOptionsFromSearchBox(),"The options for the search box are ");
        googlePage.pressEnterOnTextBox();
        googlePage.clickOnFlipkartLink();
        if (homePage.cancelPopup.isDisplayed())
            homePage.clickCancelOnPopup();
        homePage.selectWindowAcOption();
        windowACPage.compareAC();
        comparePage.brandAndProductSelection();
        printMessage(comparePage.getNameOfAC(),"The Name of the window AC compared are ");
        printMessage(comparePage.getPriceOfAC(),"The Price of the window AC compared are ");
        addItemsInCart();
        cartPage.providePinCode("382421");
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(cartPage.pinCodeDropDown)));
        printMessage(cartPage.getMessageForEachItem(),"Availability of product delivery");
        cartPage.changePinCode();
        wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(cartPage.pinCodeDropDown)));
        printMessage(cartPage.getMessageForEachItem(),"Availability of product delivery after change of Pincode");
    }

    private void printMessage(List<String> stringList,String title){
        log.info(title);
        for (int i =0;i<stringList.size();i++){
            System.out.println(stringList.get(i));
        }
    }

    private void addItemsInCart()
    {
        for (int i=0;i<3;i++)
        {
            Assert.assertEquals(comparePage.isComparePageDisplayed(),true);
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,2000)");
            comparePage.addToCartButtons.get(0).click();
            WebDriverWait wait=new WebDriverWait(driver, 5);
            wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOfAllElements(cartPage.itemsInMyCart)));
            Assert.assertEquals(cartPage.getItemsInMyCart(),"My Cart ("+(i+1)+")");
            if (i<2)
                driver.navigate().back();
        }
    }


    @AfterClass
    public void closeBrowser()
    {
        driver.quit();
    }
}


