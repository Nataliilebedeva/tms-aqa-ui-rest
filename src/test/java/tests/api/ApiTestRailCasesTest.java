package tests.api;

import adapters.CaseAdapter;
import adapters.ProjectsAdapter;
import adapters.SectionAdapter;
import baseEntities.BaseApiTest;
import endpoints.CaseEndpoints;
import io.restassured.response.Response;
import models.*;
import org.apache.http.HttpStatus;
import org.testng.Assert;
import org.testng.annotations.Test;


import static io.restassured.RestAssured.given;

public class ApiTestRailCasesTest extends BaseApiTest {
    private int projectID;
    private int sectionID;
    private int caseID;

    @Test
    public void addProjectsTest() {
        Project project = Project.builder()
                .name("CasesProject")
                .suite_mode(ProjectTypes.SINGLE_SUITE_MODE)
                .show_announcement(true)
                .flag(true)
                .build();
        System.out.println(project);

        Project newProject = new ProjectsAdapter().postAdd(project);
        System.out.println(newProject);
        projectID = newProject.getId();
    }

    @Test(dependsOnMethods = "addProjectsTest")
    public void addSectionTest() {
        Section section = Section.builder()
                .name("Some case")
                .description("Bla Bla")
                .build();
        System.out.println(section);

        Section actualSection = new SectionAdapter().postAdd(section, projectID);
        sectionID = actualSection.getId();
        System.out.println(actualSection);
        Assert.assertEquals(actualSection.getName(), section.getName());
        Assert.assertEquals(actualSection.getDescription(), section.getDescription());
    }

    @Test(dependsOnMethods = "addSectionTest")
    public void addCaseTest() {
        Case expectedCase = Case.builder()
                .title("SomeCase")
                .type_id(CaseTypes.ACCEPTANCE)
                .priority_id(CasePriorityTypes.MEDIUM)
                .custom_expected("EEEEEEEEE")
                .custom_preconds("PPPPPPPPP")
                .custom_steps("SSSSSSSSSSSSSSS")
                .build();

        Case actualCase = new CaseAdapter().postAdd(expectedCase, sectionID);
        caseID = actualCase.getId();
        System.out.println(actualCase);
        Assert.assertEquals(actualCase.getTitle(), expectedCase.getTitle());
        Assert.assertEquals(actualCase.getType_id(), expectedCase.getType_id());
        Assert.assertEquals(actualCase.getPriority_id(), expectedCase.getPriority_id());
    }

    @Test(dependsOnMethods = "addCaseTest")
    public void getCaseTest() {
        //int caseID = 124;
        Case actualCase = new CaseAdapter().get(caseID);
        System.out.println(actualCase);
    }

    @Test(dependsOnMethods = "getCaseTest")
    public void getHistoryCaseTest() {
        Response response = new CaseAdapter().getHistory(caseID);
    }

    //проверка только на статус, больше не знаю, какой ассерт здесь можно придумать
    //тело боди хардкод, потому что в боди требуется только одно поле, не вижу смысла создавать объект
    @Test(dependsOnMethods = "getHistoryCaseTest")
    public void CopyAndMoveCasesToSectionTest() {
        String body = "{\n" +
                "  \"case_ids\": \"%s,%s\"\n" +
                "}";

        Section section = Section.builder()
                .name("Section 2")
                .build();
        Section newSection = new SectionAdapter().postAdd(section, projectID);
        int newSectionID = newSection.getId();

        Case someCase = Case.builder()
                .title("+1Case")
                .type_id(CaseTypes.ACCEPTANCE)
                .priority_id(CasePriorityTypes.MEDIUM)
                .build();

        Case newCase = new CaseAdapter().postAdd(someCase, sectionID);
        int newCaseID = newCase.getId();
        //_______________Копирование______________________
        given()
                .body(String.format(body, caseID, newCaseID))
                .when()
                .post(String.format(CaseEndpoints.COPY_CASES, newSectionID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);

        //_______________Перемещение______________________
        given()
                .body(String.format(body, caseID, newCaseID))
                .when()
                .post(String.format(CaseEndpoints.MOVE_CASES, newSectionID))
                .then()
                .log().body()
                .statusCode(HttpStatus.SC_OK);
    }

    @Test (dependsOnMethods = "CopyAndMoveCasesToSectionTest")
    public void updateCaseTest() {
        Case expectedCase = Case.builder()
                .title("SomeCaseUpdate")
                .type_id(CaseTypes.ACCESSIBILITY)
                .priority_id(CasePriorityTypes.CRITICAL)
                .custom_expected(":))))")
                .custom_preconds(":))))")
                .custom_steps(":))))")
                .build();

        Case actualCase = new CaseAdapter().postUpdate(expectedCase, caseID);
        System.out.println(actualCase);
        Assert.assertEquals(actualCase.getTitle(), expectedCase.getTitle());
        Assert.assertEquals(actualCase.getType_id(), expectedCase.getType_id());
        Assert.assertEquals(actualCase.getPriority_id(), expectedCase.getPriority_id());
        Assert.assertEquals(actualCase.getCustom_expected(), expectedCase.getCustom_expected());
        Assert.assertEquals(actualCase.getCustom_preconds(), expectedCase.getCustom_preconds());
        Assert.assertEquals(actualCase.getCustom_steps(), expectedCase.getCustom_steps());
    }

    @Test (dependsOnMethods = "updateCaseTest")
    public void deleteCaseTest(){
        new CaseAdapter().delete(caseID);
    }
}



