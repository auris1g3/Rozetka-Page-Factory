package Pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class RozetkaComparePage {

    WebDriver webDriver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[text()='Очистить все']")
    WebElement waitingLoadComparePage;
    @FindBy(xpath = "//a[@class='comparison-g-title g-title novisited']")
    List<WebElement> nameProducts;
    @FindBy(xpath = "//div[@class='g-price-uah']")
    List<WebElement> priceProducts;

    public RozetkaComparePage(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
        PageFactory.initElements(webDriver, this);
    }

    public void waitLoadComparePage() {
        wait.until(visibilityOf(waitingLoadComparePage));
    }

    public String getProductNameOnComparePage(int numberProductInList) {
        String productName;
        List<WebElement> listProduct = nameProducts;
        productName = listProduct.get(numberProductInList).getText();
        return productName;
    }

    public String getProductPriceOnComparePage(int numberProductInList) {
        String productPrice;
        List<WebElement> listProduct = priceProducts;
        productPrice = listProduct.get(numberProductInList).getText().substring(0, 4).trim();
        return productPrice;
    }
}
