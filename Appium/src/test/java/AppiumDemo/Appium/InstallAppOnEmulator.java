package AppiumDemo.Appium;

import java.net.MalformedURLException;
import java.net.URL;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class InstallAppOnEmulator {

    public static void main(String[] args) throws MalformedURLException {

    	//Installation through the script in the emulator
        UiAutomator2Options options = new UiAutomator2Options();
        options.setAutomationName("UiAutomator2");
        options.setPlatformName("Android");
        
     // use your adb device name
        options.setDeviceName("emulator-5554"); 
     // Use Android pixel 3a emulator if possible
        options.setPlatformVersion("9.0"); 
        options.setApp("D:\\Eclipse\\APK Files\\Android.SauceLabs.Mobile.Sample.app.2.7.1.apk");

        // ✅ Use correct package and activity from adb output
        options.setAppPackage("com.swaglabsmobileapp");
        options.setAppActivity("com.swaglabsmobileapp.SplashActivity");

        // Add wait capability (helps if splash takes time to load)
        options.setCapability("appWaitActivity", "*");

        // Connect to Appium server
        URL url = new URL("http://127.0.0.1:4723/");
        AndroidDriver driver = new AndroidDriver(url, options);

        System.out.println("✅ App launched successfully!");

        
        
        
        driver.quit();
    }
}
