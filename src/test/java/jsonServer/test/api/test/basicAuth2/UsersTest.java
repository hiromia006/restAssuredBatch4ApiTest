package jsonServer.test.api.test.basicAuth2;

import io.restassured.http.ContentType;
import jsonServer.test.api.test.BaseBatch4ApiTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class UsersTest extends BaseBatch4ApiTest {

    @Test
    public void getUsersShouldSucceed() {
        given()
                .auth().basic("admin", "12345")
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/users")
                .then()
                .log().body()
                .statusCode(200);

    }
}
