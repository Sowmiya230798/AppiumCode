package AppiumDemo.Appium;

import java.net.MalformedURLException;
import java.net.URL;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class InstallAppOnRealDevice {

    public static void main(String[] args) throws MalformedURLException {
        // Create UiAutomator2Options instead of DesiredCapabilities
        UiAutomator2Options options = new UiAutomator2Options();

        // Set required capabilities
        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        options.setDeviceName("Android Device"); // Real device name
        options.setPlatformVersion("16");       // Your Android version
        options.setApp("D:\\Eclipse\\APK Files\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");

        // Optional — If you already have the app installed and just want to open it
        // options.setAppPackage("com.swaglabsmobileapp");
        // options.setAppActivity("com.swaglabsmobileapp.SplashActivity");

        // Appium Server URL
        URL serverUrl = new URL("http://127.0.0.1:4723/");

        // Initialize the AndroidDriver
        AndroidDriver driver = new AndroidDriver(serverUrl, options);

        System.out.println("✅ App installed and launched successfully on the real device!");

        // Close the session
        driver.quit();
    }
}
