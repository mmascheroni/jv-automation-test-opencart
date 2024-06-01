package Tests;

import Pages.RegisterPage;
import Pages.SearchAndAddToCartPage;
import Tests.reports.ReportFactory;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.junit.jupiter.api.*;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static Tests.reports.ReportFactory.captureScreenshot;
import static org.junit.Assert.assertTrue;

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

		try {
			RegisterPage registerPage = new RegisterPage(driver, wait);
			registerPage.clickToOpenMyAccountDropdown();

			String login = registerPage.pLoginToVerifyCorrectDropdown();
			if ( login.equals("Login") ) {
				test.log(Status.PASS, "Verify Login Option In dropdown");
			} else {
				test.log(Status.FAIL, "FAIL VALIDATION: Login option in dropdown is not visible");
				captureScreenshot(test, "FAIL_REGISTER_THE_LOGIN_OPTION", driver);
			}
			assertEquals("The option Login should be visible and correct", "Login", login);

			String  register = registerPage.pRegisterToVerifyCorrectDropdown();
			if ( register.equals("Register") ) {
				test.log(Status.PASS, "Verify Register Option In dropdown");
			} else {
				test.log(Status.FAIL, "FAIL VALIDATION: Register option in dropdown is not visible");
				captureScreenshot(test, "FAIL_REGISTER_THE_REGISTER_OPTION", driver);
			}
			assertEquals("The option Register should be visible and correct", "Register", register);

			registerPage.clickToSelectRegisterOptionInDropdown();
			test.log(Status.INFO, "Select Register Option In dropdown");

			Boolean isFormPresent = registerPage.isFormPresent();
			if ( isFormPresent ) {
				test.log(Status.PASS, "The Register Form should be visible");
			} else {
				test.log(Status.FAIL, "FAIL VALIDATION: The form register is not visible");
				captureScreenshot(test, "FAIL_REGISTER_FORM_REGISTER", driver);
			}
			assertTrue("The register form should be visible", isFormPresent);

			registerPage.completeInputFirstName("Mauro");
			registerPage.completeInputLastName("Mas");
			String randomNumber = String.valueOf(random.nextInt(100));
			registerPage.completeInputEmail("mauro" + randomNumber + "@gmail.com");
			registerPage.completeInputTelephone("0991990009");
			registerPage.completeInputPassword("123456");
			registerPage.completeInputPasswordConfirm("123456");
			registerPage.clickSubscribeRadioBtnNo();
			registerPage.clickPrivacyCheckBox();
			registerPage.clickBtnContinueForm();
			test.log(Status.INFO, "Insert all the data on Form and send");

			String actualMessage = registerPage.successMessageRegister();
			if ( actualMessage.equals("Congratulations! Your new account has been successfully created!") ) {
				test.log(Status.PASS, "Verify Message about success Register");
			} else {
				test.log(Status.FAIL, "FAIL VALIDATION: The message success register is not visible or not correct");
				captureScreenshot(test, "FAIL_REGISTER_MESSAGE_SUCCESS_REGISTER", driver);
			}
			assertEquals("The success message Register should be visible and correct", "Congratulations! Your new account has been successfully created!", actualMessage);

			registerPage.clickBtnContinueFinal();
			String editAccount = registerPage.pEditAccountToVerifysuccessRegister();
			if ( editAccount.equals("Edit Account") ) {
				test.log(Status.PASS, "Verify appear Edit Account on Screen");
			} else {
				test.log(Status.FAIL, "FAIL VALIDATION: The text Edit Account is not visible");
				captureScreenshot(test, "FAIL_REGISTER_TEXT_EDIT_ACCOUNT", driver);
			}
			assertEquals("The text Edit Account should be visible and correct", "Edit Account", editAccount);

		} catch (Exception e) {
			test.log(Status.FAIL, "FAIL TEST REGISTER: " + e.getMessage());
			captureScreenshot(test, "FAIL_REGISTER", driver);
		}


	}

	@Test
	@Tag("SEARCH_AND_ADD_TO_CART")
	@Tag("SUCCESS_REGISTER_SEARCH_AND_ADD_TO_CART")
	public void successSearchIphoneAndAddToCart() throws InterruptedException {
		ExtentTest test = extent.createTest("Success Search and Add To Cart");
		test.log(Status.INFO, "Test Start");

		try {
			SearchAndAddToCartPage searchAndAddToCartPage = new SearchAndAddToCartPage(driver, wait);
			searchAndAddToCartPage.searchItem("Iphone");
			searchAndAddToCartPage.clickSearchBtn();
			test.log(Status.INFO, "Realize Search");

			searchAndAddToCartPage.clickAddToCartBtnFirstItem();
			String messageAddToCart = searchAndAddToCartPage.messageSuccessAddToCart();
			test.log(Status.INFO, "Add to the Cart first item");
			if ( messageAddToCart.equals("shopping cart") ) {
				test.log(Status.PASS, "Message success Add Item to the cart");
			} else {
				test.log(Status.FAIL, "FAIL VALIDATION: The success message from add to cart is not visible");
				captureScreenshot(test, "FAIL_SUCCESS_MESSAGE_ON_ADD_TO_CART", driver);
			}
			assertEquals("The success message add to cart should be visible", "shopping cart", messageAddToCart);
		} catch (Exception e) {
			test.log(Status.FAIL, "FAIL TEST SEARCH AND ADD TO CART" + e.getMessage());
			captureScreenshot(test, "FAIL_SEARCH_AND_ADD_TO_CART", driver);
		}
	}

	@AfterAll
	public void close() throws InterruptedException {
		driver.quit();
		extent.flush();
	}

}
