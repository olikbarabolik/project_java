package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.ie.*;


public class RegistrationHelper {

    private final ApplicationManager app;
    private WebDriver wd;

    public  RegistrationHelper(ApplicationManager app) {
        this.app = app;
        wd = app.getDriver();
    }

    public void start(String username, String email){
        wd.get(app.getProperty("web.baseUrl")+ "/signup_page.php");
    }
}
