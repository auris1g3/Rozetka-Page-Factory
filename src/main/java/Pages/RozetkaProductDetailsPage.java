package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class RozetkaProductDetailsPage {

    WebDriver webDriver;
    WebDriverWait wait;

    @FindBy(xpath = "//ul[@class='product-actions']")
    WebElement waitingLoadDetailsProductPage;
    @FindBy(xpath = "//ul[@class='product-actions']")
    WebElement comparingForm;
    @FindBy(xpath = "//button[@class='compare-button']")
    WebElement compareButton;
    @FindBy(xpath = "//p[@class='product-prices__big product-prices__big_color_red']")
    WebElement priceMonitor;
    @FindBy(xpath = "//h1[@class='product__title']")
    WebElement nameMonitor;
    @FindBy(xpath = "//a[@class='header-topline__user-link link-dashed']")
    WebElement loginLink;
    @FindBy(xpath = "//span[@class='header-actions__button-counter']")
    WebElement counterComparingProducts;
    @FindBy(xpath = "//a[@class='header-actions__button header-actions__button_type_compare header-actions__button_state_active']")
    WebElement baseCompareButton;
    @FindBy(linkText = "Мониторы (2)")
    WebElement comparingProducts;

    public RozetkaProductDetailsPage(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
        PageFactory.initElements(webDriver, this);
    }

    public void waitLoadProductDetailsPage() {
        wait.until(visibilityOf(waitingLoadDetailsProductPage));
    }

    public void scrollToComparingForm() {
        WebElement comparingFormElement = comparingForm;
        scrollToElement(comparingFormElement);
    }

    public void clickOnCompareButton() {
        WebElement compareButtonElement = compareButton;
        compareButtonElement.click();
    }

    public String getPriceMonitor() {
        return priceMonitor.getText().substring(0, 4);
    }

    public String getNameMonitor() {
        return nameMonitor.getText();
    }

    public String getCounterComparingProducts() {
        return counterComparingProducts.getText();
    }

    public void navigateBack() {
        webDriver.navigate().back();
    }

    public void scrollToCountCompareProducts() {
        WebElement countCompareProductsElement = loginLink;
        scrollToElement(countCompareProductsElement);
    }

    public void scrollToElement(WebElement element) {
        ( (JavascriptExecutor) webDriver ).executeScript("arguments[0].scrollIntoView(true);", element);
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void clickOnBaseCompareButton() {
        Actions actions = new Actions(webDriver);
        WebElement LinkProductsCategory = baseCompareButton;
        actions.moveToElement(LinkProductsCategory).perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        comparingProducts.click();
    }
}
