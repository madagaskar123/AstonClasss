import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PaymentPage extends BasePage {
    // Локаторы
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

    public PaymentPage(WebDriver driver) {
        super(driver);
    }

    public void selectServicesTab() {
        selectTab(servicesTab, phoneInput);
    }

    public void selectHomeInternetTab() {
        selectTab(homeInternetTab, homeInternetAccountInput);
    }

    public void selectInstallmentTab() {
        selectTab(installmentTab, installmentAccountInput);
    }

    public void selectDebtTab() {
        selectTab(debtTab, debtAccountInput);
    }

    private void selectTab(By tabLocator, By inputLocator) {
        clickElement(tabLocator);
        wait.until(ExpectedConditions.presenceOfElementLocated(inputLocator));
    }

    public String getPhoneInputPlaceholder() {
        return getElementAttribute(phoneInput, "placeholder");
    }

    public String getHomeInternetAccountPlaceholder() {
        return getElementAttribute(homeInternetAccountInput, "placeholder");
    }

    public String getInstallmentAccountPlaceholder() {
        return getElementAttribute(installmentAccountInput, "placeholder");
    }

    public String getDebtAccountPlaceholder() {
        return getElementAttribute(debtAccountInput, "placeholder");
    }

    public String getSumInputPlaceholder() {
        return getElementAttribute(sumInput, "placeholder");
    }

    public String getEmailInputPlaceholder() {
        return getElementAttribute(emailInput, "placeholder");
    }

    public void clickContinue() {
        clickElement(continueButton);
    }

    public boolean isVisaIconDisplayed() {
        return isElementDisplayed(visaIcon);
    }

    public boolean isMastercardIconDisplayed() {
        return isElementDisplayed(mastercardIcon);
    }
}