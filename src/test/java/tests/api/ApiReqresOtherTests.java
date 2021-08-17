package tests.api;

import baseEntities.BaseReqresTest;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiReqresOtherTests extends BaseReqresTest {

    @Test
    public void listUsersTest() {
        String endpoint = "/api/users?page=2";
        //Given
        RequestSpecification httpRequest = given();
        //When
        Response response = httpRequest.request(Method.GET, endpoint);
        //Then
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void getSingleUserTest() {
        String endpoint = "/api/users/2";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET, endpoint);
        String responseBode = response.getBody().asString();
        System.out.println(responseBode);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void getSingleUserNotFoundTest() {
        String endpoint = "/api/users/23";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET, endpoint);
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);
    }

    @Test
    public void getListResourceTest() {
        String endpoint = "/api/unknown";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET, endpoint);
        String responseBode = response.getBody().asString();
        System.out.println(responseBode);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void getSingleResourceTest() {
        String endpoint = "/api/unknown/2";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET, endpoint);
        String responseBode = response.getBody().asString();
        System.out.println(responseBode);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void getSingleResourceNotFoundTest() {
        String endpoint = "/api/unknown/23";
        RequestSpecification httpRequest = given();
        Response response = httpRequest.request(Method.GET, endpoint);
        String responseBode = response.getBody().asString();
        System.out.println(responseBode);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 404);
    }

    @Test
    public void postCreateTest() {
        String endpoint = "/api/users";
        RequestSpecification httpRequest = given()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"leader\"\n" +
                        "}");
        Response response = httpRequest.request(Method.POST, endpoint);
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);
    }

    @Test
    public void putUpdateTest() {
        String endpoint = "/api/users/2";
        RequestSpecification httrRequest = given()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}");
        Response response = httrRequest.request(Method.PUT, endpoint);
        String responseBode = response.getBody().asString();
        System.out.println(responseBode);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void patchUpdateTest() {
        String endpoint = "/api/users/2";
        RequestSpecification httrRequest = given()
                .body("{\n" +
                        "    \"name\": \"morpheus\",\n" +
                        "    \"job\": \"zion resident\"\n" +
                        "}");
        Response response = httrRequest.request(Method.PATCH, endpoint);
        String responseBode = response.getBody().asString();
        System.out.println(responseBode);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void deleteTest() {
        String endpoint = "/api/users/2";
        RequestSpecification httrRequest = given();
        Response response = httrRequest.request(Method.DELETE, endpoint);
        String responseBode = response.getBody().asString();
        System.out.println(responseBode);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 204);
    }

    @Test
    public void postRegisterSuccessfulTest() {
        String endpoint = "/api/register";
        RequestSpecification httrRequest = given()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"pistol\"\n" +
                        "}");
        Response response = httrRequest.request(Method.POST, endpoint);
        String responseBode = response.getBody().asString();
        System.out.println(responseBode);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void postRegisterUnSuccessfulTest() {
        String endpoint = "/api/register";
        RequestSpecification httrRequest = given()
                .body("{\n" +
                        "    \"email\": \"sydney@fife\"\n" +
                        "}");
        Response response = httrRequest.request(Method.POST, endpoint);
        String responseBode = response.getBody().asString();
        System.out.println(responseBode);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);
    }

    @Test
    public void postLoginSuccessfulTest() {
        String endpoint = "/api/login";
        RequestSpecification httrRequest = given()
                .body("{\n" +
                        "    \"email\": \"eve.holt@reqres.in\",\n" +
                        "    \"password\": \"cityslicka\"\n" +
                        "}");
        Response response = httrRequest.request(Method.POST, endpoint);
        String responseBode = response.getBody().asString();
        System.out.println(responseBode);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void postLoginUnSuccessfulTest() {
        String endpoint = "/api/login";
        RequestSpecification httrRequest = given()
                .body("{\n" +
                        "    \"email\": \"peter@klaven\"\n" +
                        "}");
        Response response = httrRequest.request(Method.POST, endpoint);
        String responseBode = response.getBody().asString();
        System.out.println(responseBode);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 400);
    }

    @Test
    public void getDelayedResponseTest() {
        String endpoint = "/api/users?delay=3";
        RequestSpecification httrRequest = given();
        Response response = httrRequest.request(Method.GET, endpoint);
        String responseBode = response.getBody().asString();
        System.out.println(responseBode);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
