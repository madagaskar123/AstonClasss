import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.*;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ChromeCreateTest {

    private static WebDriver driver;
    private static WebDriverWait wait;

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
        acceptCookiesIfPresent();
    }

    @Test
    @DisplayName("Проверка названия блока <Онлайн пополнение без комисии>")
    public void testBlockTitle() {
        // Получаем элемент заголовка
        WebElement titleElement = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.xpath("//h2[contains(., 'Онлайн пополнение')]")));

        // Вариант 1: Стандартный способ (объединяет текст через пробел)
        String fullText = titleElement.getText().replace("\n", " ").trim();
        assertEquals("Онлайн пополнение без комиссии", fullText);

        // Вариант 2: Через JavaScript (точное получение текста)
        String jsText = (String) ((JavascriptExecutor)driver)
                .executeScript("return arguments[0].textContent", titleElement);
        assertEquals("Онлайн пополнение без комиссии", jsText.trim());
    }

    @Test
    @DisplayName("Проверка на наличие логотипов платежных систем")

    public void testPaymentLogosExist() {
        // 1. Находим контейнер с логотипами
        List<WebElement> logos = driver.findElements(
                By.cssSelector("div.pay__partners li")
        );

        // 2. Проверяем, что найдены логотипы
        assertFalse(logos.isEmpty(), "Логотипы платежных систем не найдены");


        // 3. Проверяем видимость всех логотипов
        for (WebElement logo : logos) {
            assertTrue(logo.isDisplayed(), "Один из логотипов не отображается");
        }
    }

    @Test
    @DisplayName("Тестирование работы ссылки")
    public void testPaymentSecurityServiceLink() {
        WebElement link = driver.findElement(By.xpath("//a[contains(.,'Подробнее о сервисе')]"));
        String expectedPath = link.getAttribute("href");

        link.click();

        // Проверяем что текущий URL содержит ожидаемый путь
        assertTrue(driver.getCurrentUrl().contains(expectedPath),
                "Переход выполнен не по ожидаемой ссылке");
    }
    @Test
    @DisplayName("Тестирование кнопки 'Продолжить'")
    public void testContinueButtonFunctionality() {

        // 1. Выбираем "Услуги связи" из выпадающего списка
        List<WebElement> options = driver.findElements(By.cssSelector("li.select_item"));
        for (WebElement option : options) {
            if (option.getText().contains("Услуги связи")) {
                option.click();
                break;
            }
        }

        // 2. Вводим тестовый номер телефона
        WebElement phoneInput = driver.findElement(By.cssSelector("input[placeholder='Номер телефона']"));
        phoneInput.sendKeys("297777777");

        // 3. Вводим сумму пополнения
        WebElement amountInput = wait.until(ExpectedConditions
                .visibilityOfElementLocated(By.cssSelector("input[placeholder*='Сумма']")));
        amountInput.clear();
        amountInput.sendKeys("10"); // Вводим сумму 10 рублей


        // 4. Кликаем кнопку "Продолжить"
        WebElement continueButton = wait.until(ExpectedConditions
                .presenceOfElementLocated(By.xpath("//button[contains(., 'Продолжить')]")));

        // 5. Проверяем базовые параметры кнопки
        assertAll("Проверка кнопки 'Продолжить'",
                () -> assertTrue(continueButton.isDisplayed(), "Кнопка не отображается"),
                () -> assertTrue(continueButton.isEnabled(), "Кнопка неактивна"),
                () -> assertFalse(continueButton.getAttribute("class").contains("disabled"),
                        "Кнопка имеет класс disabled")
        );

        // 6. Проверяем реакцию на клик
        assertDoesNotThrow(() -> continueButton.click(),
                "При клике на кнопку произошла ошибка");
        try {
            WebElement validationMessage = wait.until(ExpectedConditions
                    .visibilityOfElementLocated(By.cssSelector(".error-message")));
            assertTrue(validationMessage.isDisplayed(),
                    "Не появилось сообщение о необходимости заполнить поле");
        } catch (TimeoutException e) {}
    }

    private void acceptCookiesIfPresent() {
        try {
            driver.findElement(By.xpath("//button[contains(., 'Принять')]")).click();
        } catch (Exception e) {}
    }

    @AfterAll
    public static void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}