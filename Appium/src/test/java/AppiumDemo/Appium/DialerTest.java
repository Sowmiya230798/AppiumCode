package AppiumDemo.Appium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

public class DialerTest {
    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        // --- Step 1: Configure device and app ---
        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("Android Device")
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setPlatformVersion("9")
                .setAppPackage("com.google.android.dialer")
                .setAppActivity("com.google.android.dialer.extensions.GoogleDialtactsActivity")
                .setAppWaitActivity("com.google.android.dialer.extensions.*")
                .setNoReset(true);

        // --- Step 2: Start Appium session ---
        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        Thread.sleep(4000); // give app time to load

        try {
            // --- Step 3: Open the dial pad ---
            WebElement dialPadButton = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.id("com.google.android.dialer:id/fab")));
            dialPadButton.click();
            System.out.println("Opened dial pad successfully.");
        } catch (Exception e) {
            System.out.println("Could not find dial pad button via id. Trying accessibilityId fallback...");
            try {
                driver.findElement(AppiumBy.accessibilityId("key pad")).click();
            } catch (Exception ex) {
                System.out.println("Dial pad not found. Please confirm element IDs via UIAutomatorViewer.");
            }
        }

        // --- Step 4: Enter number ---
        try {
            WebElement digits = wait.until(ExpectedConditions.presenceOfElementLocated(
                    AppiumBy.id("com.google.android.dialer:id/digits")));
            digits.sendKeys("1234567890");
            System.out.println("Entered phone number.");
        } catch (Exception e) {
            System.out.println("Could not locate digits field.");
        }

        // --- Step 5: Tap call button ---
        try {
            WebElement callButton = driver.findElement(
                    AppiumBy.id("com.google.android.dialer:id/dialpad_floating_action_button"));
            callButton.click();
            System.out.println("Call button clicked.");
        } catch (Exception e) {
            System.out.println("Call button not found.");
        }

        Thread.sleep(5000);
        driver.quit();
    }
}
