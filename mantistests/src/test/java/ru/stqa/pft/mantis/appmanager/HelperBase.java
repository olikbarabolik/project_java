package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.ie.*;

import java.io.File;
import java.util.concurrent.TimeUnit;

import  javax.mail.MessagingException;

public class HelperBase {

    protected ApplicationManager app;
    protected WebDriver wd;

    public HelperBase(ApplicationManager app){
        this.app = app;
        this.wd = app.getDriver();
    }

    protected void click (By locator) { wd.findElement(locator).click();}

    protected void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = wd.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                wd.findElement(locator).clear();
                wd.findElement(locator).sendKeys(text);
            }
        }
    }

    protected void attach(By locator, File file){
        if (file!=null){
            wd.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }




}
