package api.test;

import api.endpoints.AccountEndPoints;
import api.payload.Account;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AccountTest extends BaseTest{
    private Account accountPayload;
    private String id;
    private Faker faker;

    @BeforeClass
    public void setupData(){

        faker = new Faker();
        accountPayload = new Account();

        accountPayload.setName(faker.name().name());
        accountPayload.setType("asset");
        accountPayload.setIban(faker.finance().iban());
        accountPayload.setBic(faker.finance().bic());
        accountPayload.setAccount_number(faker.number().digits(15));
        accountPayload.setOpening_balance("1032.33");
        accountPayload.setOpening_balance_date("2024-06-15T10:30:00Z");
        accountPayload.setVirtual_balance("123.74");
        accountPayload.setCurrency_id("12");
        accountPayload.setCurrency_code("EUR");
        accountPayload.setActive(false);
        accountPayload.setOrder(1);
        accountPayload.setInclude_net_worth(true);
        accountPayload.setAccount_role("defaultAsset");
        accountPayload.setCredit_card_type("monthlyFull");
        accountPayload.setMonthly_payment_date("2024-07-15T10:30:00Z");
        accountPayload.setLiability_type("loan");
        accountPayload.setLiability_direction("credit");
        accountPayload.setInterest("5.3");
        accountPayload.setInterest_period("monthly");
        accountPayload.setNotes(faker.pokemon().name());
        accountPayload.setLatitude(51.983333);
        accountPayload.setLongitude(5.916667);
        accountPayload.setZoom_level(6);
    }

    @Description("Create new account")
    @Test(priority = 1)
    public void testPostAccount(){
        Response response = AccountEndPoints.createAccount(accountPayload);

        id = String.valueOf(response.jsonPath().getString("data.id"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
    }

    @Description("Get Single Account")
    @Test(priority = 2)
    public void testGetSingleAccount() {
        Response response = AccountEndPoints.readAccount(id);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
        Assert.assertEquals(response.jsonPath().get("data.attributes.name").toString(),
                accountPayload.getName());
        Assert.assertEquals(response.jsonPath().get("data.attributes.notes").toString(),
                accountPayload.getNotes());
        Assert.assertEquals(response.jsonPath().get("data.attributes.iban").toString(),
                accountPayload.getIban());
        Assert.assertEquals(response.jsonPath().get("data.attributes.account_number").toString(),
                accountPayload.getAccount_number());

        MatcherAssert.assertThat(
                "Validate json schema",
                response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath("AccountJsonSchema.json"));
    }

    @Description("Update existing account")
    @Test(priority = 3)
    public void testUpdateAccount() {
        //update data using payload
        accountPayload.setName(faker.name().name());
        accountPayload.setNotes(faker.random().toString());

        Response response = AccountEndPoints.updateAccount(id, accountPayload);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);

        //checking data after update
        Response responseAfterUpdate = AccountEndPoints.readAccount(id);
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
        Assert.assertEquals(responseAfterUpdate.jsonPath().get("data.attributes.name").toString(),
                accountPayload.getName());
        Assert.assertEquals(responseAfterUpdate.jsonPath().get("data.attributes.notes").toString(),
                accountPayload.getNotes());
    }

    @Description("Delete Account")
    @Test(priority = 4)
    public void testDeleteAccount() {
        Response response = AccountEndPoints.deleteAccount(id);

        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
