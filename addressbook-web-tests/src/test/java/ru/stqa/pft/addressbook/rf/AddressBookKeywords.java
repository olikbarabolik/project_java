package ru.stqa.pft.addressbook.rf;

import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.GroupData;
import org.openqa.selenium.remote.BrowserType;
import java.io.IOException;

public class AddressBookKeywords {

    private ApplicationManager app;
    public static final String ROBOT_LIBRARY_SCOPE = "GLOBAL";

    public void initApplicationManager() throws IOException {
        app = new ApplicationManager(System.getProperty("browser",BrowserType.CHROME));
        app.init();
    }

    public void stopApplicationManager() throws IOException {
        app.stop();
        app = null;
    }

    public int getGroupCount(){
        app.goTo().groupPage();
        return app.group().count();
    }

    public void createGroup(String name, String header, String footer){
        app.goTo().groupPage();
        app.group().create(new GroupData().withName(name).withHeader(header).withFooter(footer));
    }
}
