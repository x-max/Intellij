package com.test;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import java.io.File;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = "json:target/cucumber.json", features = {
        "src/test/resources/features/Notification.feature",
        "src/test/resources/features/SecureDialog.feature"},
        glue = "com.test.stepdefs")
public class RunCukesTest {

    private static AppiumDriverLocalService service;

    @BeforeClass
    public static void startAppium() {
        String osName = System.getProperty("os.name").toLowerCase();

        String nodePath = null;
        String appiumPath = null;

        if (osName.contains("mac")) {
//            mac path
            //nodePath = "C:/Users/806590/AppData/Local/Programs/appium-desktop/resources/app/node_modules";
            nodePath = "/usr/local/bin/node";
            appiumPath = "/usr/local/lib/node_modules/appium/build/lib/main.js";
            //appiumPath="C:/Users/806590/AppData/Local/Programs/appium-desktop/resources/app/node_modules/appium/build/lib/main.js";
        } else if (osName.contains("linux")) {
//      linux path
            nodePath = System.getenv("HOME") + "/.linuxbrew/bin/node";
            appiumPath = System.getenv("HOME") + "/.linuxbrew/lib/node_modules/appium/build/lib/main.js";
        }
        service = AppiumDriverLocalService.buildService(new AppiumServiceBuilder()
                .usingDriverExecutable(new File("C:/Program Files/nodejs/node.exe"))
                .usingPort(4723)
                .withAppiumJS(new File("C:/Users/806590/AppData/Local/Programs/appium-desktop/resources/app/node_modules/appium/build/lib/main.js")));
        service.start();
    }

    @AfterClass
    public static void stopAppium() {
        service.stop();
    }
}
