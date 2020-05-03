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
    private MailHelper mailHelper;

    private RegistrationHelper registrationHelper;
    //private FtpHelper ftp;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target","local");
        properties.load(new FileReader(new File(String.format("src/test/java/resources/%s.properties", target))));
        properties.load(new FileReader(new File("src/test/java/resources/config_inc.php")));

        //Проверка браузера
        /*if (browser.equals(BrowserType.FIREFOX)){
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
        wd.get(properties.getProperty("web.baseUrl"));*/
    }

    public void stop() {
        if (wd != null){
            wd.quit();
        }
    }


    public WebDriver getDriver(){
        if (wd==null){
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
        return wd;
    }

    public HttpSession newSession(){
        return new HttpSession(this);
    }

    public String getProperty(String key){
        return properties.getProperty(key);
    }

    public RegistrationHelper registration(){
        if (registrationHelper==null){
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public MailHelper mail(){
        if (mailHelper == null){
            mailHelper = new MailHelper(this);
        }
        return mailHelper;
    }
}






