package com.halas.listener;

import com.halas.utils.TakeScreenshot;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class MyTestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult arg0) {
        TakeScreenshot.takeScreenshot();
    }
}
