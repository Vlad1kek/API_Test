package api.endpoints;

import api.payload.Bill;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BillsEndPoints extends BaseEndPoints {

    public static Response createBill(Bill payload) {

        return given()
                .accept("application/vnd.api+json")
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .body(payload)

                .when()
                .post(getURL("post_bil_url"));
    }

    public static Response readSingleBill(String id) {

        return given()
                .accept("application/vnd.api+json")
                .pathParam("id", id)
                .queryParam("start", 2024-05-1)
                .queryParam("end", 2025-12-31)
                .headers("Authorization", "Bearer " + BEARER_TOKEN)

                .when()
                .get(getURL("get_bil_url"));
    }

    public static Response readAllAttachmentsToBill(String id) {

        return given()
                .accept("application/vnd.api+json")
                .pathParam("id", id)
                .queryParam("limit", 10)
                .queryParam("page", 1)
                .headers("Authorization", "Bearer " + BEARER_TOKEN)

                .when()
                .get(getURL("get_att_bil_url"));
    }

    public static Response readAllRulesBill(String id) {

        return given()
                .accept("application/vnd.api+json")
                .pathParam("id", id)
                .headers("Authorization", "Bearer " + BEARER_TOKEN)

                .when()
                .get(getURL("get_rules_bil_url"));
    }

    public static Response readAllTransactionsBill(String id) {

        return given()
                .accept("application/vnd.api+json")
                .pathParam("id", id)
                .queryParam("start", 2024-06-01)
                .queryParam("end", 2025-12-31)
                .headers("Authorization", "Bearer " + BEARER_TOKEN)

                .when()
                .get(getURL("get_transactions_bil_url"));
    }

    public static Response updateBill(String id, Bill payload) {

        return given()
                .accept("application/vnd.api+json")
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .body(payload)

                .when()
                .put(getURL("update_bil_url"));
    }

    public static Response deleteBill(String id) {

        return given()
                .pathParam("id", id)
                .headers("Authorization", "Bearer " + BEARER_TOKEN)

                .when()
                .delete(getURL("delete_bil_url"));
    }
}
