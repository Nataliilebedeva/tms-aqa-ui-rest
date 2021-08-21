package tests.api;

import adapters.MilestonesAdapter;
import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import endpoints.MilestonesEndpoints;
import io.restassured.response.Response;
import models.Milestones;
import models.Project;
import models.ProjectTypes;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class ApiTestRailMilestonesTests extends BaseApiTest {
    int projectID;
    int milestoneID;

    @Test
    public void addProjectsTest() {
        Project project = Project.builder()
                .name("MilestoneProject")
                .suite_mode(ProjectTypes.SINGLE_SUITE_BASELINES)
                .show_announcement(true)
                .flag(true)
                .build();
        System.out.println(project);

        Project newProject = new ProjectsAdapter().postAdd(project);
        System.out.println(newProject);
        projectID = newProject.getId();
    }

    @Test (dependsOnMethods = "addProjectsTest")
    public void addMilestonesTest() {
        Milestones expectedMilestone = Milestones.builder()
                .name("Nat")
                .description("Я попробовала")
                .refs("Пусть будет")
                .start_on(1629331200)
                .due_on(1629740843)
                .build();
        Milestones actualMilestone = new MilestonesAdapter().postAdd(expectedMilestone, projectID);
        System.out.println(actualMilestone);
        milestoneID = actualMilestone.getId();

        Assert.assertEquals(actualMilestone.getName(),expectedMilestone.getName());
        Assert.assertEquals(actualMilestone.getDescription(), expectedMilestone.getDescription());
        Assert.assertEquals(actualMilestone.getRefs(), expectedMilestone.getRefs());
        Assert.assertEquals(actualMilestone.getStart_on(), expectedMilestone.getStart_on());
        Assert.assertEquals(actualMilestone.getDue_on(), expectedMilestone.getDue_on());
    }

    @Test (dependsOnMethods = "addMilestonesTest")
    public void getMilestoneTest() {
        given()
                .when()
                .get(String.format(MilestonesEndpoints.GET_MILESTONE, milestoneID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test (dependsOnMethods = "getMilestoneTest")
    public void getMilestonesTest() {
        Response response = given()
                .when()
                .get(String.format(MilestonesEndpoints.GET_MILESTONES, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        System.out.println(response);
    }

    @Test(dependsOnMethods = "getMilestonesTest")
    public void updateMilestoneTest(){
        Milestones expectedMilestone = Milestones.builder()
                .name("NatUpdate")
                .description("Изменила")
                .refs("Изменила")
                .start_on(1629331200)
                .due_on(1629740843)
                .is_completed(true)
                .build();

        Milestones actualMilestone = new MilestonesAdapter().postUpdate(expectedMilestone,milestoneID);
        System.out.println(actualMilestone);
        Assert.assertEquals(actualMilestone.getName(),expectedMilestone.getName());
        Assert.assertEquals(actualMilestone.getDescription(), expectedMilestone.getDescription());
        Assert.assertEquals(actualMilestone.getRefs(), expectedMilestone.getRefs());
        Assert.assertEquals(actualMilestone.getStart_on(), expectedMilestone.getStart_on());
    }

    @Test (dependsOnMethods = "updateMilestoneTest")
    public void deleteMilestoneTest(){
        new MilestonesAdapter().delete(milestoneID);
    }
}
