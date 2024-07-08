package api.endpoints;

import api.payload.Account;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

// Created for perform Create, Read, Update, Delete requests the account API
public class AccountEndPoints extends BaseEndPoints{

    public static Response createAccount(Account payload) {

        return given()
                .contentType("application/json")
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .body(payload)
                .when()
                .post(getURL("post_acc_url"));
    }

    public static Response readAccount(String id) {

        return given()
                .pathParam("id", id)
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .when()
                .get(getURL("get_acc_url"));
    }

    public static Response updateAccount(String id, Account payload) {

        return given()
                .contentType(ContentType.JSON)
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParam("id", id)
                .body(payload)
                .when()
                .put(getURL("update_acc_url"));
    }

    public static Response deleteAccount(String id) {

        return given()
                .headers("Authorization", "Bearer " + BEARER_TOKEN)
                .pathParam("id", id)
                .when()
                .delete(getURL("delete_acc_url"));
    }
}
