package adapters;

import com.google.gson.reflect.TypeToken;
import endpoints.ProjectEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Project;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class ProjectsAdapter extends BaseAdapter {

    public List<Project> get() {
        Response response = given()
                .when()
                .get(ProjectEndpoints.GET_PROJECTS)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), new TypeToken<List<Project>>() {
        }.getType());
    }


    public Project get(int projectID) {
        Response response = given()
                .when()
                .get(String.format(ProjectEndpoints.GET_PROJECT, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Project.class);
    }

    public Project postAdd(Project project) {
       Response response =  given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(ProjectEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

       return gson.fromJson(response.asString().trim(),Project.class);
    }

    public Project postUpdate(Project project, int projectID) {
        Response response =  given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(String.format(ProjectEndpoints.UPDATE_PROJECT,projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(),Project.class);
    }

    public void delete(int projectID){
        given()
                .post(String.format(ProjectEndpoints.DELETE_PROJECT, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }
}
