package ru.stqa.pft.addressbook.appmanager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;

import ru.stqa.pft.addressbook.SessionHelper;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;


public class ApplicationManager {
    private NavigateHelper navigateHelper;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private GroupHelper groupHelper;

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
        contactHelper = new ContactHelper(wd);
        navigateHelper = new NavigateHelper(wd);
        sessionHelper = new SessionHelper(wd);
        groupHelper = new GroupHelper(wd);
        sessionHelper.login("admin", "secret");
    }


    public void stop() {
        //wd.quit();
    }

    public GroupHelper getGroupHelper(){
        return groupHelper;
    }

    public ContactHelper getContactHelper(){
        return contactHelper;
    }

    public NavigateHelper getNavigateHelper(){
        return navigateHelper;
    }


}
