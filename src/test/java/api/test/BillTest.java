package api.test;

import api.endpoints.BillsEndPoints;
import api.payload.Bill;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BillTest extends BaseTest{
    private Faker faker;
    private Bill billPayload;
    private String id;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        billPayload = new Bill();

        billPayload.setCurrency_id("5");
        billPayload.setCurrency_code("EUR");
        billPayload.setName(faker.artist().name());
        billPayload.setAmount_min("100.32");
        billPayload.setAmount_max("200.65");
        billPayload.setDate("2024-08-15T00:00:00Z");
        billPayload.setEnd_date("2024-08-15T10:30:00Z");
        billPayload.setExtension_date("2024-08-15T10:30:00Z");
        billPayload.setRepeat_freq("monthly");
        billPayload.setSkip(0);
        billPayload.setActive(true);
        billPayload.setNotes(faker.harryPotter().character());
        billPayload.setObject_group_id("5");
        billPayload.setObject_group_title(faker.food().fruit());
    }

    @Description("Store a new bill")
    @Test(priority = 1)
    public void testPostBill() {
        Response response = BillsEndPoints.createBill(billPayload);
        id = String.valueOf(response.jsonPath().getString("data.id"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
    }

    @Description("Get a single bill")
    @Test(priority = 2)
    public void testGetSingleBill() {
        Response response = BillsEndPoints.readSingleBill(id);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
        Assert.assertEquals(response.jsonPath().get("data.attributes.name").toString(),
                billPayload.getName());
        Assert.assertEquals(response.jsonPath().get("data.attributes.amount_min").toString(),
                billPayload.getAmount_min());
        Assert.assertEquals(response.jsonPath().get("data.attributes.amount_max").toString(),
                billPayload.getAmount_max());
        Assert.assertEquals(response.jsonPath().get("data.attributes.notes").toString(),
                billPayload.getNotes());

        MatcherAssert.assertThat(
                "Validate json schema",
                response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath("BillJsonSchema.json"));
    }

    @Description("List of all attachments uploaded to the bill")
    @Test(priority = 3)
    public void testGetAttachmentToBill() {
        Response response = BillsEndPoints.readAllAttachmentsToBill(id);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
    }

    @Description("List all rules associated with the bill")
    @Test(priority = 4)
    public void testGetRulesWithBill() {
        Response response = BillsEndPoints.readAllRulesBill(id);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
    }

    @Description("List all transactions associated with the bill")
    @Test(priority = 5)
    public void testGetTransactionsWithBill() {
        Response response = BillsEndPoints.readAllTransactionsBill(id);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
    }

    @Description("Update existing bill")
    @Test(priority = 6)
    public void testUpdateBill() {
        billPayload.setName(faker.pokemon().name());
        billPayload.setAmount_min("345.32");
        billPayload.setAmount_max("743.97");
        billPayload.setNotes(faker.hobbit().character());

        Response response = BillsEndPoints.updateBill(id, billPayload);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);

        //checking data after update
        Response responseAfterUpdate = BillsEndPoints.readSingleBill(id);
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
        Assert.assertEquals(responseAfterUpdate.jsonPath().get("data.attributes.name").toString(),
                billPayload.getName());
        Assert.assertEquals(responseAfterUpdate.jsonPath().get("data.attributes.amount_min").toString(),
                billPayload.getAmount_min());
        Assert.assertEquals(responseAfterUpdate.jsonPath().get("data.attributes.amount_max").toString(),
                billPayload.getAmount_max());
        Assert.assertEquals(responseAfterUpdate.jsonPath().get("data.attributes.notes").toString(),
                billPayload.getNotes());
    }

    @Description("Delete a bill")
    @Test(priority = 7)
    public void testDeleteBill() {
        Response response = BillsEndPoints.deleteBill(id);

        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
