package baseEntities;

import core.ReadProperties;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.apache.http.protocol.HTTP;
import org.testng.annotations.BeforeTest;

import static io.restassured.RestAssured.given;

public class BaseApiTest {
    protected ReadProperties properties;

    @BeforeTest
    public void setup() {
        properties = new ReadProperties();
        RestAssured.baseURI = properties.getTestRailURL();

        RestAssured.requestSpecification = given()
        .header(HTTP.CONTENT_TYPE, ContentType.JSON)
        .auth().preemptive().basic(
                properties.getApiUsername(),
                properties.getApiPassword());
    }

}
