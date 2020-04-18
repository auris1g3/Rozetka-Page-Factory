package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static java.lang.Integer.parseInt;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class RozetkaProductListPage {

    WebDriver webDriver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[@class='header-topline__user-link link-dashed']")
    WebElement waitingForLoadProductList;
    @FindBy(xpath = "//span[@class='goods-tile__price-value']")
    List<WebElement> priceAllMonitors;
    @FindBy(xpath = "//a[@class='goods-tile__picture']")
    List<WebElement> linkFirstMonitor;

    /*By waitingForLoadProductList = By.xpath("//a[@class='header-topline__user-link link-dashed']");
    By priceAllMonitors = By.xpath("//span[@class='goods-tile__price-value']");
    By linkFirstMonitor = By.xpath("//a[@class='goods-tile__picture']");*/

    public RozetkaProductListPage(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
        PageFactory.initElements(webDriver, this);
    }

    public void waitLoadProductListPage() {
        wait.until(visibilityOf(waitingForLoadProductList));
    }

    public int clickOnMonitorWithPriceLessThen(int price) {
        int savePrice = 0;
        List<WebElement> listPriceAllMonitors = priceAllMonitors;
        for (int i = 0; i < listPriceAllMonitors.size(); i++) {
            int tempPrice = parseInt(listPriceAllMonitors.get(i).getText().replaceAll(" ", ""));
            if (tempPrice < price) {
                savePrice = tempPrice;
                WebElement element = linkFirstMonitor.get(i);
                element.click();
                break;
            }
        }
        return savePrice;
    }
}
