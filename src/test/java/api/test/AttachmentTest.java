package api.test;

import api.endpoints.AttachmentEndPoints;
import api.payload.Attachment;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;


public class AttachmentTest extends BaseTest{
    private Faker faker;
    private Attachment attachmentPayload;
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

    @Description("Store a new attachment")
    @Test(priority = 1)
    public void testPostAttachment() {
        Response response = AttachmentEndPoints.createAttachment(attachmentPayload);

        id = String.valueOf(response.jsonPath().getString("data.id"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
    }

    @Description("Get a single attachment")
    @Test(priority = 2)
    public void testGetSingleAttachment() {
        Response response = AttachmentEndPoints.readAttachment(id);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
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

    @Description("Update existing attachment")
    @Test(priority = 3)
    public void testUpdateAttachment() {
        attachmentPayload.setFilename(faker.name().username() + ".pdf");
        attachmentPayload.setNotes(faker.book().title());
        attachmentPayload.setNotes(faker.pokemon().name());

        Response response = AttachmentEndPoints.updateAttachment(id, attachmentPayload);

        Assert.assertEquals(response.getStatusCode(),200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);

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

    @Description("Upload an attachment")
    @Test(priority = 4)
    public void testUploadAttachment() {
        File file = new File(".//testData/UploadAttachment.txt");
        Response response = AttachmentEndPoints.uploadAttachment(id, file);

        Assert.assertEquals(response.getStatusCode(), 204);
    }

    @Description("Download a single attachment")
    @Test(priority = 5)
    public void testDownloadAttachment() {
        Response response = AttachmentEndPoints.downloadAttachment(id);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), "application/octet-stream");
    }
    @Description("Delete an attachment")
    @Test(priority = 6)
    public void testDeleteAttachment() {
        Response response = AttachmentEndPoints.deleteAttachment(id);

        Assert.assertEquals(response.getStatusCode(), 204);
    }
}



