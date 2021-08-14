package tests.api;

import core.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.http.protocol.HTTP;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ApiSimpleTestRailTests {
    ReadProperties properties = new ReadProperties();

    @Test
    public void getAllUsers() {
        RestAssured.baseURI = properties.getTestRailURL();
        String endpoint = "index.php?/api/v2/get_users";
        RequestSpecification httpRequest = given();
        httpRequest.header(HTTP.CONTENT_TYPE, ContentType.JSON);
        httpRequest.auth().preemptive().basic(
                properties.getApiUsername(),
                properties.getApiPassword());
        Response response = httpRequest.request(Method.GET, endpoint);
        String responseBody = response.getBody().asString();
        System.out.println(responseBody);
        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }
}
