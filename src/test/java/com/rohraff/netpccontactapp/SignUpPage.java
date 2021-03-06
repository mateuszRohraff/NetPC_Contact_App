package com.rohraff.netpccontactapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstNameField;

    @FindBy(id = "inputLastName")
    private WebElement lastNameField;

    @FindBy(id = "inputUsername")
    private WebElement usernameFiled;

    @FindBy(id = "inputPassword")
    private WebElement passwordField;

    @FindBy(id = "submit-button")
    private WebElement submitButton;

    public SignUpPage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void signUp(String firstName, String lastName, String username, String password) {
        this.firstNameField.sendKeys(firstName);
        this.lastNameField.sendKeys(lastName);
        this.usernameFiled.sendKeys(username);
        this.passwordField.sendKeys(password);
        this.submitButton.click();
    }
}
