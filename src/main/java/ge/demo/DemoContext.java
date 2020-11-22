package ge.demo;

import ge.demo.pages.HerokuappPage;
import ge.demo.pages.Page;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class DemoContext {
    private WebDriver driver;
    private String webSiteUrl;

    public DemoContext(WebDriver driver, String webSiteUrl) {
        this.driver = driver;
        this.webSiteUrl = webSiteUrl;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public String getWebSiteUrl() {
        return webSiteUrl;
    }

    public HerokuappPage startWebSite(){
        driver.get(webSiteUrl);
        return Page.dispatch(HerokuappPage.class,this);
    }

    public static DemoContext getDefault(){
        return DemoContext.getBuilder().build();
    }

    public static DemoContextBuilder getBuilder(){
        return new DemoContextBuilder();
    }


    public static class DemoContextBuilder{
        private WebDriver driver;
        private String webSiteUrl = "http://the-internet.herokuapp.com";

        public DemoContextBuilder setDriver(WebDriver driver) {
            this.driver = driver;
            return this;
        }

        public DemoContextBuilder setWebSiteUrl(String webSiteUrl) {
            this.webSiteUrl = webSiteUrl;
            return this;
        }

        public DemoContext build(){
            if(driver == null){
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }
           return new DemoContext(driver, webSiteUrl);
        }
    }

}
