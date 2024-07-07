package api.endpoints;

import api.payload.Attachment;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;

public class AttachmentEndPoints extends BaseEndPoints {

    public static Response createAttachment(Attachment payload) {

        return given()
                .contentType("application/json")
                .accept( "application/vnd.api+json")
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .body(payload)

                .when()
                .post(getURL("post_att_url"));
    }

    public static Response readAttachment(String id) {

        return given()
                .pathParam("id", id)
                .headers("Authorization", "Bearer " + BEARER_TOKEN)

                .when()
                .get(getURL("get_att_url"));
    }

    public static Response updateAttachment(String id, Attachment payload) {

        return given()
                .contentType(ContentType.JSON)
                .accept("application/vnd.api+json")
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParam("id", id)
                .body(payload)

                .when()
                .put(getURL("update_att_url"));
    }

    public static Response uploadAttachment(String id, File file) {

        return given()
                .accept("*/*")
                .contentType("application/octet-stream")
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParam("id", id)
                .body(file)

                .when()
                .post(getURL("upload_att_url"));

    }

    public static Response downloadAttachment(String id) {

        return given()
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParam("id", id)

                .when()
                .get(getURL("download_att_url"));
    }

    public static Response deleteAttachment(String id) {

        return given()
                .pathParam("id", id)
                .accept("*/*")
                .headers("Authorization", "Bearer " + BEARER_TOKEN)

                .when()
                .delete(getURL("delete_att_url"));
    }
}
