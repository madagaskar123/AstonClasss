import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class MtsHomePage extends BasePage {
    // Локаторы
    private final By cookieAcceptButton = By.xpath("//button[contains(., 'Принять')]");
    private final By onlinePaymentTitle = By.xpath("//h2[contains(., 'Онлайн пополнение')]");
    private final By paymentLogos = By.cssSelector("div.pay__partners li");
    private final By paymentServiceLink = By.xpath("//a[contains(.,'Подробнее о сервисе')]");
    private final By serviceOptions = By.cssSelector("li.select_item");
    private final By phoneInput = By.cssSelector("input[placeholder='Номер телефона']");
    private final By amountInput = By.cssSelector("input[placeholder*='Сумма']");
    private final By continueButton = By.xpath("//button[contains(., 'Продолжить')]");
    private final By validationMessage = By.cssSelector(".error-message");
    private final By amountDisplay = By.xpath("//span[contains(., 'BYN')]");

    public MtsHomePage(WebDriver driver) {
        super(driver);
    }

    public void acceptCookies() {
        try {
            clickElement(cookieAcceptButton);
        } catch (Exception e) {
            // Куки уже приняты или кнопка не найдена
        }
    }

    public String getOnlinePaymentTitle() {
        return getElementText(onlinePaymentTitle).replace("\n", " ").trim();
    }

    public List<WebElement> getPaymentLogos() {
        return driver.findElements(paymentLogos);
    }

    public String getPaymentServiceLinkHref() {
        return getElementAttribute(paymentServiceLink, "href");
    }

    public void clickPaymentServiceLink() {
        clickElement(paymentServiceLink);
    }

    public void selectServiceOption(String optionText) {
        List<WebElement> options = driver.findElements(serviceOptions);
        for (WebElement option : options) {
            if (option.getText().contains(optionText)) {
                option.click();
                break;
            }
        }
    }

    public void enterPhoneNumber(String phoneNumber) {
        enterText(phoneInput, phoneNumber);
    }

    public void enterPaymentAmount(String amount) {
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(amountInput));
        element.clear();
        element.sendKeys(amount);
    }

    public WebElement getContinueButton() {
        return wait.until(ExpectedConditions.presenceOfElementLocated(continueButton));
    }

    public WebElement getValidationMessage() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(validationMessage));
    }

    public String getDisplayedAmount() {
        return getElementText(amountDisplay).trim();
    }
}