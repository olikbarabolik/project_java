package ru.stqa.pft.mantis.appmanager;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.ie.*;


public class ApplicationManager {
    private final Properties properties;

    private String browser;


    WebDriver wd;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();

    }

    public void init() throws IOException {

        String target = System.getProperty("target","local");
        //properties.load(new FileReader(new File(String.format("src/test/java/ru/stqa/pft/mantis/tests/resources/%s.properties", target))));
        properties.load(new FileReader(new File(String.format("src/test/java/resources/%s.properties", target))));

        //Проверка браузера
        if (browser.equals(BrowserType.FIREFOX)){
            System.setProperty("webdriver.gecko.driver", "C://1806/2/geckodriver.exe");
            wd = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)){
            System.setProperty("webdriver.chrome.driver", "C://1806/2/3/chromedriver.exe");
            wd = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)){
            System.setProperty("webdriver.ie.driver", "C://1806/2/3/IEDriverServer.exe");
            wd = new InternetExplorerDriver();;
        }

        wd.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        wd.get(properties.getProperty("web.baseUrl"));





    }


    public void stop() {
        wd.quit();
    }

    /*public RegistrationHelper registration(){
        return new RegistrationHelper(this);
    }*/

    public WebDriver getDriver(){
        return wd;
    }





}

