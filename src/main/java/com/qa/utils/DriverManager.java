package com.qa.utils;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.IOException;
import java.net.URL;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driver= new ThreadLocal<>();
    TestUtils utils= new TestUtils();

    public AppiumDriver getDriver(){
        return driver.get();
    }
    public void setDriver(AppiumDriver driver2){
        driver.set(driver2);

    }

    public void initializeDriver() throws Exception {
        AppiumDriver driver= null;
        GlobalParams params = new GlobalParams();
        PropertyManager props= new PropertyManager();

        if(driver == null){
            try{
                switch(params.getPlatformName()){
                 case "Android":
                     driver = new AndroidDriver(new ServerManager().getServer().getUrl(), new CapabilitiesManager().getCaps());
                     break;
                 case "iOS":
                     driver= new IOSDriver(new ServerManager().getServer().getUrl(),  new CapabilitiesManager().getCaps());
                     break;
             }
             if(driver== null){
                 throw new Exception("driver is null. ABORT!!");
             }

             this.driver.set(driver);;
            }catch(IOException e){
               e.printStackTrace();

               throw e;
            }
        }
    }

}
