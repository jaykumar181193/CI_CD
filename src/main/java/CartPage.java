import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class CartPage {
    WebDriver driver;

    public CartPage(WebDriver driver) {
        this.driver = driver;
    }

    @FindBy(xpath = ".//input[@placeHolder='Enter delivery pincode']")
    public WebElement delieveryPincodeTxtBox;

    @FindBy(xpath = ".//span[text()='Check']")
    private WebElement checkBtn;

    @FindBy(xpath = ".//div[@class='_1lBhq8']")
    public WebElement itemsInMyCart;

    @FindBy(xpath = ".//div[@class='_1nWa4x']")
    public WebElement pinCodeDropDown;

    @FindBy(xpath = ".//div[@class='_1g90e7']")
    private WebElement pinCodeSection;

    @FindBy(xpath = ".//input[@placeholder='Enter delivery pincode']")
    private WebElement pincodeTextBox;

    @FindBy(xpath = ".//span[text()='Check']")
    private WebElement checkPincodeBtn;

    @FindBy(xpath = ".//div[@class='aCNg3Z']")
    private List<WebElement> totalItemsInCart;

    public void providePinCode(String pinCode) {
        delieveryPincodeTxtBox.sendKeys(pinCode);
        checkBtn.click();
    }

    public List<String> getMessageForEachItem() {
        List<String> msg = new ArrayList<String>();
        int temp = 0;
        for (int i = 0; i < totalItemsInCart.size(); i++) {
            try {
                WebElement eleValue = driver.findElement(By.xpath("(.//div[@class='aCNg3Z'])[" + (i + 1) + "]/descendant::div[@class='bzD9az']"));
                msg.add(eleValue.getText().split("\\|")[0]);
            } catch (NoSuchElementException ee) {
                msg.add(driver.findElement(By.xpath("(.//div[@class='_1wlPCy'])[" + (temp + 1) + "]")).getText());
                temp++;
            }
        }
        return msg;
    }

    public void changePinCode()
    {
        pinCodeDropDown.click();
        WebDriverWait wait=new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(pinCodeSection));
        pincodeTextBox.sendKeys("380005");
        checkPincodeBtn.click();
    }

    public String getItemsInMyCart() {
        return itemsInMyCart.getText();
    }


}
