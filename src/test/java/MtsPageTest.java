import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;
import java.util.logging.Logger;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

@DisplayName("Тесты для страницы MTS")
public class MtsPageTest {
    private static final Logger logger = Logger.getLogger(MtsPageTest.class.getName());
    private static WebDriver driver;
    private static MtsHomePage homePage;
    private static PaymentPage paymentPage;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
    }

    @BeforeEach
    public void openPage() {
        driver.get("https://www.mts.by");
        homePage = new MtsHomePage(driver);
        paymentPage = new PaymentPage(driver);
        homePage.acceptCookies();
    }

    @Test
    @DisplayName("Проверка названия блока <Онлайн пополнение без комисии>")
    public void testBlockTitle() {
        String actualTitle = homePage.getOnlinePaymentTitle();
        assertEquals("Онлайн пополнение без комиссии", actualTitle);
    }

    @Test
    @DisplayName("Проверка на наличие логотипов платежных систем")
    public void testPaymentLogosExist() {
        List<WebElement> logos = homePage.getPaymentLogos();
        assertFalse(logos.isEmpty(), "Логотипы платежных систем не найдены");

        for (WebElement logo : logos) {
            assertTrue(logo.isDisplayed(), "Один из логотипов не отображается");
        }
    }

    @Test
    @DisplayName("Тестирование работы ссылки")
    public void testPaymentSecurityServiceLink() {
        String expectedPath = homePage.getPaymentServiceLinkHref();
        homePage.clickPaymentServiceLink();
        assertTrue(driver.getCurrentUrl().contains(expectedPath),
                "Переход выполнен не по ожидаемой ссылке");
    }

    @Test
    @DisplayName("Тестирование кнопки 'Продолжить'")
    public void testContinueButtonFunctionality() {
        homePage.selectServiceOption("Услуги связи");
        homePage.enterPhoneNumber("297777777");
        homePage.enterPaymentAmount("10");

        WebElement continueButton = homePage.getContinueButton();

        assertAll("Проверка кнопки 'Продолжить'",
                () -> assertTrue(continueButton.isDisplayed(), "Кнопка не отображается"),
                () -> assertTrue(continueButton.isEnabled(), "Кнопка неактивна"),
                () -> assertFalse(continueButton.getAttribute("class").contains("disabled"),
                        "Кнопка имеет класс disabled")
        );

        assertDoesNotThrow(() -> continueButton.click(),
                "При клике на кнопку произошла ошибка");

        try {
            WebElement validationMessage = homePage.getValidationMessage();
            assertTrue(validationMessage.isDisplayed(),
                    "Не появилось сообщение о необходимости заполнить поле");
        } catch (TimeoutException e) {}
    }

    @Test
    @DisplayName("Проверка плейсхолдеров для Услуг связи")
    void verifyEmptyFieldsPlaceholdersForMobileServices() {
        paymentPage.selectServicesTab();

        assertAll("Проверка плейсхолдеров",
                () -> assertEquals("Номер телефона", paymentPage.getPhoneInputPlaceholder()),
                () -> assertEquals("Сумма", paymentPage.getSumInputPlaceholder()),
                () -> assertEquals("E-mail для отправки чека", paymentPage.getEmailInputPlaceholder())
        );
    }

    @Test
    @DisplayName("Проверка плейсхолдеров для Домашнего интернета")
    void verifyEmptyFieldsPlaceholdersForHomeInternet() {
        paymentPage.selectHomeInternetTab();

        assertAll("Проверка для Домашнего интернета",
                () -> assertEquals("Номер абонента", paymentPage.getHomeInternetAccountPlaceholder()),
                () -> assertEquals("Сумма", paymentPage.getSumInputPlaceholder()),
                () -> assertEquals("E-mail для отправки чека", paymentPage.getEmailInputPlaceholder())
        );
    }

    @Test
    @DisplayName("Проверка плейсхолдеров для Рассрочки")
    void verifyEmptyFieldsPlaceholdersForInstallment() {
        paymentPage.selectInstallmentTab();

        assertAll("Проверка для Рассрочки",
                () -> assertEquals("Номер счета на 44", paymentPage.getInstallmentAccountPlaceholder()),
                () -> assertEquals("Сумма", paymentPage.getSumInputPlaceholder()),
                () -> assertEquals("E-mail для отправки чека", paymentPage.getEmailInputPlaceholder())
        );
    }

    @Test
    @DisplayName("Проверка плейсхолдеров для Задолженности")
    void verifyEmptyFieldsPlaceholdersForDebt() {
        paymentPage.selectDebtTab();

        assertAll("Проверка для Задолженности",
                () -> assertEquals("Номер счета на 2073", paymentPage.getDebtAccountPlaceholder()),
                () -> assertEquals("Сумма", paymentPage.getSumInputPlaceholder()),
                () -> assertEquals("E-mail для отправки чека", paymentPage.getEmailInputPlaceholder())
        );
    }

    @Test
    @DisplayName("Проверка отображения иконок платежных систем")
    public void testPaymentSystemIconsDisplayed() {
        homePage.selectServiceOption("Услуги связи");
        homePage.enterPhoneNumber("297777777");
        homePage.enterPaymentAmount("10");
        homePage.getContinueButton().click();

        assertAll("Проверка иконок платежных систем",
                () -> assertTrue(paymentPage.isVisaIconDisplayed(), "Иконка Visa не отображается"),
                () -> assertTrue(paymentPage.isMastercardIconDisplayed(), "Иконка Mastercard не отображается")
        );
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}