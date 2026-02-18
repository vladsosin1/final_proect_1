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
@Feature("Модальное окно результатов")
public class PopupValidationTest extends BaseTest {

    private PracticeFormPage formPage;
    private PracticeFormPopupPage popupPage;

    @BeforeMethod
    public void initPages() {
        formPage = new PracticeFormPage(driver);
        popupPage = new PracticeFormPopupPage(driver);
    }

    @Test(description = "Проверка всех данных в модальном окне и фиксированным email")
    @Story("Полное заполнение формы")
    @Severity(SeverityLevel.BLOCKER)
    @Description("Проверка, что модальное окно содержит все введенные данные (ФИО, email test1@mail.ru)")
    public void testPopupContainsAllEnteredData() {
        Allure.step("Генерация тестовых данных (ФИО)");
        String firstName = TestDataGenerator.generateFirstName();
        String lastName = TestDataGenerator.generateLastName();
        String email = TestDataGenerator.FIXED_EMAIL;
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

        Allure.step("Заполнение всех полей формы. ФИО: " + firstName + " " + lastName + ", Email: " + email);
        formPage.fillAllFields(
                firstName, lastName, email, gender, mobile,
                day, month, year, subjects, hobbies, picturePath,
                address, state, city
        );

        Allure.step("Проверка отображения модального окна");
        Assert.assertTrue(popupPage.isPopupDisplayed(),
                "Модальное окно не появилось после отправки формы!");

        Allure.step("Проверка заголовка модального окна");
        Assert.assertEquals(popupPage.getModalTitle(), "Thanks for submitting the form",
                "Неверный заголовок модального окна");

        Allure.step("Проверка имени студента в модальном окне");
        String expectedFullName = firstName + " " + lastName;
        Assert.assertEquals(popupPage.getStudentName(), expectedFullName,
                "Имя студента в модальном окне не совпадает. Ожидалось: '" + expectedFullName +
                        "', получено: '" + popupPage.getStudentName() + "'");

        Allure.step("Проверка email в модальном окне");
        Assert.assertEquals(popupPage.getStudentEmail(), email,
                "Email в модальном окне не совпадает. Ожидалось: '" + email +
                        "', получено: '" + popupPage.getStudentEmail() + "'");

        Allure.step("Проверка пола в модальном окне");
        Assert.assertEquals(popupPage.getGender(), gender,
                "Пол в модальном окне не совпадает");

        Allure.step("Проверка телефона в модальном окне");
        Assert.assertEquals(popupPage.getMobile(), mobile,
                "Телефон в модальном окне не совпадает");

        Allure.step("Проверка даты рождения в модальном окне");
        Assert.assertEquals(popupPage.getDateOfBirth(), day + " " + month + "," + year,
                "Дата рождения в модальном окне не совпадает");

        Allure.step("Проверка Subjects в модальном окне");
        Assert.assertEquals(popupPage.getSubjects(), String.join(", ", subjects),
                "Subjects в модальном окне не совпадают");

        Allure.step("Проверка Hobbies в модальном окне");
        Assert.assertEquals(popupPage.getHobbies(), String.join(", ", hobbies),
                "Hobbies в модальном окне не совпадают");

        Allure.step("Проверка загруженного файла в модальном окне");
        Assert.assertTrue(popupPage.getPicture().contains("test.jpg"),
                "Имя файла в модальном окне не совпадает");

        Allure.step("Проверка адреса в модальном окне");
        Assert.assertEquals(popupPage.getAddress(), address,
                "Адрес в модальном окне не совпадает. Ожидалось: '" + address +
                        "', получено: '" + popupPage.getAddress() + "'");

        Allure.step("Проверка State и City в модальном окне");
        Assert.assertEquals(popupPage.getStateAndCity(), state + " " + city,
                "State/City в модальном окне не совпадает");
    }

    @Test(description = "Проверка кнопки Close в модальном окне")
    @Story("Закрытие модального окна")
    @Severity(SeverityLevel.NORMAL)
    @Description("Проверка, что кнопка Close закрывает модальное окно после отправки")
    public void testCloseButtonClosesPopup() {
        String firstName = TestDataGenerator.generateFirstName();
        String lastName = TestDataGenerator.generateLastName();

        Allure.step("Заполнение обязательных полей. ФИО: " + firstName + " " + lastName);
        formPage.fillMinimumRequiredFields(
                firstName, lastName, "Male", TestDataGenerator.generateMobileNumber()
        );

        Allure.step("Проверка появления модального окна");
        Assert.assertTrue(popupPage.isPopupDisplayed(),
                "Модальное окно не появилось");

        Allure.step("Закрытие модального окна");
        popupPage.closePopup();

        Allure.step("Проверка закрытия модального окна");
        Assert.assertTrue(popupPage.isPopupClosed(),
                "Модальное окно не закрылось после нажатия Close");
    }
}