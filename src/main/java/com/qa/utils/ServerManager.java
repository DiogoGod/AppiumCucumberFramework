package com.qa.utils;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServerHasNotBeenStartedLocallyException;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.openqa.selenium.remote.RemoteWebElement;

import java.io.File;
import java.net.URL;
import java.util.HashMap;


public class ServerManager {
    private static final ThreadLocal<AppiumDriverLocalService> server= new ThreadLocal<>();
     TestUtils utils= new TestUtils();

     public AppiumDriverLocalService getServer(){
         return server.get();
     }

     public void startServer(){

         AppiumDriverLocalService server= WindowsGetAppiumService();
         server.start();
         if(!server.isRunning()){
             throw new AppiumServerHasNotBeenStartedLocallyException("Appium server not started . Abort!!");


         }
         //server.clearOutPutStreams();
         this.server.set(server);

     }

    public AppiumDriverLocalService getAppiumServerDefault(){

     return AppiumDriverLocalService.buildDefaultService();
    }
    public AppiumDriverLocalService WindowsGetAppiumService() {
        GlobalParams params = new GlobalParams();
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE));

    }

    public AppiumDriverLocalService MacGetAppiumService(){
        GlobalParams params= new GlobalParams();
        HashMap<String, String> environment= new HashMap<>();
        environment.put("PATH","/Users/diogo.cardoso/Library/Android/sdk/platform-tools:/Users/diogo.cardoso/Library/Android/sdk/cmdline-tools:/Library/Java/JavaVirtualMachines/jdk-23.jdk/Contents/Home/bin:/usr/local/opt/node@14/bin:/opt/homebrew/bin:/opt/homebrew/sbin:/usr/local/bin:/System/Cryptexes/App/usr/bin:/usr/bin:/bin:/usr/sbin:/sbin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/local/bin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/bin:/var/run/com.apple.security.cryptexd/codex.system/bootstrap/usr/appleinternal/bin:/Library/Apple/usr/bin:/opt/puppetlabs/bin");
        environment.put("ANDROID_HOME","/Users/diogo.cardoso/Library/Android/sdk");
        environment.put("JAVA_HOME","/Library/Java/JavaVirtualMachines/jdk-23.jdk/Contents/Home");
        return AppiumDriverLocalService.buildService(new AppiumServiceBuilder()

                .usingDriverExecutable(new File("/opt/homebrew/bin/node"))
                .withAppiumJS(new File("/opt/homebrew/bin/appium"))
                .usingAnyFreePort()
                .withArgument(GeneralServerFlag.SESSION_OVERRIDE)
                .withIPAddress("127.0.0.1")
                        .usingPort(4723)
                .withEnvironment(environment));


    }

}
