package jsonServer.test.api.test.bearerToken2;

import io.restassured.http.ContentType;
import jsonServer.test.api.test.BaseBatch4ApiTest;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class BearerTokenTest extends BaseBatch4ApiTest {

    @Test
    public void getArticleShouldSucceed() {
        given()
                .header("Authorization", "Bearer " + getBearerToken())
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/articles")
                .then()
                .log().body()
                .statusCode(200);

    }


    private String getBearerToken() {
        JSONObject login = new JSONObject();
        login.put("email", "superman@gmail.com");
        login.put("password", "123456");
        return given()
                .contentType(ContentType.JSON)
                .body(login)
                .when()
                .post("/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("token");
    }
}
