package com.demoqa.tests;

import com.demoqa.base.BaseTest;
import com.demoqa.pages.PracticeFormPage;
import com.demoqa.pages.PracticeFormPopupPage;
import com.demoqa.utils.TestDataGenerator;
import com.demoqa.utils.AllureListener;
import io.qameta.allure.*;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(AllureListener.class)
@Epic("Форма регистрации")
@Feature("Попап результатов")
public class PopupValidationTest extends BaseTest {

    private PracticeFormPage formPage;
    private PracticeFormPopupPage popupPage;

    @BeforeMethod
    public void initPages() {
        formPage = new PracticeFormPage(driver);
        popupPage = new PracticeFormPopupPage(driver);
    }

    @Test(description = "Проверка всех данных в попапе")
    @Story("Полное заполнение формы")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка, что попап содержит все введенные данные")
    public void testPopupContainsAllEnteredData() {
        Allure.step("Генерация тестовых данных");
        String firstName = TestDataGenerator.generateFirstName();
        String lastName = TestDataGenerator.generateLastName();
        String email = TestDataGenerator.generateEmail();
        String gender = "Female";
        String mobile = TestDataGenerator.generateMobileNumber();
        String day = "15";
        String month = "May";
        String year = "1995";
        String[] subjects = {"Maths", "English"};
        String[] hobbies = {"Reading"};
        String picturePath = System.getProperty("user.dir") + "/src/test/resources/test.jpg";
        String address = TestDataGenerator.generateAddress();
        String state = "NCR";
        String city = "Delhi";

        Allure.step("Заполнение всех полей формы");
        formPage.fillAllFields(
                firstName, lastName, email, gender, mobile,
                day, month, year, subjects, hobbies, picturePath,
                address, state, city
        );

        Allure.step("Проверка отображения попапа");
        Assert.assertTrue(popupPage.isPopupDisplayed(),
                "Попап не появился после отправки формы!");

        Allure.step("Проверка заголовка попапа");
        Assert.assertEquals(popupPage.getModalTitle(), "Thanks for submitting the form",
                "Неверный заголовок попапа");

        Allure.step("Проверка имени студента");
        Assert.assertEquals(popupPage.getStudentName(), firstName + " " + lastName,
                "Имя студента в попапе не совпадает");

        Allure.step("Проверка email");
        Assert.assertEquals(popupPage.getStudentEmail(), email,
                "Email в попапе не совпадает");

        Allure.step("Проверка пола");
        Assert.assertEquals(popupPage.getGender(), gender,
                "Пол в попапе не совпадает");

        Allure.step("Проверка телефона");
        Assert.assertEquals(popupPage.getMobile(), mobile,
                "Телефон в попапе не совпадает");

        Allure.step("Проверка даты рождения");
        Assert.assertEquals(popupPage.getDateOfBirth(), day + " " + month + "," + year,
                "Дата рождения в попапе не совпадает");

        Allure.step("Проверка Subjects");
        Assert.assertEquals(popupPage.getSubjects(), String.join(", ", subjects),
                "Subjects в попапе не совпадают");

        Allure.step("Проверка Hobbies");
        Assert.assertEquals(popupPage.getHobbies(), String.join(", ", hobbies),
                "Hobbies в попапе не совпадают");

        Allure.step("Проверка загруженного файла");
        Assert.assertTrue(popupPage.getPicture().contains("test.jpg"),
                "Имя файла в попапе не совпадает");

        Allure.step("Проверка адреса");
        Assert.assertEquals(popupPage.getAddress(), address,
                "Адрес в попапе не совпадает");

        Allure.step("Проверка State и City");
        Assert.assertEquals(popupPage.getStateAndCity(), state + " " + city,
                "State/City в попапе не совпадает");
    }

    @Test(description = "Проверка кнопки Close")
    @Story("Закрытие попапа")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка, что кнопка Close закрывает попап")
    public void testCloseButtonClosesPopup() {
        Allure.step("Заполнение обязательных полей");
        formPage.fillMinimumRequiredFields(
                "John", "Doe", "Male", "1234567890"
        );

        Allure.step("Проверка появления попапа");
        Assert.assertTrue(popupPage.isPopupDisplayed(),
                "Попап не появился");

        Allure.step("Закрытие попапа");
        popupPage.closePopup();

        Allure.step("Проверка закрытия попапа");
        Assert.assertTrue(popupPage.isPopupClosed(),
                "Попап не закрылся после нажатия Close");
    }
}