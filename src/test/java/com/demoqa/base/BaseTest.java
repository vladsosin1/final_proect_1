package com.demoqa.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.time.Duration;

public class BaseTest {

    protected WebDriver driver;
    protected static final String BASE_URL = "https://demoqa.com/automation-practice-form";

    @BeforeMethod
    public void setUp() {

        WebDriverManager.chromedriver().setup();

        // Настройки браузера
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized"); // Открыть на весь экран
        options.addArguments("--disable-notifications"); // Отключить уведомления

        driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Неявное ожидание
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(20));

        // Открываем сайт
        driver.get(BASE_URL);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit(); // Закрываем браузер после каждого теста
        }
    }
    public WebDriver getDriver() {
        return driver;
    }
}