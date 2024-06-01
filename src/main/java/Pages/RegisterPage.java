package Pages;

import Base.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class RegisterPage extends BasePage {

    // Common Locators
    private By myAccountDropdown = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/a/span[1]");
    private By loginDropdownOption = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[2]/a");
    private By registerDropdownOption = By.xpath("//*[@id=\"top-links\"]/ul/li[2]/ul/li[1]/a");
    private By form = By.tagName("form");
    private By inputFirstName = By.xpath("//*[@id=\"input-firstname\"]");
    private By inputLastName = By.xpath("//*[@id=\"input-lastname\"]");
    private By inputEmail = By.xpath("//*[@id=\"input-email\"]");
    private By inputTelephone = By.xpath("//*[@id=\"input-telephone\"]");
    private By inputPassword = By.xpath("//*[@id=\"input-password\"]");
    private By inputPasswordConfirm = By.xpath("//*[@id=\"input-confirm\"]");
    private By subscribeOptionNo = By.xpath("//*[@id=\"content\"]/form/fieldset[3]/div/div/label[2]/input");
    private By checkboxPrivacy = By.xpath("//*[@id=\"content\"]/form/div/div/input[1]");
    private By btnContinueForm = By.xpath("//*[@id=\"content\"]/form/div/div/input[2]");
    private By pSuccessRegister = By.xpath("//*[@id=\"content\"]/p[1]");
    private By btnContinueSuccessRegister = By.xpath("//*[@id=\"content\"]/div/div/a");
    private By pEditAccountToVerifySuccessRegistration = By.xpath("//*[@id=\"content\"]/ul[1]/li[1]/a");


    public RegisterPage(WebDriver driver, WebDriverWait wait) {
        super(driver, wait);
    }

    public void clickToOpenMyAccountDropdown() throws InterruptedException {
        click(myAccountDropdown);
    }

    public String pLoginToVerifyCorrectDropdown() throws InterruptedException {
        return getText(loginDropdownOption);
    }

    public String pRegisterToVerifyCorrectDropdown() throws InterruptedException {
        return getText(registerDropdownOption);
    }

    public void clickToSelectRegisterOptionInDropdown() throws InterruptedException {
        click(registerDropdownOption);
    }

    public boolean isFormPresent() {
        if ( findElement(form).isDisplayed() ) {
            return true;
        }

        return false;
    }

    public void completeInputFirstName(String firstName) throws InterruptedException {
        sendKey(firstName, inputFirstName);
    }

    public void completeInputLastName(String lastName) throws InterruptedException {
        sendKey(lastName, inputLastName);
    }

    public void completeInputEmail(String email) throws InterruptedException {
        sendKey(email, inputEmail);
    }

    public void completeInputTelephone(String telephone) throws InterruptedException {
        sendKey(telephone, inputTelephone);
    }

    public void completeInputPassword(String password) throws InterruptedException {
        sendKey(password, inputPassword);
    }

    public void completeInputPasswordConfirm(String password) throws InterruptedException {
        sendKey(password, inputPasswordConfirm);
    }

    public void clickSubscribeRadioBtnNo() throws InterruptedException {
        click(subscribeOptionNo);
    }

    public void clickPrivacyCheckBox() throws InterruptedException {
        click(checkboxPrivacy);
    }

    public void clickBtnContinueForm() throws InterruptedException {
        click(btnContinueForm);
    }

    public String successMessageRegister() throws InterruptedException {
        return getText(pSuccessRegister);
    }

    public void clickBtnContinueFinal() throws InterruptedException {
        click(btnContinueSuccessRegister);
    }

    public String pEditAccountToVerifysuccessRegister() throws InterruptedException {
        return getText(pEditAccountToVerifySuccessRegistration);
    }
}
