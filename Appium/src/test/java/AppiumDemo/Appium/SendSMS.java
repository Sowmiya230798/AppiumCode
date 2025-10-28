package AppiumDemo.Appium;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.time.Duration;

public class SendSMS {
    public static void main(String[] args) throws Exception {

        UiAutomator2Options options = new UiAutomator2Options()
                .setDeviceName("Android Device")
                .setPlatformName("Android")
                .setAutomationName("UiAutomator2")
                .setPlatformVersion("9")
                .setAppPackage("com.google.android.apps.messaging")
                .setAppActivity("com.google.android.apps.messaging.ui.ConversationListActivity")
                .setNoReset(true);

        AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        try {
            // Wait for app to load
            Thread.sleep(4000);

            WebElement startChatButton = null;

            // Try different locators for "Start chat" button
            try {
                startChatButton = wait.until(
                        ExpectedConditions.elementToBeClickable(AppiumBy.accessibilityId("Start chat")));
            } catch (Exception e1) {
                try {
                    startChatButton = wait.until(
                            ExpectedConditions.elementToBeClickable(AppiumBy.id("com.google.android.apps.messaging:id/start_new_conversation_button")));
                } catch (Exception e2) {
                    startChatButton = wait.until(
                            ExpectedConditions.elementToBeClickable(AppiumBy.xpath("//android.widget.ImageButton[@content-desc='Start new conversation']")));
                }
            }

            startChatButton.click();

            // Enter recipient number
            WebElement recipientField = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            AppiumBy.id("com.google.android.apps.messaging:id/recipient_text_view")));
            recipientField.sendKeys("1234567890");

            // Confirm recipient (press enter)
            driver.executeScript("mobile: performEditorAction", java.util.Map.of("action", "done"));
            Thread.sleep(2000);

            // Enter message text
            WebElement messageBox = wait.until(
                    ExpectedConditions.presenceOfElementLocated(
                            AppiumBy.id("com.google.android.apps.messaging:id/compose_message_text")));
            messageBox.sendKeys("Hello, this is an automated message from Appium 🚀");

            // Click send
            WebElement sendButton = wait.until(
                    ExpectedConditions.elementToBeClickable(
                            AppiumBy.accessibilityId("Send SMS")));
            sendButton.click();

            System.out.println("✅ SMS sent successfully!");
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }

        Thread.sleep(5000);
        driver.quit();
    }
}
