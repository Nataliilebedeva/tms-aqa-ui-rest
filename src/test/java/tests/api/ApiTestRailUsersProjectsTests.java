package tests.api;

import adapters.ProjectsAdapter;
import baseEntities.BaseApiTest;
import com.google.gson.GsonBuilder;
import endpoints.ProjectEndpoints;
import endpoints.UsersEndpoints;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;
import models.Project;
import models.ProjectTypes;
import models.User;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class ApiTestRailUsersProjectsTests extends BaseApiTest {

    @Test
    public void getAllUsers() {
        String endpoint = "index.php?/api/v2/get_users";
        given()
                .when()
                .get(endpoint)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getUserDetailTest() {
        int userID = 1;
        String endpoint = "index.php?/api/v2/get_user/%s";
        given()
                .when()
                .get(String.format(endpoint, userID))
                .then()
                .log().body()
                .body("name", is("Alex Tros"))
                .body("email", equalTo("atrostyanko+0606@gmail.com"))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getAllUsersDetailTest() {
        User user = User.builder()
                .name("Alex Tros")
                .email("atrostyanko+0606@gmail.com")
                .build();
        given()
                .when()
                .get(UsersEndpoints.GET_USERS)
                .then()
                .log().body()
                .body("get(0).name", is(user.getName()))
                .body("get(0).email", equalTo(user.getEmail()))
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void addProjectsTest() {
        Project project = Project.builder()
                .name("NatLeb")
                .suite_mode(ProjectTypes.SINGLE_SUITE_BASELINES)
                .show_announcement(true)
                .build();

        String body = String.format("{\n" +
                "  \"name\": \"%s\",\n" +
                "  \"suite_mode\": \"%d\"\n" +
                "  \"show_announcement\": %b\n" +
                "}", project.getName(), project.getSuite_mode(), project.isShow_announcement());
        System.out.println(body);
        given()
                .body(body)
                .when()
                .post(ProjectEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void addProjectsTest2() {
        Project project = Project.builder()
                .name("SomeProject")
                .suite_mode(ProjectTypes.SINGLE_SUITE_BASELINES)
                .show_announcement(true)
                .build();

        Map<String, Object> jsonAsMap = new HashMap<>();
        jsonAsMap.put("name", project.getName());
        jsonAsMap.put("suite_mode", project.getSuite_mode());
        jsonAsMap.put("show_announcement", project.isShow_announcement());

        given()
                .body(jsonAsMap)
                .when()
                .post(ProjectEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void addProjectsTest3() {
        Project project = Project.builder()
                .name("SomeProject_2")
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .show_announcement(true)
                .build();

        given()
                .body(project)
                .when()
                .post(ProjectEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void addProjectsTest4() {
        Project project = Project.builder()
                .name("SomeProject_3")
                .suite_mode(ProjectTypes.MULTIPLE_SUITE_MODE)
                .show_announcement(true)
                .build();

        given()
                .body(project, ObjectMapperType.GSON)
                .when()
                .post(ProjectEndpoints.ADD_PROJECT)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void addProjectsByProjectAdapterTest() {
        Project project = Project.builder()
                .name("AddProjectAdapter")
                .suite_mode(ProjectTypes.MULTIPLE_SUITE_MODE)
                .announcement("With Project Adapter")
                .show_announcement(true)
                .build();
        Project newProject = new ProjectsAdapter().postAdd(project);
        Assert.assertTrue(project.equals(newProject));
    }

    @Test
    public void getAllProjects() {
        given()
                .when()
                .get(ProjectEndpoints.GET_PROJECTS)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getAllProjects2() {
        Response response = given()
                .when()
                .get(ProjectEndpoints.GET_PROJECTS)
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK)
                .extract().response();
        System.out.println(response);
    }

    @Test
    public void getAllProjectsAdapterTest() {
        List<Project> projectList = new ProjectsAdapter().get();
        System.out.println(projectList.size());
        System.out.println(projectList.get(0));
        System.out.println(new GsonBuilder().create().toJson(projectList.get(0)));
    }

    @Test
    public void getProjectTest() {
        int projectID = 22;
        given()
                .when()
                .get(String.format(ProjectEndpoints.GET_PROJECT, projectID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getProjectTest2() {
        int projectID = 22;
        Project actualProject = new ProjectsAdapter().get(projectID);
        System.out.println(actualProject);
    }

    @Test
    public void staticJsonValidationTest() throws FileNotFoundException {

        Project expectedProject = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                .fromJson(new FileReader("src/test/resources/expectedProject.json"), Project.class);

        Project actualProject = new ProjectsAdapter().get(22);
        Assert.assertTrue(expectedProject.equals(actualProject));
    }

    @Test
    public void updateProjectAdapterTest(){
        Project expectedProject = Project.builder()
                .name("SomeProjectUpdate")
                .announcement(":)))))")
                .is_completed(false)
                .build();
        Project actualProject = new ProjectsAdapter().postUpdate(expectedProject,85);
        Assert.assertEquals(actualProject.getName(),expectedProject.getName());
        Assert.assertEquals(actualProject.getAnnouncement(), expectedProject.getAnnouncement());
        Assert.assertEquals(actualProject.is_completed(), expectedProject.is_completed());
    }

    @Test
    public void deleteProjectAdapter(){
        new ProjectsAdapter().delete(85);
    }
}
