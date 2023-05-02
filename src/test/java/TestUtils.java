import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class TestUtils {
    public static ValidatableResponse callGet(String path) {
        return given()
                .when()
                .get(path)
                .then()
                .log().all();
    }

    public static ValidatableResponse callPut(String path) {
        return given()
                .when()
                .put(path)
                .then()
                .log().all();
    }

    public static ValidatableResponse callPost(String path) {
        return given()
                .when()
                .post(path)
                .then()
                .log().all();
    }

    public static ValidatableResponse callDelete(String path) {
        return given()
                .when()
                .delete(path)
                .then()
                .log().all();
    }

}
