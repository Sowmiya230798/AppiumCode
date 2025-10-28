package AppiumDemo.Appium;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebElement;

public class CalculatorTest {

    public static void main(String[] args) throws MalformedURLException, InterruptedException {

        // Set desired capabilities using UiAutomator2Options
        UiAutomator2Options options = new UiAutomator2Options();
        options.setPlatformName("Android");
        options.setAutomationName("UiAutomator2");
        options.setDeviceName("Android Device"); // Use actual device/emulator name
        options.setPlatformVersion("9");       // Your Android version
        options.setAppPackage("com.android.calculator2");
        options.setAppActivity("com.android.calculator2.Calculator");

        // Connect to Appium Server
        URL url = new URL("http://127.0.0.1:4723/");
        AndroidDriver driver = new AndroidDriver(url, options);
        Thread.sleep(3000);

        // ✅ Addition Test (7 + 3 = 10)
        performCalculation(driver, "com.android.calculator2:id/op_add", "10", "Addition");

        // ✅ Subtraction Test (7 - 3 = 4)
        performCalculation(driver, "com.android.calculator2:id/op_sub", "4", "Subtraction");

        // ✅ Multiplication Test (7 × 3 = 21)
        performCalculation(driver, "com.android.calculator2:id/op_mul", "21", "Multiplication");

        // ✅ Division Test (7 ÷ 3 ≈ 2.33)
        performCalculation(driver, "com.android.calculator2:id/op_div", "2.33", "Division");

        // Close session
        driver.quit();
    }

    // 🔹 Common reusable method for all arithmetic operations
    private static void performCalculation(AndroidDriver driver, String operationId, String expected, String testName)
            throws InterruptedException {

        // Clear previous input (if calculator supports it)
        try {
            driver.findElement(AppiumBy.id("com.android.calculator2:id/clr")).click();
        } catch (Exception ignored) {}

        // Click on 7
        driver.findElement(AppiumBy.id("com.android.calculator2:id/digit_7")).click();

        // Click on the operation (+, -, ×, ÷)
        driver.findElement(AppiumBy.id(operationId)).click();

        // Click on 3
        driver.findElement(AppiumBy.id("com.android.calculator2:id/digit_3")).click();

        // Click on equals
        driver.findElement(AppiumBy.id("com.android.calculator2:id/eq")).click();

        // Capture result
        WebElement resultElement = driver.findElement(AppiumBy.id("com.android.calculator2:id/result"));
        String result = resultElement.getText();

        // Validate result
        if (operationId.contains("div")) {
            // For division, just check prefix since result may vary (e.g., 2.3333, 2.33)
            if (result.startsWith(expected)) {
                System.out.println(testName + " Test Passed ✅ (Result: " + result + ")");
            } else {
                System.out.println(testName + " Test Failed ❌ (Result: " + result + ")");
            }
        } else {
            if (result.equals(expected)) {
                System.out.println(testName + " Test Passed ✅ (Result: " + result + ")");
            } else {
                System.out.println(testName + " Test Failed ❌ (Result: " + result + ")");
            }
        }

        Thread.sleep(2000);
    }
}
