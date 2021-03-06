package ru.stqa.pft.addressbook.appmanager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.Platform;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.ie.*;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.GroupData;

import org.openqa.selenium.Capabilities;

public class ApplicationManager {
    private final Properties properties;
    private NavigateHelper navigateHelper;
    private ContactHelper contactHelper;
    private SessionHelper sessionHelper;
    private GroupHelper groupHelper;
    private String browser;
    private DbHelper dbHelper;

    WebDriver wd;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {

        String target = System.getProperty("target","local");
        properties.load(new FileReader(new File(String.format("src/test/java/ru/stqa/pft/addressbook/tests/resources/%s.properties", target))));
        System.setProperty("webdriver.chrome.driver", "C://1806/2/chromedriver.exe");
        dbHelper = new DbHelper();
        //Проверка браузера
        if ("".equals(properties.getProperty("selenium.server"))) {
            if (browser.equals(BrowserType.FIREFOX)) {
                System.setProperty("webdriver.gecko.driver", "C://1806/2/geckodriver.exe");
                wd = new FirefoxDriver();
            } else if (browser.equals(BrowserType.CHROME)) {
                System.setProperty("webdriver.chrome.driver", "C://1806/2/chromedriver.exe");
                wd = new ChromeDriver();
            } else if (browser.equals(BrowserType.IE)) {
                System.setProperty("webdriver.ie.driver", "C://1806/2/IEDriverServer.exe");
                wd = new InternetExplorerDriver();
                ;
            }
        }  else{
            DesiredCapabilities capabilites = new DesiredCapabilities();
            capabilites.setBrowserName(browser);
            //capabilites.setPlatform(Platform.fromString(System.getProperty("platform","win7")));
            capabilites.setPlatform(Platform.WINDOWS);//Platform.WINDOWS
            wd = new RemoteWebDriver(new URL(properties.getProperty("selenium.server")), capabilites);

        }

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        //wd.get("http://localhost/addressbook/");
        wd.get(properties.getProperty("web.baseUrl"));
        contactHelper = new ContactHelper(wd);
        navigateHelper = new NavigateHelper(wd);
        sessionHelper = new SessionHelper(wd);
        groupHelper = new GroupHelper(wd);
        sessionHelper.login(properties.getProperty("web.adminLogin"), properties.getProperty("web.adminPassword"));



    }


    public void stop() {
        wd.quit();
    }

    public GroupHelper group(){
        return groupHelper;
    }

    public ContactHelper contact(){
        return contactHelper;
    }

    public NavigateHelper goTo(){
        return navigateHelper;
    }

    public DbHelper db(){
        return dbHelper;
    }

    public byte[] takeScreenshot(){
        return ((TakesScreenshot) wd).getScreenshotAs(OutputType.BYTES);
    }


}
