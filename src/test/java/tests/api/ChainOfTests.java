package tests.api;

import baseEntities.BaseApiTest;
import com.google.gson.GsonBuilder;
import endpoints.ProjectEndpoints;
import io.restassured.mapper.ObjectMapperType;
import models.Project;
import models.ProjectTypes;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class ChainOfTests extends BaseApiTest {
    private int projectID;

    @Test
    public void addProjectsTest() {
        Project project = Project.builder()
                .name("SomeProject_ForUpdate")
                .suite_mode(ProjectTypes.MULTIPLE_SUITE_MODE)
                .show_announcement(true)
                .flag(true)
                .build();

        System.out.println(new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create().toJson(project));

        projectID = given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(ProjectEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().jsonPath().get("id");
    }

    @Test(dependsOnMethods = "addProjectsTest")
    public void updateProjectsTest() {
        Project project = Project.builder()
                .name("SomeProjectUpdate")
                .announcement(":)))))")
                .is_completed(false)
                .build();
        System.out.println(project);

        String json = given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(String.format(ProjectEndpoints.UPDATE_PROJECT, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response().asString();
        System.out.println(json);

        Project newProject = new GsonBuilder().create().fromJson(json, Project.class);
        System.out.println(newProject);

        Assert.assertEquals(project.getName(), newProject.getName());
        Assert.assertEquals(project.getAnnouncement(), newProject.getAnnouncement());
        Assert.assertEquals(project.is_completed(), newProject.is_completed());
    }

    @Test(dependsOnMethods = "updateProjectsTest")
    public void deleteTest() {
        given()
                .post(String.format(ProjectEndpoints.DELETE_PROJECT, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }
}
