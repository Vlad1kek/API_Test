package api.test;

import api.endpoints.AttachmentEndPoints;
import api.payload.Attachment;
import com.github.javafaker.Faker;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;


public class AttachmentTest {
    Faker faker;
    Attachment attachmentPayload;
    private String id;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        attachmentPayload = new Attachment();

        attachmentPayload.setFilename(faker.name().username() + ".pdf");
        attachmentPayload.setAttachable_type("Bill");
        attachmentPayload.setAttachable_id("1");
        attachmentPayload.setTitle(faker.book().title());
        attachmentPayload.setNotes(faker.pokemon().name());
    }

    @Test(priority = 1)
    public void testPostAttachment() {
        Response response = AttachmentEndPoints.createAttachment(attachmentPayload);
        response.then().log().all();

        id = String.valueOf(response.jsonPath().getString("data.id"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/vnd.api+json");
    }

    @Test(priority = 2)
    public void testGetSingleAttachment() {
        Response response = AttachmentEndPoints.readAttachment(id);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/vnd.api+json");
        Assert.assertEquals(response.jsonPath().get("data.attributes.filename").toString(),
                attachmentPayload.getFilename());
        Assert.assertEquals(response.jsonPath().get("data.attributes.attachable_type").toString(),
                attachmentPayload.getAttachable_type());
        Assert.assertEquals(response.jsonPath().get("data.attributes.attachable_id").toString(),
                attachmentPayload.getAttachable_id());
        Assert.assertEquals(response.jsonPath().get("data.attributes.title").toString(),
                attachmentPayload.getTitle());
        Assert.assertEquals(response.jsonPath().get("data.attributes.notes").toString(),
                attachmentPayload.getNotes());

        MatcherAssert.assertThat(
                "Validate json schema",
                response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath("AttachmentJsonSchema.json"));
    }

    @Test(priority = 3)
    public void testUpdateAttachment() {
        attachmentPayload.setFilename(faker.name().username() + ".pdf");
        attachmentPayload.setNotes(faker.book().title());
        attachmentPayload.setNotes(faker.pokemon().name());

        Response response = AttachmentEndPoints.updateAttachment(id, attachmentPayload);
        response.then().log().all();

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.header("Content-Type"), "application/vnd.api+json");

        //checking data after update
        Response responseAfterUpdate = AttachmentEndPoints.readAttachment(id);
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
        Assert.assertEquals(responseAfterUpdate.jsonPath().get("data.attributes.filename").toString(),
                attachmentPayload.getFilename());
        Assert.assertEquals(responseAfterUpdate.jsonPath().get("data.attributes.title").toString(),
                attachmentPayload.getTitle());
        Assert.assertEquals(responseAfterUpdate.jsonPath().get("data.attributes.notes").toString(),
                attachmentPayload.getNotes());
    }

    @Test(priority = 4)
    public void testUploadAttachment() {
        File file = new File(".//testData/UploadAttachment.txt");
        Response response = AttachmentEndPoints.uploadAttachment(id, file);

        Assert.assertEquals(response.getStatusCode(), 204);
    }

    @Test(priority = 5)
    public void testDownloadAttachment() {
        Response response = AttachmentEndPoints.downloadAttachment(id);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/octet-stream");
    }

    @Test(priority = 6)
    public void testDeleteAttachment() {
        Response response = AttachmentEndPoints.deleteAttachment(id);

        Assert.assertEquals(response.getStatusCode(), 204);
    }
}



