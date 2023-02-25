package jsonServer.test.api.test.read;

import io.restassured.http.ContentType;
import jsonServer.test.api.test.BaseBatch4ApiTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class GetPostsTest extends BaseBatch4ApiTest {

    @Test
    public void getPostsShouldSucceed() {
        given()
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/posts")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void getPostDetailShouldSucceed() {
        given()
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .get("/posts/1")
                .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo(1));
    }
}
