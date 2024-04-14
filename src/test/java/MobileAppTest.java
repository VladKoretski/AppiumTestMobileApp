import io.appium.java_client.android.AndroidDriver;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class MobileAppTest {

    private AndroidDriver driver;

    @BeforeEach
    public void setUp() throws MalformedURLException {

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "Android");
        desiredCapabilities.setCapability("appium:deviceName", "Infinix");
        desiredCapabilities.setCapability("appium:appPackage", "ru.netology.testing.uiautomator");
        desiredCapabilities.setCapability("appium:appActivity", "ru.netology.testing.uiautomator.MainActivity");
        desiredCapabilities.setCapability("appium:automationName", "uiautomator2");
        desiredCapabilities.setCapability("appium:ensureWebviewsHavePages", true);
        desiredCapabilities.setCapability("appium:nativeWebScreenshot", true);
        desiredCapabilities.setCapability("appium:newCommandTimeout", 3600);
        desiredCapabilities.setCapability("appium:connectHardwareKeyboard", true);

        URL remoteUrl = new URL("http://127.0.0.1:4723");

        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void changeTextTest() {

        String expectedText = "New Text";

        var el1 = driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        el1.isDisplayed();
        el1.click();
        el1.sendKeys(expectedText);
        var el2 = driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonChange"));
        el2.isDisplayed();
        el2.click();
        var el3 = driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        el3.isDisplayed();
        String actualText = el3.getText();

        Assertions.assertEquals(expectedText, actualText);
    }

    @Test
    public void emptyTextTest() {
        var el1 = driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        el1.isDisplayed();
        String defaultText = el1.getText();

        var el2 = driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        el2.isDisplayed();
        el2.click();
        el2.sendKeys("");
        var el3 = driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonChange"));
        el3.isDisplayed();
        el3.click();
        var el4 = driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        el4.isDisplayed();
        String actualText = el4.getText();

        Assertions.assertEquals(defaultText, actualText);

    }

    @Test
    public void spaceTextTest() {
        var el1 = driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        el1.isDisplayed();
        String defaultText = el1.getText();

        var el2 = driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        el2.isDisplayed();
        el2.click();
        el2.sendKeys("  ");
        var el3 = driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonChange"));
        el3.isDisplayed();
        el3.click();
        var el4 = driver.findElement(By.id("ru.netology.testing.uiautomator:id/textToBeChanged"));
        el4.isDisplayed();
        String actualText = el4.getText();

        Assertions.assertEquals(defaultText, actualText);
    }

    @Test
    public void newActivityTest() {

        String newActivityText = "New Activity";

        var el1 = driver.findElement(By.id("ru.netology.testing.uiautomator:id/userInput"));
        el1.isDisplayed();
        el1.sendKeys(newActivityText);
        var el2 = driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonChange"));
        el2.isDisplayed();
        el2.click();
        var el3 = driver.findElement(By.id("ru.netology.testing.uiautomator:id/buttonActivity"));
        el3.isDisplayed();
        el3.click();
        var el4 = driver.findElement(By.id("ru.netology.testing.uiautomator:id/text"));
        el4.isDisplayed();
        String actualText = el4.getText();

        Assertions.assertEquals(newActivityText, actualText);
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }
}