package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.PracticeFormPage;
import com.demoqa.pages.PracticeFormPopupPage;
import com.demoqa.utils.TestDataGenerator;
import com.demoqa.utils.AllureListener;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AllureListener.class)
@Epic("Форма регистрации")
@Feature("Заполнение формы")
public class FormFillingTest extends BaseTest {

    @Test(description = "Заполнение всех обязательных полей")
    @Story("Отправка формы с минимальными данными")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка, что форма отправляется при заполнении только обязательных полей")
    @Owner("Автор теста")
    public void testSubmitWithMinimumRequiredFields() {
        PracticeFormPage formPage = new PracticeFormPage(driver);

        Allure.step("Генерация тестовых данных");
        String firstName = TestDataGenerator.generateFirstName();
        String lastName = TestDataGenerator.generateLastName();
        String gender = "Male";
        String mobile = TestDataGenerator.generateMobileNumber();

        Allure.step("Заполнение обязательных полей: имя, фамилия, пол, телефон");
        PracticeFormPopupPage popupPage = formPage.fillMinimumRequiredFields(
                firstName, lastName, gender, mobile
        );

        Allure.step("Проверка отображения попапа");
        Assert.assertTrue(popupPage.isPopupDisplayed(),
                "Попап с результатами не появился после отправки формы!");

        Allure.step("Проверка данных в попапе");
        String expectedFullName = firstName + " " + lastName;
        Assert.assertEquals(popupPage.getStudentName(), expectedFullName,
                "Имя студента в попапе не совпадает с введенным");
        Assert.assertEquals(popupPage.getMobile(), mobile,
                "Номер телефона в попапе не совпадает с введенным");
        Assert.assertEquals(popupPage.getGender(), gender,
                "Пол в попапе не совпадает с выбранным");
    }

    @Test(description = "Проверка обязательности поля Mobile")
    @Story("Валидация полей")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка, что форма не отправляется без заполнения поля Mobile")
    public void testMobileFieldIsRequired() {
        PracticeFormPage formPage = new PracticeFormPage(driver);

        Allure.step("Заполнение всех полей кроме Mobile");
        formPage.enterFirstName("John")
                .enterLastName("Doe")
                .selectGender("Male")
                .clickSubmit();

        Allure.step("Проверка, что попап не появился");
        PracticeFormPopupPage popupPage = new PracticeFormPopupPage(driver);
        Assert.assertFalse(popupPage.isPopupDisplayed(),
                "Попап появился, хотя поле Mobile не было заполнено!");
    }
}