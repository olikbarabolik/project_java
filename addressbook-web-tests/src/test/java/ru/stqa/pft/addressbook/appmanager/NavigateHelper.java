package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;

public class NavigateHelper extends HelperBase{
    private WebDriver wd;

    public NavigateHelper(WebDriver wd) {
        super(wd);
    }

    public void gotoToContactPage() {
        click(By.linkText("add new"));
        //wd.findElement().click();
    }


}