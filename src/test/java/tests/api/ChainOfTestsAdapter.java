package tests.api;

import adapters.ProjectsAdapter;
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

public class ChainOfTestsAdapter extends BaseApiTest {
    private int projectID;

    @Test
    public void addProjectsTest() {
        Project project = Project.builder()
                .name("SomeProject_ForUpdate")
                .suite_mode(ProjectTypes.MULTIPLE_SUITE_MODE)
                .show_announcement(true)
                .flag(true)
                .build();
        System.out.println(project);

     Project newProject = new ProjectsAdapter().postAdd(project);
     System.out.println(newProject);
     projectID = newProject.getId();
    }

    @Test(dependsOnMethods = "addProjectsTest")
    public void updateProjectsTest() {
        Project project = Project.builder()
                .name("SomeProjectUpdate")
                .announcement(":)))))")
                .is_completed(false)
                .build();
        System.out.println(project);

        Project newProject = new ProjectsAdapter().postUpdate(project,projectID);
        System.out.println(newProject);

        Assert.assertEquals(project.getName(), newProject.getName());
        Assert.assertEquals(project.getAnnouncement(), newProject.getAnnouncement());
        Assert.assertEquals(project.is_completed(), newProject.is_completed());
    }

    @Test(dependsOnMethods = "updateProjectsTest")
    public void deleteTest() {
        new ProjectsAdapter().delete(projectID);
    }
}
