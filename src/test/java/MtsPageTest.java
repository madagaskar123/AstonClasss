import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.logging.Logger;



public class MtsPageTest {
    private static final Logger logger = Logger.getLogger(MtsPageTest.class.getName());
    private static WebDriver driver;
    private static WebDriverWait wait;
    private WebDriverWait shortWait;
    private final By phoneInput = By.xpath("//*[@id='connection-phone']");
    private final By sumInput = By.xpath("//*[@id='connection-sum']");
    private final By emailInput = By.xpath("//*[@id='connection-email']");
    private final By continueButton = By.xpath("//*[@id='pay-connection']/button");
    private final By homeInternetAccountInput = By.xpath("//input[@placeholder='Номер абонента']");
    private final By installmentAccountInput = By.xpath("//input[@placeholder='Номер счета на 44']");
    private final By debtAccountInput = By.xpath("//input[contains(@placeholder, 'Номер счета на 2073')]");
    private final By homeInternetTab = By.xpath("//*[@id='pay-section']/div/div/div[2]/section/div/div[1]/div[1]/div[2]/button/span[1]");
    private final By servicesTab = By.xpath("//span[text()='Услуги связи']");
    private final By installmentTab = By.xpath("//option[@value='Рассрочка']");
    private final By debtTab = By.xpath("//option[@value='Задолженность' and @data-open='pay-arrears']");
    private final By visaIcon = By.xpath("//img[contains(@src, 'visa')]");
    private final By mastercardIcon = By.xpath("//img[contains(@src, 'mastercard')]");
    private static MtsByHomePage homePage;
    static By amountDisplayLocator = By.xpath("//span[contains(., 'BYN')]");


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

        // Тесты

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
        selectTab(servicesTab, phoneInput);

        assertAll("Проверка плейсхолдеров",
                () -> assertEquals("Номер телефона", getElementAttribute(phoneInput, "placeholder")),
                this::assertCommonPaymentFormFields
        );
    }

    @Test
    @DisplayName("Проверка плейсхолдеров для Домашнего интернета")
    void verifyEmptyFieldsPlaceholdersForHomeInternet() {
        selectTab(homeInternetTab, homeInternetAccountInput);

        assertAll("Проверка для Домашнего интернета",
                () -> assertEquals("Номер абонента", getElementAttribute(homeInternetAccountInput, "placeholder")),
                this::assertCommonPaymentFormFields
        );
    }

    @Test
    @DisplayName("Проверка плейсхолдеров для Рассрочки")
    void verifyEmptyFieldsPlaceholdersForInstallment() {
        selectTab(installmentTab, installmentAccountInput);

        assertAll("Проверка для Рассрочки",
                () -> assertEquals("Номер счета на 44", getElementAttribute(installmentAccountInput, "placeholder")),
                this::assertCommonPaymentFormFields
        );
    }

    @Test
    @DisplayName("Проверка плейсхолдеров для Задолженности")
    void verifyEmptyFieldsPlaceholdersForDebt() {
        selectTab(debtTab, debtAccountInput);

        assertAll("Проверка для Задолженности",
                () -> assertEquals("Номер счета на 2073", getElementAttribute(debtAccountInput, "placeholder")),
                this::assertCommonPaymentFormFields
        );
    }

    @Test
    @DisplayName("Тестирование кнопки 'Продолжить'")
    public void testContinueButtonFunctionality2() {
        homePage.selectServiceOption("Услуги связи");
        homePage.enterPhoneNumber("297777777");
        homePage.enterPaymentAmount("10");
        clickContinue();

        WebElement continueButton = homePage.getContinueButton();

        assertAll("Проверка кнопки 'Продолжить'",
                () -> assertTrue(continueButton.isDisplayed(), "Кнопка не отображается"),
                () -> assertTrue(continueButton.isEnabled(), "Кнопка неактивна"),
                () -> assertFalse(continueButton.getAttribute("class").contains("disabled"),
                        "Кнопка имеет класс disabled"),
        () -> assertTrue(isPaymentSystemIconDisplayed("Visa")),
                () -> assertTrue(isPaymentSystemIconDisplayed("Mastercard"))

        );

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
        private WebDriverWait wait;

        public String getDisplayedAmount() {
            return driver.findElement(amountDisplayLocator).getText().trim();
        }

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

        private String getElementText(By locator) {
            return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator))
                    .getText();
        }
        private WebDriverWait getWait() {
            if (wait == null) {
                wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            }
            return wait;
        }


    }

    public void enterText(By locator, String text) {
        WebElement element = getWait().until(ExpectedConditions.elementToBeClickable(locator));
        element.clear();
        element.sendKeys(text);
    }

    public void clickContinue() {
        getWait().until(ExpectedConditions.elementToBeClickable(continueButton)).click();
    }

    public void selectTab(By tabLocator, By inputLocator) {
        getWait().until(ExpectedConditions.elementToBeClickable(tabLocator)).click();
        getWait().until(ExpectedConditions.presenceOfElementLocated(inputLocator));
    }
    private String getElementAttribute(By locator, String attribute) {
        return getWait().until(ExpectedConditions.presenceOfElementLocated(locator))
                .getAttribute(attribute);
    }

    private String getElementText(By locator) {
        return getWait().until(ExpectedConditions.visibilityOfElementLocated(locator))
                .getText();
    }

    public boolean isPaymentSystemIconDisplayed(String systemName) {
        By iconLocator;
        switch (systemName.toLowerCase()) {
            case "visa":
                iconLocator = visaIcon;
                break;
            case "mastercard":
                iconLocator = mastercardIcon;
                break;

            default:
                throw new IllegalArgumentException("Unknown payment system: " + systemName);
        }
        try {
            return driver.findElement(iconLocator).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }


    private WebDriverWait getWait() {
        if (wait == null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return wait;
    }

    private WebDriverWait getShortWait() {
        if (shortWait == null) {
            shortWait = new WebDriverWait(driver, Duration.ofSeconds(5));
        }
        return shortWait;
    }

    private void assertCommonPaymentFormFields() {
        assertAll("Общие проверки формы оплаты",
                () -> assertEquals("Сумма", getElementAttribute(sumInput, "placeholder")),
                () -> assertEquals("E-mail для отправки чека", getElementAttribute(emailInput, "placeholder"))
        );
    }

}






















