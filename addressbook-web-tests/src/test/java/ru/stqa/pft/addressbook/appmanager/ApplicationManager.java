package ru.stqa.pft.addressbook.appmanager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
//import ru.stqa.pft.addressbook.SessionHelper;
import ru.stqa.pft.addressbook.SessionHelper;
import ru.stqa.pft.addressbook.tests.ContactData;


public class ApplicationManager{
    private NavigateHelper navigateHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    WebDriver wd;

    public void init() {
        //Если наш driver firefox
        //System.setProperty("webdriver.firefox.driver", "C://1806/2/geckodriver.exe");
        //driver = new FirefoxDriver();

        //Если наш driver chrome
        System.setProperty("webdriver.chrome.driver", "C://1806/2/3/chromedriver.exe");
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        wd.get("http://localhost/addressbook/");
        groupHelper = new GroupHelper(wd);
        navigateHelper = new NavigateHelper(wd);
        sessionHelper = new SessionHelper(wd);

        sessionHelper.login("admin", "secret");
    }



    public void submitContactGroup() {
        groupHelper.submitContactGroup();
    }

    public void fillContactForm(ContactData contactData) {
        groupHelper.fillContactForm(contactData);
    }

    public void gotoToContactPage() {
        navigateHelper.gotoToContactPage();
    }

    public void stop() {
        wd.quit();
    }

    public boolean isElementPresent(By by) {
        try {
            wd.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


}
