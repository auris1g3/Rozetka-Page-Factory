package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfElementLocated;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class RozetkaFiltersPage {

    WebDriver webDriver;
    WebDriverWait wait;

    @FindBy(xpath = "//span[@class='goods-tile__title']")
    List<WebElement> titleAllProducts;
    @FindBy(xpath = "//img[@title='Интернет-супермаркет ROZETKA']")
    WebElement waitingForLoadProductList;
    @FindBy(xpath = "//a[@name='show_more_categories']/ancestor::ul/li[3]//span[text()='Еще']")
    WebElement elseButtonMonitorAndNotebook;
    @FindBy(xpath = "//a[text()='Планшеты']")
    WebElement buttonTablet;
    @FindBy(xpath = "//label[@for='Acer']")
    WebElement checkBoxAcer;
    @FindBy(xpath = "//label[@for='Asus']")
    WebElement checkBoxAsus;
    @FindBy(xpath = "//span[@class='catalog-more__text']")
    WebElement allProductsAddedByFilters;
    @FindBy(xpath = "//input[@formcontrolname='min']")
    WebElement minPrice;
    @FindBy(xpath = "//input[@formcontrolname='max']")
    WebElement maxPrice;
    @FindBy(xpath = "//button[@type='submit' and text()=' Ok ']")
    WebElement okButtonPrice;
    @FindBy(xpath = "//span[@class='goods-tile__price-value']")
    List<WebElement> allPriceValueByPriceFilter;
    @FindBy(xpath = "//label[@for='4 ГБ']")
    WebElement ramFilter;
    @FindBy(xpath = "//label[@for='AMOLED']")
    WebElement screenType;
    @FindBy(xpath = "//label[@for='Android 9.x']")
    WebElement androidType;
    @FindBy(xpath = "//p[@class='goods-tile__description goods-tile__description_type_text']")
    List<WebElement> descriptionProduct;
    @FindBy(xpath = "//li[@class='catalog-grid__cell catalog-grid__cell_type_slim']")
    WebElement blockOfProduct;

    public RozetkaFiltersPage(WebDriver webDriver, WebDriverWait wait) {
        this.webDriver = webDriver;
        this.wait = wait;
        PageFactory.initElements(webDriver, this);
    }

    public void clickOnElseButtonMonitorAndNotebook() {
        elseButtonMonitorAndNotebook.click();
    }

    public void clickOnButtonTablet() {
        buttonTablet.click();
    }

    public void clickOnCheckBoxAcer() {
        checkBoxAcer.click();
    }

    public void clickOnCheckBoxAsus() {
        checkBoxAsus.click();
    }

    public void clickOnAllProductsAddedByFilters() {
        allProductsAddedByFilters.click();
    }

    public void waitLoadProductListPage() {
        wait.until(visibilityOf(waitingForLoadProductList));
    }

    public boolean verifyFilterManufacturer(String firstTitle, String secondTitle, String thirdTitle) throws Exception {
        List<WebElement> allTitle = titleAllProducts;
        for (WebElement element : allTitle) {
            String titleProduct = element.getText().toLowerCase();
            if (!( titleProduct.contains(firstTitle) || titleProduct.contains(secondTitle) || titleProduct.contains(thirdTitle) )) {
                throw new Exception("No Acer or Samsung or Asus");
            }
        }
        return true;
    }

    public boolean verifyPriceFilter(int minPriceValue, int maxPriceValue) throws Exception {
        List<WebElement> allPrice = allPriceValueByPriceFilter;
        for (WebElement webElement : allPrice) {
            int price = Integer.parseInt(webElement.getText().replaceAll(" ", ""));
            if (!( price > minPriceValue && price < maxPriceValue )) {
                throw new Exception("Selected products do not fall within the range of selected prices");
            }
        }
        return true;
    }

    public boolean verifyThreeFilters() throws Exception {
        List<WebElement> productsWithThreeFilters = descriptionProduct;
        for (WebElement productsWithThreeFilter : productsWithThreeFilters) {
            String text = productsWithThreeFilter.getText().toLowerCase();
            if (!text.contains("amoled android 9 4 гб")) {
                throw new Exception("Not selected Android 9 or AMOLED or RAM 4 Gb");
            }
        }
        return true;
    }

    public void setMinPrice(String price) {
        WebElement inputMinPrice = minPrice;
        inputMinPrice.click();
        inputMinPrice.clear();
        inputMinPrice.sendKeys(price);
    }

    public void setMaxPrice(String price) {
        WebElement inputMaxPrice = maxPrice;
        inputMaxPrice.click();
        inputMaxPrice.clear();
        inputMaxPrice.sendKeys(price);
    }

    public void clickOkPriceButton() {
        okButtonPrice.click();
    }

    public void setRamType() {
        ramFilter.click();
    }

    public void setScreenType() {
        screenType.click();
    }

    public void setAndroidType() {
        androidType.click();
    }

    public void hoverToProductAfterFilter() {
        Actions actions = new Actions(webDriver);
        WebElement product = blockOfProduct;
        actions.moveToElement(product).perform();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
