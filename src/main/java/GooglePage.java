import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.Keys;
import java.util.ArrayList;
import java.util.List;

public class GooglePage {
    WebDriver driver;
    public GooglePage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = ".//input[@name='q']")
    WebElement searchTxtBox;

    @FindBy(xpath = ".//ul[@role='listbox']")
    WebElement listBox;

    @FindBy(xpath = ".//ul[@role='listbox']/descendant::div[@role='option']/div/span")
    List<WebElement> optionsForTextBox;

    @FindBy(xpath = ".//a[@href='https://www.flipkart.com/']")
    WebElement flipkartHyperLink;

    @FindBy(xpath = ".//button[contains(text(),'✕')]")
    public List<WebElement> cancelOnLoginPopup;

    public void provideValueInTxtBox(String textToEnter)
    {
        searchTxtBox.sendKeys(textToEnter);
    }

    public void pressEnterOnTextBox()
    {
        searchTxtBox.sendKeys(Keys.ENTER);
    }
    public List<String> fetchOptionsFromSearchBox() {
        List<String> options = new ArrayList<String>();
        for (int i = 0; i < optionsForTextBox.size(); i++) {
            options.add(optionsForTextBox.get(i).getText());
        }
        return options;
    }

    public void clickOnFlipkartLink()
    {
        flipkartHyperLink.click();
    }

}
