package com.demoqa.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class PracticeFormPopupPage {

    private WebDriver driver;


    @FindBy(className = "modal-content")
    private WebElement modalContent;

    @FindBy(className = "modal-title")
    private WebElement modalTitle;

    @FindBy(xpath = "//div[@class='table-responsive']//td[text()='Student Name']/following-sibling::td")
    private WebElement studentNameValue;

    @FindBy(xpath = "//div[@class='table-responsive']//td[text()='Student Email']/following-sibling::td")
    private WebElement studentEmailValue;

    @FindBy(xpath = "//div[@class='table-responsive']//td[text()='Gender']/following-sibling::td")
    private WebElement genderValue;

    @FindBy(xpath = "//div[@class='table-responsive']//td[text()='Mobile']/following-sibling::td")
    private WebElement mobileValue;

    @FindBy(xpath = "//div[@class='table-responsive']//td[text()='Date of Birth']/following-sibling::td")
    private WebElement dateOfBirthValue;

    @FindBy(xpath = "//div[@class='table-responsive']//td[text()='Subjects']/following-sibling::td")
    private WebElement subjectsValue;

    @FindBy(xpath = "//div[@class='table-responsive']//td[text()='Hobbies']/following-sibling::td")
    private WebElement hobbiesValue;

    @FindBy(xpath = "//div[@class='table-responsive']//td[text()='Picture']/following-sibling::td")
    private WebElement pictureValue;

    @FindBy(xpath = "//div[@class='table-responsive']//td[text()='Address']/following-sibling::td")
    private WebElement addressValue;

    @FindBy(xpath = "//div[@class='table-responsive']//td[text()='State and City']/following-sibling::td")
    private WebElement stateAndCityValue;

    @FindBy(id = "closeLargeModal")
    private WebElement closeButton;

    @FindBy(xpath = "//button[text()='Close']")
    private WebElement closeButtonByText;


    public PracticeFormPopupPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public boolean isPopupDisplayed() {
        try {
            return modalContent.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public String getModalTitle() {
        return modalTitle.getText();
    }

    public String getStudentName() {
        return studentNameValue.getText();
    }

    public String getStudentEmail() {
        return studentEmailValue.getText();
    }

    public String getGender() {
        return genderValue.getText();
    }

    public String getMobile() {
        return mobileValue.getText();
    }

    public String getDateOfBirth() {
        return dateOfBirthValue.getText();
    }

    public String getSubjects() {
        return subjectsValue.getText();
    }

    public String getHobbies() {
        return hobbiesValue.getText();
    }

    public String getPicture() {
        return pictureValue.getText();
    }

    public String getAddress() {
        return addressValue.getText();
    }

    public String getStateAndCity() {
        return stateAndCityValue.getText();
    }

    public void closePopup() {
        try {
            closeButton.click();
        } catch (Exception e) {
            closeButtonByText.click();
        }
    }

    public boolean isPopupClosed() {
        try {
            return !modalContent.isDisplayed();
        } catch (Exception e) {
            return true;
        }
    }

    private void clickWithJS(WebElement element) {
        org.openqa.selenium.JavascriptExecutor executor =
                (org.openqa.selenium.JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", element);
    }

    public String getAllPopupData() {
        StringBuilder sb = new StringBuilder();
        sb.append("Student Name: ").append(getStudentName()).append("\n");
        sb.append("Email: ").append(getStudentEmail()).append("\n");
        sb.append("Gender: ").append(getGender()).append("\n");
        sb.append("Mobile: ").append(getMobile()).append("\n");
        sb.append("Date of Birth: ").append(getDateOfBirth()).append("\n");
        sb.append("Subjects: ").append(getSubjects()).append("\n");
        sb.append("Hobbies: ").append(getHobbies()).append("\n");
        sb.append("Picture: ").append(getPicture()).append("\n");
        sb.append("Address: ").append(getAddress()).append("\n");
        sb.append("State and City: ").append(getStateAndCity()).append("\n");
        return sb.toString();
    }
}