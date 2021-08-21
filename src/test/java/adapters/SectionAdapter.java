package adapters;

import endpoints.ProjectEndpoints;
import endpoints.SectionEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Project;
import models.Section;
import org.apache.http.HttpStatus;
import sun.swing.SwingUtilities2;

import static io.restassured.RestAssured.given;

public class SectionAdapter extends BaseAdapter{

    public Section postAdd(Section section, int projectID) {
        Response response =  given()
                .body(section, ObjectMapperType.GSON)
                .when()
                .post(String.format(SectionEndpoints.ADD_SECTION,projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(),Section.class);
    }
}
