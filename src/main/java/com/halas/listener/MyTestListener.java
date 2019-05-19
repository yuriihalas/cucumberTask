package com.halas.listener;

import com.halas.utils.MyDate;
import com.halas.utils.TakeScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

import java.io.File;

public class MyTestListener extends TestListenerAdapter {
    private static final String PATH_TO_SAVE_SCREEN_FORMAT = "src/main/resources/screenshots/%s.png";

    @Override
    public void onTestFailure(ITestResult arg0) {
        String nameNewImg = arg0.getThrowable().getClass().getSimpleName() + MyDate.getCurrentDateTime();
        File outputFolder = new File(String.format(
                PATH_TO_SAVE_SCREEN_FORMAT, nameNewImg));
        TakeScreenshot.takeScreenshot(outputFolder);
    }
}
