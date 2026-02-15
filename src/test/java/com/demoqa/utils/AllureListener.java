package com.demoqa.utils;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.io.ByteArrayInputStream;

public class AllureListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testClass = result.getInstance();
        WebDriver driver = ((com.demoqa.base.BaseTest) testClass).getDriver();

        if (driver != null) {
            takeScreenshot(driver);
            attachPageSource(driver);
        }
        attachLogs("Тест упал: " + result.getThrowable());
    }

    @Attachment(value = "Скриншот ошибки", type = "image/png")
    private byte[] takeScreenshot(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "HTML страницы", type = "text/html")
    private String attachPageSource(WebDriver driver) {
        return driver.getPageSource();
    }

    @Attachment(value = "Логи ошибки", type = "text/plain")
    private String attachLogs(String log) {
        return log;
    }
}