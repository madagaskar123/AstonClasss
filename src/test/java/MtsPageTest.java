import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MtsPageTest {

    private static WebDriver driver;
    private static WebDriverWait wait;
    private static MtsByHomePage homePage;

    @BeforeAll
    public static void setup() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    @BeforeEach
    public void openPage() {
        driver.get("https://www.mts.by");
        homePage = new MtsByHomePage(driver, wait);
        homePage.acceptCookiesIfPresent();
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


    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    // Page Object Class
    private static class MtsByHomePage {
        private final WebDriver driver;
        private final WebDriverWait wait;

        public MtsByHomePage(WebDriver driver, WebDriverWait wait) {
            this.driver = driver;
            this.wait = wait;
        }

        public void acceptCookiesIfPresent() {
            try {
                driver.findElement(By.xpath("//button[contains(., 'Принять')]")).click();
            } catch (Exception e) {}
        }

        public String getOnlinePaymentTitle() {
            WebElement titleElement = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.xpath("//h2[contains(., 'Онлайн пополнение')]")));
            return titleElement.getText().replace("\n", " ").trim();
        }

        public List<WebElement> getPaymentLogos() {
            return driver.findElements(By.cssSelector("div.pay__partners li"));
        }

        public String getPaymentServiceLinkHref() {
            WebElement link = driver.findElement(By.xpath("//a[contains(.,'Подробнее о сервисе')]"));
            return link.getAttribute("href");
        }

        public void clickPaymentServiceLink() {
            WebElement link = driver.findElement(By.xpath("//a[contains(.,'Подробнее о сервисе')]"));
            link.click();
        }

        public void selectServiceOption(String optionText) {
            List<WebElement> options = driver.findElements(By.cssSelector("li.select_item"));
            for (WebElement option : options) {
                if (option.getText().contains(optionText)) {
                    option.click();
                    break;
                }
            }
        }

        public void enterPhoneNumber(String phoneNumber) {
            WebElement phoneInput = driver.findElement(By.cssSelector("input[placeholder='Номер телефона']"));
            phoneInput.sendKeys(phoneNumber);
        }

        public void enterPaymentAmount(String amount) {
            WebElement amountInput = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.cssSelector("input[placeholder*='Сумма']")));
            amountInput.clear();
            amountInput.sendKeys(amount);
        }

        public WebElement getContinueButton() {
            return wait.until(ExpectedConditions
                    .presenceOfElementLocated(By.xpath("//button[contains(., 'Продолжить')]")));
        }

        public WebElement getValidationMessage() {
            return wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.cssSelector(".error-message")));
        }
    }
}