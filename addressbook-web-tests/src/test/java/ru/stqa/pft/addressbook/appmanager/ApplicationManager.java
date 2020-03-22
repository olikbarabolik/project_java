package ru.stqa.pft.addressbook.appmanager;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
//import ru.stqa.pft.addressbook.SessionHelper;
import ru.stqa.pft.addressbook.SessionHelper;
import ru.stqa.pft.addressbook.tests.ContactData;
import ru.stqa.pft.addressbook.tests.GroupData;


public class ApplicationManager{
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



    public void submitContactGroup() {
        contactHelper.submitContactGroup();
    }

    public void submitGroupCreation() {
        groupHelper.submitGroupCreation();
    }

    public void returnToGroupPage(){ navigateHelper.returnToGroupPage(); }

    public void fillContactForm(ContactData contactData) {
        contactHelper.fillContactForm(contactData);
    }

    public void fillGroupForm(GroupData groupData) {
        groupHelper.fillGroupForm(groupData);
    }

    public void selectGroup(){
        groupHelper.selectGroup();
    }

    public void deleteSelectedGroups(){
        groupHelper.deleteSelectedGroups();
    }

    public void gotoToContactPage() {
        navigateHelper.gotoToContactPage();
    }

    public void gotoToGroupPage() {
        navigateHelper.gotoToGroupPage();
    }

    public void initGroupCreation() {
        groupHelper.initGroupCreation();
    }


        public void stop() {
        //wd.quit();
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
