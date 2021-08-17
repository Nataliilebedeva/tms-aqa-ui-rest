package tests.api;

import baseEntities.BaseReqresTest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class ApiReqresTests extends BaseReqresTest {

    @Test
    public void listUsersTest() {
        String endpoint = "/api/users?page=2";
        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getSingleUserTest() {
        String endpoint = "/api/users/2";
        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getSingleUserNotFoundTest() {
        String endpoint = "/api/users/23";
        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void getListResourceTest() {
        String endpoint = "/api/unknown";
        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getSingleResourceTest() {
        String endpoint = "/api/unknown/2";
        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getSingleResourceNotFoundTest() {
        String endpoint = "/api/unknown/23";
        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_NOT_FOUND);
    }

    @Test
    public void postCreateTest() {
        String endpoint = "/api/users";
        given()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}")
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .body("name", is("morpheus"))
                .body("job", is("leader"))
                .statusCode(HttpStatus.SC_CREATED);
    }

    @Test
    public void putUpdateTest() {
        String endpoint = "/api/users/2";
        given()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .when()
                .put(endpoint)
                .then()
                .log().body()
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void patchUpdateTest() {
        String endpoint = "/api/users/2";
        given()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}")
                .when()
                .patch(endpoint)
                .then()
                .log().body()
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void deleteTest() {
        String endpoint = "/api/users/2";
        given()
                .when()
                .delete(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

    @Test
    public void postRegisterSuccessfulTest() {
        String endpoint = "/api/register";
        given()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}")
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .body("id", is(4))
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void postRegisterUnSuccessfulTest() {
        String endpoint = "/api/register";
        given()
                .body("{\n" +
                        "    \"email\": \"sydney@fife\"\n" +
                        "}")
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .body("error", is("Missing password"))
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void postLoginSuccessfulTest() {
        String endpoint = "/api/login";
        given()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}")
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .body("token", is("QpwL5tke4Pnpja7X4"))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void postLoginUnSuccessfulTest() {
        String endpoint = "/api/login";
        given()
                .body("{\n" +
                        "    \"email\": \"peter@klaven\"\n" +
                        "}")
                .when()
                .post(endpoint)
                .then()
                .log().body()
                .body("error", is("Missing password"))
                .statusCode(HttpStatus.SC_BAD_REQUEST);
    }

    @Test
    public void getDelayedResponseTest() {
        String endpoint = "/api/users?delay=3";
        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }
}
