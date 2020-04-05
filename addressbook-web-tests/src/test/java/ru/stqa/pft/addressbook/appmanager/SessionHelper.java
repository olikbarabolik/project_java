package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.pft.addressbook.appmanager.HelperBase;


public class SessionHelper extends HelperBase {

    private WebDriver wd;

    public SessionHelper(WebDriver wd) {
        super(wd);
    }

    public void login(String admin, String password) {

        type(By.name("user"), admin);
        type(By.name("pass"), password);
        click(By.xpath("//input[@value='Login']"));
    }
}
