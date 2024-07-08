package api.test;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

import java.lang.reflect.Method;

public abstract class BaseTest {

    public static final String VND_API_JSON = "application/vnd.api+json";

    public static void log(String str, Object... arr) {
        System.out.printf(str, arr);
        System.out.println();
    }

    @BeforeTest
    protected void setFilter(){
        RestAssured.filters(new AllureRestAssured());
    }

    @BeforeMethod
    protected void beforeMethod(Method method) {
        log("Run %s.%s", this.getClass().getName(), method.getName());
    }

    @AfterMethod
    protected void afterMethod(Method method, ITestResult testResult) {
        log("Execution time is %o ms\n\n", (testResult.getEndMillis() - testResult.getStartMillis()));
    }
}
