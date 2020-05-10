package ru.stqa.pft.mantis.tests;


import ru.stqa.pft.mantis.appmanager.ApplicationManager;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;


import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.*;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.remote.BrowserType;
import org.openqa.selenium.ie.*;

import org.testng.SkipException;
import ru.stqa.pft.mantis.model.Issue;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.RemoteException;
import javax.xml.rpc.ServiceException;

public class TestBase {



    public static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp() throws Exception {
        app.init();
        app.ftp().upload(new File("src/test/java/resources/config_inc.php"), "config/config_inc.php","config/config_inc.php.bak");
        app.mail().start();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.ftp().restore("config/config_inc.php.bak", "config/config_inc.php");
        app.mail().stop();
        app.stop();
    }

    boolean isIssueOpen(int issueId) throws MalformedURLException, ServiceException, RemoteException{
        Issue issue = app.soap().getIssueById(issueId);
        if ((issue.getStatus().equals("resolved")) || (issue.getStatus().equals("closed")) ||
                (issue.getResolution().equals("fixed"))) {
            return true;
        } else {
            return false;
        }
    }


    public void skipIfNotFixed(int issueId) throws MalformedURLException, ServiceException, RemoteException{
        if (isIssueOpen(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }

    public boolean isIssueOpenRest(int issueId) throws IOException, ServiceException {
        String issueStatus = app.rest().getIssueForBugifyById(issueId);
        if ((issueStatus.equals("Resolved")) || (issueStatus.equals("Closed")) ||
                (issueStatus.equals("Fixed"))) {
            return false;
        }
        return true;
    }

    public void skipIfNotFixedRest(int issueId) throws IOException, ServiceException {
        if (isIssueOpenRest(issueId)) {
            throw new SkipException("Ignored because of issue " + issueId);
        }
    }



}
