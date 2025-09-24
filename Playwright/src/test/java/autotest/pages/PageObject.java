package autotest.pages;

import com.microsoft.playwright.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PageObject {
    private final Page page;

    @Autowired
    public PageObject(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return page;
    }

    public String getTitle() {
        return page.title();
    }

    public String getHeaderText() {
        return page.locator("h1").textContent();
    }
}