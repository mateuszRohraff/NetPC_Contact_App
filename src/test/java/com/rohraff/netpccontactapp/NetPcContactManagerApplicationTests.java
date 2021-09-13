package com.rohraff.netpccontactapp;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NetPcContactManagerApplicationTests {

    @LocalServerPort
    private int port;

    private WebDriver driver;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        this.driver = new ChromeDriver();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }
    /**
     * metoda logująca się do aplikacji
     */
    private void login(String username, String password) {
        driver.get("http://localhost:" + this.port + "/login");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login(username, password);
    }
    /**
     * metoda wykonująca proces rejestracji
     */
    private void signUp(String username, String password) {
        driver.get("http://localhost:" + this.port + "/signup");
        SignUpPage signUpPage = new SignUpPage(driver);
        signUpPage.signUp("Mateusz", "Kowalski", username, password);
    }
    /**
     * Test sprawdzający poprawność wyświetlenia strony Sign Up
     */
    @Test
    public void getSignUpPage() {
        driver.get("http://localhost:" + this.port + "/signup");
        Assertions.assertEquals("Sign Up", driver.getTitle());
    }
    /**
     * Test sprawdzający poprawność wyświetlenia strony Login
     */
    @Test
    public void getLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }
    /**
     * Test sprawdzający poprawność procesu rejestracji i logowania
     */
    @Test
    public void signUpAndLogin() throws InterruptedException {
        signUp("user1", "password1");
        login("user1", "password1");

        Assertions.assertEquals("Home", driver.getTitle());
    }
    /**
     * Test sprawdzający poprawność walidacji hasła podczas rejestracji (brak cyfry)
     */
    @Test
    void signUpAndLoginWithBadCredentialsDigit() {
        signUp("user", "password");
        Assertions.assertEquals("Sign Up", driver.getTitle());
        Assertions.assertEquals("The password must contain at least one digit!", driver.findElement(By.id("error-msg")).getText());
    }
    /**
     * Test sprawdzający poprawność walidacji hasła (zbyt krótkie hasło)
     */
    @Test
    void signUpAndLoginWithBadCredentialsShort() {
        signUp("user", "user1");
        Assertions.assertEquals("Sign Up", driver.getTitle());
        Assertions.assertEquals("Password must be longer than 8 characters!", driver.findElement(By.id("error-msg")).getText());
    }
    /**
     * Test sprawdzający przejście do strony logowania oraz rejestracji z /home
     */
    @Test
    void signUpAndLoginFromHome() {
        HomePage homePage = new HomePage(driver);

        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Home", driver.getTitle());
        homePage.goToLoginPage();
        Assertions.assertEquals("Login", driver.getTitle());

        driver.get("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Home", driver.getTitle());
        homePage.goToSignUpPage();
        Assertions.assertEquals("Sign Up", driver.getTitle());
    }
    /**
     * Test sprawdzający poprawność mechanizmu blokowaniu rejestracji nowego użytkownika
     * który podał istniejącą wartość "Username"
     */
    @Test
    void uniqueUsername () {
        signUp("user", "Password1");
        signUp("user", "Password1");
        Assertions.assertEquals("Sign Up", driver.getTitle());
        Assertions.assertEquals("The username already exists.", driver.findElement(By.id("error-msg")).getText());

    }
}
