package ru.stqa.pft.addressbook.tests;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import org.openqa.selenium.remote.BrowserType;

import java.lang.reflect.Method;
import java.util.Arrays;

@Listeners(MyTestListener.class)
public class TestBase {

    Logger logger = LoggerFactory.getLogger(TestBase.class);

    public static final ApplicationManager app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));

    @BeforeSuite(alwaysRun = true)
    public void setUp(ITestContext context) throws Exception {
        app.init();
        context.setAttribute("app", app);
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws Exception {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p){
        logger.info("Start test" +m.getName()+ "with Parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun=true)
    public void logTestStop(Method m){
        logger.info("Stop test" +m.getName());
    }

}
