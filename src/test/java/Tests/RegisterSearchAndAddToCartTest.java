package Tests;

import Pages.RegisterPage;
import Pages.SearchAndAddToCartPage;
import Tests.reports.ReportFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RegisterSearchAndAddToCartTest {
	private static WebDriver driver;
	private static WebDriverWait wait;
	private static ExtentSparkReporter info = new ExtentSparkReporter("reports/RegisterSearchAndAddToCart.html");
	private static ExtentReports extent;

	private Random random = new Random();

	@BeforeAll
	public static void createReport() {
		extent = ReportFactory.getInstance();
		extent.attachReporter(info);
	}

	@BeforeAll
	public static void setUp() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--ignore-certificate-errors");
		options.addArguments("start-maximized");
		driver = new ChromeDriver(options);
		wait = new WebDriverWait(driver, Duration.ofMillis(5000));
		RegisterPage registerPage = new RegisterPage(driver, wait);
		registerPage.getUrl("http://opencart.abstracta.us/index.php?route=common/home");
	}

	@Test
	@Tag("REGISTER")
	@Tag("SUCCESS_REGISTER_SEARCH_AND_ADD_TO_CART")
	public void successRegister() throws InterruptedException {
		ExtentTest test = extent.createTest("Success Register");

		test.log(Status.INFO, "Test Start");
		test.log(Status.INFO, "Navigate to the app");

		RegisterPage registerPage = new RegisterPage(driver, wait);
		registerPage.clickToOpenMyAccountDropdown();

		String login = registerPage.pLoginToVerifyCorrectDropdown();
		login.equals("Login");
		test.log(Status.PASS, "Verify Login Option In dropdown");
		String register = registerPage.pRegisterToVerifyCorrectDropdown();
		register.equals("Register");
		test.log(Status.PASS, "Verify Register Option In dropdown");

		registerPage.clickToSelectRegisterOptionInDropdown();
		test.log(Status.INFO, "Select Register Option In dropdown");

		Boolean isFormPresent = registerPage.isFormPresent();
		isFormPresent.equals(true);
		test.log(Status.PASS, "Verify appear Register Form on screen");

		registerPage.completeInputFirstName("Mauro");
		registerPage.completeInputLastName("Mascheroni");
		String randomNumber = String.valueOf(random.nextInt(100));
		registerPage.completeInputEmail("mauro" + randomNumber + "@gmail.com");
		registerPage.completeInputTelephone("0991990009");
		registerPage.completeInputPassword("123456");
		registerPage.completeInputPasswordConfirm("123456");
		registerPage.clickSubscribeRadioBtnNo();
		registerPage.clickPrivacyCheckBox();
		registerPage.clickBtnContinueForm();
		test.log(Status.INFO, "Insert all the data on Form and send");

		String message = registerPage.successMessageRegister();
		message.contains("Congratulations! Your new account has been successfully created!");
		test.log(Status.PASS, "Verify Message about success Register");

		registerPage.clickBtnContinueFinal();
		String editAccount = registerPage.pEditAccountToVerifysuccessRegister();
		editAccount.equals("Edit Account");
		test.log(Status.PASS, "Verify appear Edit Account on Screen");
	}

	@Test
	@Tag("SEARCH_AND_ADD_TO_CART")
	@Tag("SUCCESS_REGISTER_SEARCH_AND_ADD_TO_CART")
	public void successSearchIphoneAndAddToCart() throws InterruptedException {
		ExtentTest test = extent.createTest("Success Search and Add To Cart");

		test.log(Status.INFO, "Test Start");

		SearchAndAddToCartPage searchAndAddToCartPage = new SearchAndAddToCartPage(driver, wait);
		searchAndAddToCartPage.searchItem("Iphone");
		searchAndAddToCartPage.clickSearchBtn();
		test.log(Status.INFO, "Realize Search");

		searchAndAddToCartPage.clickAddToCartBtnFirstItem();
		String messageAddToCart = searchAndAddToCartPage.messageSuccessAddToCart();
		test.log(Status.INFO, "Add to the Cart first item");
		messageAddToCart.equals("Success: You have added iPhone to your shopping cart!");
		test.log(Status.PASS, "Message success Add Item to the cart");
	}

	@AfterAll
	public void close() throws InterruptedException {
		driver.quit();
		extent.flush();
	}

}
