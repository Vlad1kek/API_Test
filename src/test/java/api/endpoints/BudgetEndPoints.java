package api.endpoints;

import api.payload.Budget;
import api.payload.Limit;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class BudgetEndPoints extends BaseEndPoints{

    public static Response createBudget(Budget payload) {

        return given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .body(payload)
                .when()
                .post(getURL("post_bud_url"));
    }

    public static Response readSingleBudget(String id) {

        return given()
                .pathParam("id", id)
                .queryParam("start", 2024-06-01)
                .queryParam("end", 2025-12-31)
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .when()
                .get(getURL("get_bud_url"));
    }

    public static Response updateBudget(String id, Budget payload) {

        return given()
                .contentType(ContentType.JSON)
                .pathParam("id", id)
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .body(payload)
                .when()
                .put(getURL("put_bud_url"));
    }

    public static Response createLimitsBudget(String id, Limit payload) {

        return given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParam("id", id)
                .body(payload)
                .when()
                .post(getURL("post_bud_lim_url"));
    }

    public static Response readSingleLimitBudget(String id, String idLimit) {

        return given()
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParam("id", id)
                .pathParam("id_limit", idLimit)
                .when()
                .get(getURL("get_bud_lim_url"));
    }

    public static Response updateLimitBudget(String id, String idLimit, Limit payload) {

        return given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParam("id", id)
                .pathParam("id_limit", idLimit)
                .body(payload)
                .when()
                .put(getURL("update_bud_lim_url"));
    }

    public static Response readTransactionByBudgetLimit(String id, String idLimit) {

        return given()
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParam("id", id)
                .pathParam("id_limit", idLimit)
                .queryParam("limit", 10)
                .queryParam("page", 1)
                .when()
                .get(getURL("get_bud_trs_url"));
    }

    public static Response deleteLimitBudget(String id, String idLimit) {

        return given()
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParam("id", id)
                .pathParam("id_limit", idLimit)
                .when()
                .delete(getURL("delete_bud_lim_url"));
    }

    public static Response deleteBudget(String id) {

        return given()
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParam("id", id)
                .when()
                .delete(getURL("delete_bud_url"));
    }
}
