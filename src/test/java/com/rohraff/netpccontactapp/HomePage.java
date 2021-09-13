package com.rohraff.netpccontactapp;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {
    @FindBy(id = "login-button")
    private WebElement loginButton;

    @FindBy(id = "signup-button")
    private WebElement signUpButton;

    public HomePage(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    public void goToLoginPage() {
        loginButton.click();
    }

    public void goToSignUpPage() {
        signUpButton.click();
    }
}
