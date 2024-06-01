package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchAndAddToCartPage extends BasePage {

    private By search = By.xpath("//*[@id=\"search\"]/input");
    private By searchBtn = By.xpath("//*[@id=\"search\"]/span/button");
    private By btnAddToCartFirstItem = By.xpath("//*[@id=\"content\"]/div[3]/div/div/div[2]/div[2]/button[1]");
    private By successMessageAddToCart = By.xpath("/html/body/div[2]/div[1]/a[2]");


    public SearchAndAddToCartPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void searchItem(String item) throws InterruptedException {
        sendText(item, search);
    }

    public void clickSearchBtn() throws InterruptedException {
        click(searchBtn);
    }

    public void clickAddToCartBtnFirstItem() throws InterruptedException {
        click(btnAddToCartFirstItem);
    }

    public String messageSuccessAddToCart() throws InterruptedException {
        return getText(successMessageAddToCart);
    }
}
