package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.PracticeFormPage;
import com.demoqa.pages.PracticeFormPopupPage;
import com.demoqa.utils.AllureListener;
import io.qameta.allure.*;
import org.openqa.selenium.support.Color;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AllureListener.class)
@Epic("Форма регистрации")
@Feature("Валидация полей")
public class FieldValidationTest extends BaseTest {

    private PracticeFormPage formPage;
    private PracticeFormPopupPage popupPage;

    @BeforeMethod
    public void initPages() {
        formPage = new PracticeFormPage(driver);
        popupPage = new PracticeFormPopupPage(driver);
    }

    @Test(description = "Поле Mobile обязательно для заполнения")
    @Story("Валидация поля Mobile")
    @Severity(SeverityLevel.CRITICAL)
    public void testMobileFieldIsRequired() {
        Allure.step("Заполнение всех полей кроме Mobile");
        formPage.enterFirstName("John")
                .enterLastName("Doe")
                .selectGender("Male")
                .clickSubmit();

        Allure.step("Проверка, что попап не появился");
        Assert.assertFalse(popupPage.isPopupDisplayed(),
                "Форма отправилась без заполнения поля Mobile");
    }

    @Test(description = "Поле Mobile принимает только цифры")
    @Story("Валидация поля Mobile")
    @Severity(SeverityLevel.NORMAL)
    public void testMobileAcceptsOnlyDigits() {
        String mobileWithLetters = "abc123def45";

        Allure.step("Ввод номера с буквами: " + mobileWithLetters);
        formPage.enterMobileNumber(mobileWithLetters);
        String actualValue = formPage.getMobileValue();

        Allure.step("Проверка, что в поле только цифры");
        Assert.assertTrue(actualValue.matches("\\d*"),
                "Поле Mobile содержит нецифровые символы");
    }

    @Test(description = "Поле Email проверяет формат")
    @Story("Валидация поля Email")
    @Severity(SeverityLevel.CRITICAL)
    public void testEmailFormatValidation() {
        Allure.step("Ввод некорректного email: invalid-email");
        formPage.enterFirstName("John")
                .enterLastName("Doe")
                .selectGender("Male")
                .enterMobileNumber("1234567890")
                .enterEmail("invalid-email")
                .clickSubmit();

        Allure.step("Проверка, что попап не появился");
        Assert.assertFalse(popupPage.isPopupDisplayed(),
                "Форма отправилась с некорректным email");

        Allure.step("Проверка подсветки поля красным");
        String borderColor = formPage.getEmailFieldBorderColor();
        String redColorHex = Color.fromString(borderColor).asHex();
        Assert.assertEquals(redColorHex, "#dc3545",
                "Поле email не подсвечено красным при ошибке валидации");
    }

    @Test(description = "Поле First Name обязательно")
    @Story("Валидация обязательных полей")
    @Severity(SeverityLevel.CRITICAL)
    public void testFirstNameIsRequired() {
        Allure.step("Заполнение всех полей кроме First Name");
        formPage.enterLastName("Doe")
                .selectGender("Male")
                .enterMobileNumber("1234567890")
                .clickSubmit();

        Allure.step("Проверка, что попап не появился");
        Assert.assertFalse(popupPage.isPopupDisplayed(),
                "Форма отправилась без заполнения First Name");
    }

    @Test(description = "Максимальная длина имени")
    @Story("Граничные значения")
    @Severity(SeverityLevel.NORMAL)
    public void testFirstNameMaxLength() {
        String longName = "A".repeat(256);
        Allure.step("Ввод имени из 256 символов");

        formPage.enterFirstName(longName)
                .enterLastName("Doe")
                .selectGender("Male")
                .enterMobileNumber("1234567890")
                .clickSubmit();

        String actualName = formPage.getFirstNameValue();
        Allure.step("Проверка длины имени: " + actualName.length());
        Assert.assertTrue(actualName.length() <= 255,
                "Имя превышает допустимую длину");
    }
}