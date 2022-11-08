package adapters;

import endpoints.MilestonesEndpoints;
import endpoints.ProjectEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Milestones;
import models.Project;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class MilestonesAdapter extends BaseAdapter{

    public Milestones postAdd(Milestones milestones, int projectID) {
        Response response =  given()
                .body(milestones, ObjectMapperType.GSON)
                .when()
                .post(String.format(MilestonesEndpoints.ADD_MILESTONES,projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(),Milestones.class);
    }

    public Milestones postUpdate(Milestones milestones, int milestoneID) {
        Response response =  given()
                .body(milestones, ObjectMapperType.GSON)
                .when()
                .post(String.format(MilestonesEndpoints.UPDATE_MILESTONES,milestoneID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(),Milestones.class);
    }

    public void delete(int milestoneID){
        given()
                .post(String.format(MilestonesEndpoints.DELETE_MILESTONES, milestoneID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }
}
