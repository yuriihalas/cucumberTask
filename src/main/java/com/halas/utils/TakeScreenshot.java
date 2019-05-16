package com.halas.utils;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;

import static com.halas.driver.WebDriverManager.getWebDriver;

public class TakeScreenshot {
    private static final Logger LOG = LogManager.getLogger(TakeScreenshot.class);
    private static final String PATH_TO_SAVE_SCREEN = "src/main/resources/screenshots/screenshot-%s.png";

    private TakeScreenshot() {
    }

    public static void takeScreenshot() {
        try {
            File scrFile = ((TakesScreenshot) getWebDriver()).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(scrFile, new File(String.format(PATH_TO_SAVE_SCREEN, MyDate.getCurrentDateTime())));
            LOG.info("Screenshot taken.");
        } catch (IOException e) {
            LOG.error("Can't save screenshot file, in takeScreenshot..");
            LOG.error("Class: " + e.getClass());
            LOG.error("Message: " + e.getMessage());
            LOG.error(e.getStackTrace());
        }
    }
}
