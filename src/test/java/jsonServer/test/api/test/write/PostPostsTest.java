package jsonServer.test.api.test.write;

import com.thedeanda.lorem.LoremIpsum;
import io.restassured.response.ValidatableResponse;
import jsonServer.test.api.test.BaseBatch4ApiTest;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

public class PostPostsTest extends BaseBatch4ApiTest {

    @Test
    public void createPostShouldSucceed() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", LoremIpsum.getInstance().getTitle(2));
        jsonObject.put("author", LoremIpsum.getInstance().getName());

        given()
                .header("Content-Type", "application/json ")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body();

    }

    @Test
    public void createPostShouldSucceed2() {
        String title = LoremIpsum.getInstance().getTitle(2);
        String author = LoremIpsum.getInstance().getName();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("author", author);

        given()
                .header("Content-Type", "application/json ")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("author", equalTo(author))
                .body("title", equalTo(title));

    }

    @Test
    public void updatePutPostShouldSucceed() {
        String title = LoremIpsum.getInstance().getTitle(2);
        String author = LoremIpsum.getInstance().getName();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("author", author);

        given()
                .header("Content-Type", "application/json ")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .put("/posts/3")
                .then()
                .statusCode(200)
                .log().body()
                .body("author", equalTo(author))
                .body("title", equalTo(title));
    }


    @Test
    public void updatePatchPostShouldSucceed() {
        String author = LoremIpsum.getInstance().getName();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("author", author);

        given()
                .header("Content-Type", "application/json ")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .patch("/posts/3")
                .then()
                .statusCode(200)
                .log().body()
                .body("author", equalTo(author));
    }

    @Test
    public void updatePostShouldSucceed() {
        String title = LoremIpsum.getInstance().getTitle(2);
        String author = LoremIpsum.getInstance().getName();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("author", author);

        ValidatableResponse validatableResponse = given()
                .header("Content-Type", "application/json ")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("author", equalTo(author))
                .body("title", equalTo(title));
        int postId = validatableResponse.extract().jsonPath().getInt("id");


        title = LoremIpsum.getInstance().getTitle(2);
        author = LoremIpsum.getInstance().getName();

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("title", title);
        jsonObject2.put("author", author);

        given()
                .header("Content-Type", "application/json ")
                .body(jsonObject2)
                .log().uri()
                .log().body()
                .when()
                .put("/posts/" + postId)
                .then()
                .statusCode(200)
                .log().body()
                .body("author", equalTo(author))
                .body("title", equalTo(title));


    }

    @Test
    public void patchPostShouldSucceed() {
        String title = LoremIpsum.getInstance().getTitle(2);
        String author = LoremIpsum.getInstance().getName();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("author", author);

        ValidatableResponse validatableResponse = given()
                .header("Content-Type", "application/json ")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("author", equalTo(author))
                .body("title", equalTo(title));
        int postId = validatableResponse.extract().jsonPath().getInt("id");


        title = LoremIpsum.getInstance().getTitle(2);

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("title", title);

        given()
                .header("Content-Type", "application/json ")
                .body(jsonObject2)
                .log().uri()
                .log().body()
                .when()
                .patch("/posts/" + postId)
                .then()
                .statusCode(200)
                .log().body()
                .body("title", equalTo(title));


    }

    @Test
    public void deletePostShouldSucceed() {
        String title = LoremIpsum.getInstance().getTitle(2);
        String author = LoremIpsum.getInstance().getName();

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("title", title);
        jsonObject.put("author", author);

        ValidatableResponse validatableResponse = given()
                .header("Content-Type", "application/json ")
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .log().body()
                .body("author", equalTo(author))
                .body("title", equalTo(title));
        int postId = validatableResponse.extract().jsonPath().getInt("id");

        given()
                .header("Content-Type", "application/json ")
                .log().uri()
                .log().body()
                .when()
                .delete("/posts/" + postId)
                .then()
                .statusCode(200);

    }
}
