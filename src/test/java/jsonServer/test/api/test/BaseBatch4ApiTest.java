package jsonServer.test.api.test;

import io.restassured.RestAssured;
import org.testng.annotations.BeforeClass;

public class BaseBatch4ApiTest {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "http://restapi.adequateshop.com";
        RestAssured.basePath = "/api";
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}
