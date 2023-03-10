package jsonServer.test.api.test.auth;

import com.thedeanda.lorem.LoremIpsum;
import io.restassured.http.ContentType;
import jsonServer.test.api.test.BaseBatch4ApiTest;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.contains;
import static org.hamcrest.core.IsEqual.equalTo;

public class BearerTokenTest extends BaseBatch4ApiTest {

    @Test
    public void bearerTokenTest() {
        JSONObject registrationJson = new JSONObject();
        registrationJson.put("name", "Developer");
        registrationJson.put("email", "sqalearningacademy@gmail.com");
        registrationJson.put("password", "123456");

        given()
                .body(registrationJson.toString())
                .header("Content-Type", "application/json ")
                .log().uri()
                .log().body()
                .when()
                .post("/authaccount/registration")
                .then()
                .log().body()
                .statusCode(200);
    }

    @Test
    public void loginTest() {
        JSONObject registrationJson = new JSONObject();
        registrationJson.put("email", "sqalearningacademy@gmail.com");
        registrationJson.put("password", "123456");

        given()
                .body(registrationJson.toString())
                .header("Content-Type", "application/json ")
                .log().uri()
                .log().body()
                .when()
                .post("/authaccount/login")
                .then()
                .log().body()
                .statusCode(200);
    }


    @Test
    public void loginShouldFail() {
        JSONObject registrationJson = new JSONObject();
        registrationJson.put("email", "sqalearningacademy131312@gmail.com");
        registrationJson.put("password", "123456");

        given()
                .body(registrationJson.toString())
                .header("Content-Type", "application/json ")
                .log().uri()
                .log().body()
                .when()
                .post("/authaccount/login")
                .then()
                .log().body()
                .statusCode(200)
                .body("code", equalTo(1))
                .body("message", equalTo("invalid username or password"));
    }

    @Test
    public void getAllUsers() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getBearerToken())
                .log().uri()
                .when()
                .get("/users?page=1")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void getUserDetailShouldSucceed() {
        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getBearerToken())
                .log().uri()
                .when()
                .get("/users/11133")
                .then()
                .statusCode(200)
                .log().body();
    }

    @Test
    public void getUserDetailShouldSucceedDynamic() {
        int userId = given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getBearerToken())
                .log().uri()
                .when()
                .get("/users?page=1")
                .then()
                .statusCode(200)
                .extract().jsonPath().getInt("data[1].id");

        given()
                .contentType(ContentType.JSON)
                .header("Authorization", "Bearer " + getBearerToken())
                .log().uri()
                .when()
                .get("/users/" + userId)
                .then()
                .statusCode(200)
                .log().body()
                .body("id", equalTo(userId));
    }

    @Test
    public void createUserShouldSucceed() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", LoremIpsum.getInstance().getName());
        jsonObject.put("email", LoremIpsum.getInstance().getEmail());
        jsonObject.put("location", LoremIpsum.getInstance().getCountry());

        given()
                .header("Authorization", "Bearer " + getBearerToken())
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().body();
    }

    @Test
    public void updateUserShouldSucceed() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", LoremIpsum.getInstance().getName());
        jsonObject.put("email", LoremIpsum.getInstance().getEmail());
        jsonObject.put("location", LoremIpsum.getInstance().getCountry());
        jsonObject.put("id", 214703);

        given()
                .header("Authorization", "Bearer " + getBearerToken())
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .put("/users/214703")
                .then()
                .statusCode(201)
                .log().body();
    }

    @Test
    public void updateUserShouldSucceedDynamic() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", LoremIpsum.getInstance().getName());
        jsonObject.put("email", LoremIpsum.getInstance().getEmail());
        jsonObject.put("location", LoremIpsum.getInstance().getCountry());

        int userId = given()
                .header("Authorization", "Bearer " + getBearerToken())
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().body()
                .extract().jsonPath().getInt("id");

        JSONObject jsonObject2 = new JSONObject();
        jsonObject2.put("name", LoremIpsum.getInstance().getName());
        jsonObject2.put("email", LoremIpsum.getInstance().getEmail());
        jsonObject2.put("location", LoremIpsum.getInstance().getCountry());
        jsonObject2.put("id", userId);

        given()
                .header("Authorization", "Bearer " + getBearerToken())
                .contentType(ContentType.JSON)
                .body(jsonObject2)
                .log().uri()
                .log().body()
                .when()
                .patch("/users/" + userId)
                .then()
                .statusCode(201)
                .log().body();
    }

    @Test
    public void deleteUserShouldSucceedDynamic() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", LoremIpsum.getInstance().getName());
        jsonObject.put("email", LoremIpsum.getInstance().getEmail());
        jsonObject.put("location", LoremIpsum.getInstance().getCountry());

        int userId = given()
                .header("Authorization", "Bearer " + getBearerToken())
                .contentType(ContentType.JSON)
                .body(jsonObject)
                .log().uri()
                .log().body()
                .when()
                .post("/users")
                .then()
                .statusCode(201)
                .log().body()
                .extract().jsonPath().getInt("id");


        given()
                .header("Authorization", "Bearer " + getBearerToken())
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .delete("/users/" + userId)
                .then()
                .statusCode(201)
                .log().body();
    }

    public String getBearerToken() {
        JSONObject registrationJson = new JSONObject();
        registrationJson.put("email", "sqalearningacademy@gmail.com");
        registrationJson.put("password", "123456");

        return given()
                .body(registrationJson.toString())
                .contentType(ContentType.JSON)
                .log().uri()
                .when()
                .post("/authaccount/login")
                .then()
                .statusCode(200)
                .extract().jsonPath().getString("data.Token");
    }


}
