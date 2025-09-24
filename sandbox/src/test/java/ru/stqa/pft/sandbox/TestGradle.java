package ru.stqa.pft.sandbox;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import io.qameta.allure.Owner;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class TestGradle {
    public static WebDriver driver;

    @Step("Step 1 learning allure")
    public void method(){
       Selenide.open("http://ya.ru");
    }

    @Step("Step 2 learning allure")
    public void method2(){
        System.setProperty("webdriver.chrome.driver", "C:/driver/chromedriver.exe");
        driver = new ChromeDriver();
        driver.get("http://ya.ru");
        driver.quit();
    }



    @BeforeEach
    public void setUp(){
        WebDriverManager.chromedriver().setup();
        Configuration.browser = "chrome";
        Configuration.browserSize = "1920x1080";
        Configuration.timeout = 40;
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @Test
    public void correctTest(){
        Point p1 = new Point(3,5);
        Point p2 = new Point(7,9);
        System.out.println("!!!! Running test 1");
        //Корректный тест
        Assert.assertEquals(p1.distance(p2), 5.656854249492381);
    }

    @Test
    @Owner("Олег ThreadQA")
    @Description("Learn learn !! allure")
    public void test1(){
        method();
    }

    @Test
    public void testForSelenium(){
        method2();
    }

    @Test
    public void testForRestAssured(){
        List<UserData> users = given().contentType(ContentType.JSON)
                //.when()
                .get("https://reqres.in/" +
                        "api/users?page=2")
                .then()
                //.log().body()
                .extract().jsonPath().getList("data", UserData.class)
                .stream().filter(x -> x.getFirst_name().contains("Michael"))
                //.limit(2)
                .collect(Collectors.toList());
        System.out.println(users);
        int i=2;
    }

    public static RequestSpecification query(String url){
        return new RequestSpecBuilder()
                .setBaseUri(url)
                .setContentType(ContentType.JSON)
                .build();

    }

    public static void installSpec(RequestSpecification query){
        RestAssured.requestSpecification = query;
    }

    @Test
    public void testForRestAssured2(){

        List<UserData> users = given().contentType(ContentType.JSON)
                //.when()

                .get("https://reqres.in/" +
                        "api/users?page=2")
                .then()
                //.log().body()
                .extract().jsonPath().getList("data", UserData.class)
                .stream().filter(x -> x.getId().equals(7))
                .collect(Collectors.toList());
        System.out.println(users);
        int i=2;
    }


    @Test
    public void testForRestAssured3(){
        installSpec(query("https://reqres.in/"));
        List<UserData> users = given()
                .get("/api/users?page=2")
                .then()

                .log().body()
                .extract().jsonPath().getList("data", UserData.class)
                .stream().filter(x -> x.getId().equals(7))
                .collect(Collectors.toList());
        System.out.println(users);
        int i=2;
    }

}
