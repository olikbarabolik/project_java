package ru.stqa.pft.addressbook.appmanager;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;

import java.util.NoSuchElementException;

//import sun.plugin2.util.*;

public class HelperBase {

    protected WebDriver wd;

    public HelperBase(WebDriver wd){
        this.wd = wd;
    }

    public void type(By locator, String text){
        wd.findElement(locator).click();
        wd.findElement(locator).clear();
        wd.findElement(locator).sendKeys(text);
    }

    public void click(By locator){
        wd.findElement(locator).click();
    }

    public boolean isAlertPresent() {
        try {
            wd.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    public void selectObject(){
        click(By.name("selected[]"));
    }

    public void updateObject(){
        click(By.name("update"));
    }

    public void clickOK(){
        wd.switchTo().alert().accept();
    }

    public boolean isElementPresent(By locator){
        System.out.println("!!!!!!!isElementPresent" + wd);
        System.out.println("!!!!!!!locator" + locator);
        /*try{  System.out.println("!!!!!!!1" + locator);
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException ex){System.out.println("!!!!!!!2" + locator);
            return false;
        }*/ return false;
    }



}
