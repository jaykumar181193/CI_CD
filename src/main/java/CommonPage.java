import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CommonPage {
    WebDriver driver;
    public CommonPage(WebDriver driver){
        this.driver = driver;
    }

    @FindBy(xpath = ".//title")
    private WebElement titleOfPage;

    public String getTitleOfPage()
    {
        return titleOfPage.getText();
    }
}
