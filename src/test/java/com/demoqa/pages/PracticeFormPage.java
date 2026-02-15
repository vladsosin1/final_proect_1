package com.demoqa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;

public class PracticeFormPage {

    private WebDriver driver;

    // ============= ЛОКАТОРЫ =============
    @FindBy(id = "firstName")
    private WebElement firstNameInput;

    @FindBy(id = "lastName")
    private WebElement lastNameInput;

    @FindBy(id = "userEmail")
    private WebElement userEmailInput;

    @FindBy(id = "userNumber")
    private WebElement mobileNumberInput;

    @FindBy(xpath = "//label[contains(text(),'Male')]")
    private WebElement genderMaleLabel;

    @FindBy(xpath = "//label[contains(text(),'Female')]")
    private WebElement genderFemaleLabel;

    @FindBy(xpath = "//label[contains(text(),'Other')]")
    private WebElement genderOtherLabel;

    @FindBy(id = "dateOfBirthInput")
    private WebElement dateOfBirthInput;

    @FindBy(id = "subjectsInput")
    private WebElement subjectsInput;

    @FindBy(xpath = "//label[contains(text(),'Sports')]")
    private WebElement hobbiesSportsLabel;

    @FindBy(xpath = "//label[contains(text(),'Reading')]")
    private WebElement hobbiesReadingLabel;

    @FindBy(xpath = "//label[contains(text(),'Music')]")
    private WebElement hobbiesMusicLabel;

    @FindBy(id = "uploadPicture")
    private WebElement uploadPictureInput;

    @FindBy(id = "currentAddress")
    private WebElement currentAddressTextarea;

    @FindBy(id = "react-select-3-input")
    private WebElement stateInput;

    @FindBy(id = "react-select-4-input")
    private WebElement cityInput;

    @FindBy(id = "submit")
    private WebElement submitButton;

    //КОНСТРУКТОР
    public PracticeFormPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    //МЕТОДЫ
    public PracticeFormPage enterFirstName(String firstName) {
        firstNameInput.sendKeys(firstName);
        return this;
    }

    public PracticeFormPage enterLastName(String lastName) {
        lastNameInput.sendKeys(lastName);
        return this;
    }

    public PracticeFormPage enterEmail(String email) {
        userEmailInput.sendKeys(email);
        return this;
    }

    public PracticeFormPage selectGender(String gender) {
        switch (gender.toLowerCase()) {
            case "male":
                clickWithJS(genderMaleLabel);
                break;
            case "female":
                clickWithJS(genderFemaleLabel);
                break;
            case "other":
                clickWithJS(genderOtherLabel);
                break;
            default:
                throw new IllegalArgumentException("Неверный пол: " + gender);
        }
        return this;
    }

    public PracticeFormPage enterMobileNumber(String mobile) {
        mobileNumberInput.sendKeys(mobile);
        return this;
    }

    public PracticeFormPage enterDateOfBirth(String date) {
        dateOfBirthInput.click();
        dateOfBirthInput.clear();
        dateOfBirthInput.sendKeys(date);
        dateOfBirthInput.sendKeys(Keys.ENTER);
        return this;
    }

    public PracticeFormPage enterSubjects(String subject) {
        subjectsInput.sendKeys(subject);
        subjectsInput.sendKeys(Keys.ENTER);
        return this;
    }

    public PracticeFormPage selectHobby(String hobby) {
        switch (hobby.toLowerCase()) {
            case "sports":
                clickWithJS(hobbiesSportsLabel);
                break;
            case "reading":
                clickWithJS(hobbiesReadingLabel);
                break;
            case "music":
                clickWithJS(hobbiesMusicLabel);
                break;
        }
        return this;
    }

    public PracticeFormPage uploadPicture(String filePath) {
        uploadPictureInput.sendKeys(filePath);
        return this;
    }

    public PracticeFormPage enterCurrentAddress(String address) {
        currentAddressTextarea.sendKeys(address);
        return this;
    }

    public PracticeFormPage selectState(String state) {
        stateInput.sendKeys(state);
        stateInput.sendKeys(Keys.ENTER);
        return this;
    }

    public PracticeFormPage selectCity(String city) {
        cityInput.sendKeys(city);
        cityInput.sendKeys(Keys.ENTER);
        return this;
    }

    public PracticeFormPopupPage clickSubmit() {
        clickWithJS(submitButton);
        return new PracticeFormPopupPage(driver);
    }

    private void clickWithJS(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public PracticeFormPopupPage fillMinimumRequiredFields(String firstName, String lastName,
                                                           String gender, String mobile) {
        return this.enterFirstName(firstName)
                .enterLastName(lastName)
                .selectGender(gender)
                .enterMobileNumber(mobile)
                .clickSubmit();
    }


    public PracticeFormPage selectDateOfBirth(String day, String month, String year) {
        dateOfBirthInput.click();

        WebElement monthDropdown = driver.findElement(By.className("react-datepicker__month-select"));
        Select selectMonth = new Select(monthDropdown);
        selectMonth.selectByVisibleText(month);

        WebElement yearDropdown = driver.findElement(By.className("react-datepicker__year-select"));
        Select selectYear = new Select(yearDropdown);
        selectYear.selectByVisibleText(year);

        WebElement dayElement = driver.findElement(
                By.xpath(String.format("//div[contains(@class, 'react-datepicker__day') and not(contains(@class, '--outside-month')) and text()='%s']", day))
        );
        dayElement.click();

        return this;
    }


    public PracticeFormPage selectSubjects(String... subjects) {
        for (String subject : subjects) {
            subjectsInput.sendKeys(subject);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            subjectsInput.sendKeys(Keys.ENTER);
        }
        return this;
    }


    public PracticeFormPopupPage fillAllFields(
            String firstName, String lastName, String email, String gender,
            String mobile, String day, String month, String year,
            String[] subjects, String[] hobbies, String picturePath,
            String address, String state, String city) {

        return this.enterFirstName(firstName)
                .enterLastName(lastName)
                .enterEmail(email)
                .selectGender(gender)
                .enterMobileNumber(mobile)
                .selectDateOfBirth(day, month, year)
                .selectSubjects(subjects)
                .selectHobby(hobbies.length > 0 ? hobbies[0] : "")
                .uploadPicture(picturePath)
                .enterCurrentAddress(address)
                .selectState(state)
                .selectCity(city)
                .clickSubmit();
    }

    // МЕТОДЫ ДЛЯ ТЕСТОВ ВАЛИДАЦИИ
    public String getMobileValue() {
        return mobileNumberInput.getAttribute("value");
    }

    public String getFirstNameValue() {
        return firstNameInput.getAttribute("value");
    }

    public String getEmailFieldBorderColor() {
        return userEmailInput.getCssValue("border-color");
    }

    public PracticeFormPage clearMobileField() {
        mobileNumberInput.clear();
        return this;
    }

    public PracticeFormPage clearEmailField() {
        userEmailInput.clear();
        return this;
    }

    public boolean isFieldInvalid(WebElement element) {
        String classAttribute = element.getAttribute("class");
        return classAttribute != null && classAttribute.contains("is-invalid");
    }
}