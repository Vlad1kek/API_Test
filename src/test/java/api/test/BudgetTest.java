package api.test;

import api.endpoints.BudgetEndPoints;
import api.payload.Budget;
import api.payload.Limit;
import com.github.javafaker.Faker;
import io.qameta.allure.Description;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.response.Response;
import org.hamcrest.MatcherAssert;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BudgetTest extends BaseTest{
    private Faker faker;
    private Budget budgetPayload;
    private Limit limitPayload;
    private String id;
    private String idLimit;

    @BeforeClass
    public void setupData() {
        faker = new Faker();
        budgetPayload = new Budget();

        budgetPayload.setName(faker.pokemon().name());
        budgetPayload.setActive(false);
        budgetPayload.setNotes(faker.music().chord());
        budgetPayload.setAuto_budget_type("reset");
        budgetPayload.setAuto_budget_currency_id("12");
        budgetPayload.setAuto_budget_currency_code("EUR");
        budgetPayload.setAuto_budget_amount("1012.12");
        budgetPayload.setAuto_budget_period("monthly");

        limitPayload = new Limit();

        limitPayload.setCurrency_id("5");
        limitPayload.setCurrency_code("EUR");
        limitPayload.setStart("2024-07-17");
        limitPayload.setEnd("2024-08-17");
        limitPayload.setAmount("123.45");
    }

    @Description("Store a new budget")
    @Test(priority = 1)
    public void testPostBudget() {
        Response response = BudgetEndPoints.createBudget(budgetPayload);
        id = String.valueOf(response.jsonPath().getString("data.id"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
    }

    @Description("Get a single budget")
    @Test(priority = 2)
    public void testGetSingleBudget() {
        Response response = BudgetEndPoints.readSingleBudget(id);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
        Assert.assertEquals(response.jsonPath().get("data.type").toString(), "budgets");
        Assert.assertEquals(response.jsonPath().get("data.attributes.name").toString(),
                budgetPayload.getName());
        Assert.assertFalse(response.jsonPath().get("data.attributes.active"));
        Assert.assertEquals(response.jsonPath().get("data.attributes.auto_budget_amount").toString(),
                budgetPayload.getAuto_budget_amount());
        Assert.assertEquals(response.jsonPath().get("data.attributes.notes").toString(),
                budgetPayload.getNotes());

        MatcherAssert.assertThat(
                "Validate json schema",
                response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath("BudgetJsonSchema.json"));
    }

    @Description("Update existing budget")
    @Test(priority = 3)
    public void testUpdateBudget() {
        budgetPayload.setName(faker.rickAndMorty().character());
        budgetPayload.setNotes(faker.superhero().name());
        budgetPayload.setAuto_budget_amount("1348.43");

        Response response = BudgetEndPoints.updateBudget(id, budgetPayload);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);

        //checking data after update
        Response responseAfterUpdate = BudgetEndPoints.readSingleBudget(id);
        Assert.assertEquals(responseAfterUpdate.getStatusCode(), 200);
        Assert.assertEquals(responseAfterUpdate.header("Content-Type"), "application/vnd.api+json");
        Assert.assertEquals(responseAfterUpdate.jsonPath().get("data.type").toString(), "budgets");
        Assert.assertEquals(responseAfterUpdate.jsonPath().get("data.attributes.name").toString(),
                budgetPayload.getName());
        Assert.assertFalse(responseAfterUpdate.jsonPath().get("data.attributes.active"));
        Assert.assertEquals(responseAfterUpdate.jsonPath().get("data.attributes.auto_budget_amount").toString(),
                budgetPayload.getAuto_budget_amount());
        Assert.assertEquals(responseAfterUpdate.jsonPath().get("data.attributes.notes").toString(),
                budgetPayload.getNotes());
    }

    @Description("Store a new budget limit")
    @Test(priority = 4)
    public void testPostBudgetLimit() {
        Response response = BudgetEndPoints.createLimitsBudget(id, limitPayload);
        idLimit = String.valueOf(response.jsonPath().getString("data.id"));

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
    }

    @Description("Get single budget limit")
    @Test(priority = 5)
    public void testGetSingleBudgetLimit() {
        Response response = BudgetEndPoints.readSingleLimitBudget(id, idLimit);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
        Assert.assertEquals(response.jsonPath().get("data.type").toString(), "budget_limits");
        Assert.assertEquals(response.jsonPath().get("data.attributes.amount").toString(),
                limitPayload.getAmount());

        MatcherAssert.assertThat(
                "Validate json schema",
                response.getBody().asString(),
                JsonSchemaValidator.matchesJsonSchemaInClasspath("BudgetLimitJsonSchema.json"));
    }

    @Description("Update existing budget limit")
    @Test(priority = 6)
    public void testUpdateBudgetLimit() {
        limitPayload.setStart("2024-08-23");
        limitPayload.setEnd("2024-10-01");
        limitPayload.setAmount("232.64");

        Response response = BudgetEndPoints.updateLimitBudget(id, idLimit, limitPayload);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);

        //checking data after update
        Response responseAfterUpdate = BudgetEndPoints.readSingleLimitBudget(id, idLimit);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
        Assert.assertEquals(response.jsonPath().get("data.attributes.amount").toString(),
                limitPayload.getAmount());
        Assert.assertEquals(response.jsonPath().get("data.attributes.start").toString(),
                limitPayload.getStart() + "T00:00:00+02:00");
        Assert.assertEquals(response.jsonPath().get("data.attributes.end").toString(),
                limitPayload.getEnd() + "T23:59:59+02:00");
    }

    @Description("List all transactions by a budget limit ID")
    @Test(priority = 7)
    public void testGetAllTransactionBudgetLimit() {
        Response response = BudgetEndPoints.readTransactionByBudgetLimit(id,idLimit);

        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(response.header("Content-Type"), VND_API_JSON);
    }

    @Description("Delete  a budget limit")
    @Test(priority = 8)
    public void testDeleteLimitBudget() {
        Response response = BudgetEndPoints.deleteLimitBudget(id, idLimit);

        Assert.assertEquals(response.getStatusCode(), 204);
    }

    @Description("Delete a budget")
    @Test(priority = 9)
    public void testDeleteBudget() {
        Response response = BudgetEndPoints.deleteBudget(id);

        Assert.assertEquals(response.getStatusCode(), 204);
    }
}
