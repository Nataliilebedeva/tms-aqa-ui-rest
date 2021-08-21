package adapters;

import com.google.gson.reflect.TypeToken;
import endpoints.CaseEndpoints;
import endpoints.MilestonesEndpoints;
import endpoints.ProjectEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Case;
import models.Milestones;
import models.Project;
import org.apache.http.HttpStatus;

import java.util.List;

import static io.restassured.RestAssured.given;

public class CaseAdapter extends BaseAdapter{

    public Case postAdd(Case addCase, int sectionID) {
        Response response =  given()
                .body(addCase, ObjectMapperType.GSON)
                .when()
                .post(String.format(CaseEndpoints.ADD_CASE,sectionID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(),Case.class);
    }

    public Case get(int caseID) {
        Response response = given()
                .when()
                .get(String.format(CaseEndpoints.GET_CASE, caseID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), Case.class);
    }

    public List<Case> getCases(int projectID) {
        Response response = given()
                .when()
                .get(String.format(CaseEndpoints.GET_CASES,projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return gson.fromJson(response.asString().trim(), new TypeToken<List<Case>>() {
        }.getType());
    }

    public Response getHistory(int caseID) {
        Response response = given()
                .when()
                .get(String.format(CaseEndpoints.GET_HISTORY, caseID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        return response;
    }

    public Case postUpdate(Case newCase, int caseID) {
        Response response =  given()
                .body(newCase, ObjectMapperType.GSON)
                .when()
                .post(String.format(CaseEndpoints.UPDATE_CASE,caseID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();

        return gson.fromJson(response.asString().trim(),Case.class);
    }

    public void delete(int caseID){
        given()
                .post(String.format(CaseEndpoints.DELETE_CASE, caseID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }



}
