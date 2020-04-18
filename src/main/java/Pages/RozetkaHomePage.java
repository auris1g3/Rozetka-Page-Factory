package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class RozetkaHomePage {

    WebDriver webDriver;
    WebDriverWait wait;

    @FindBy(xpath = "//a[@class='menu-categories__link']")
    WebElement productsCategory;
    @FindBy(linkText = "Все категории")
    WebElement waitingForProductsFromCategory;
    @FindBy(xpath = "//a[@class='menu__link' and text()=' Мониторы ']")
    WebElement monitorsProducts;
    @FindBy(name = "search")
    WebElement inputSearch;

    public RozetkaHomePage(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
        PageFactory.initElements(webDriver, this);
    }

    public void hoverToProductsCategory() {
        Actions actions = new Actions(webDriver);
        WebElement LinkProductsCategory = productsCategory;
        actions.moveToElement(LinkProductsCategory).perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitProductsFromCategory() {
        wait.until(visibilityOf(waitingForProductsFromCategory));
    }

    public void clickOnMonitorsProducts() {
        monitorsProducts.click();
    }

    public void getResultSearchItems(String text) {
        inputSearch.sendKeys(text + Keys.ENTER);
    }
}

