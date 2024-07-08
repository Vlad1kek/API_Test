package api.test;

import api.endpoints.AccountEndPoints;
import api.payload.Account;
import api.utilities.DataProviders;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

@Ignore
public class DDTest {
    int[] id;
    int x = 0;

    @Test(priority = 1, dataProvider = "Data", dataProviderClass = DataProviders.class)
    public void testPostAccount(String name, String type, String iban, String bic, String account_number,
                                String opening_balance, String virtual_balance, String currency_id,
                                String currency_code, boolean active, int order, boolean include_net_worth,
                                String account_role, String credit_card_type, String monthly_payment_date,
                                String liability_type, String liability_direction, String interest,
                                String interest_period, String notes, int latitude, int longitude, int zoom_level,
                                String opening_balance_date) {

        Account userPayload = new Account();
        userPayload.setName(name);
        userPayload.setType(type);
        userPayload.setIban(iban);
        userPayload.setBic(bic);
        userPayload.setAccount_number(account_number);
        userPayload.setOpening_balance(opening_balance);
        userPayload.setOpening_balance_date(opening_balance_date);
        userPayload.setVirtual_balance(virtual_balance);
        userPayload.setCurrency_id(currency_id);
        userPayload.setCurrency_code(currency_code);
        userPayload.setActive(active);
        userPayload.setOrder(order);
        userPayload.setInclude_net_worth(include_net_worth);
        userPayload.setAccount_role(account_role);
        userPayload.setCredit_card_type(credit_card_type);
        userPayload.setMonthly_payment_date(monthly_payment_date);
        userPayload.setLiability_type(liability_type);
        userPayload.setLiability_direction(liability_direction);
        userPayload.setInterest(interest);
        userPayload.setInterest_period(interest_period);
        userPayload.setNotes(notes);
        userPayload.setLatitude(latitude);
        userPayload.setLongitude(longitude);
        userPayload.setZoom_level(zoom_level);

        Response response = AccountEndPoints.createAccount(userPayload);
        response.then().log().all();

        id[x++] = Integer.getInteger(response.jsonPath().getString("data.id"));

        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test(priority = 2)
    public void testDeleteAccountBuId() {
        for (int i : id) {
            Response response = AccountEndPoints.deleteAccount(String.valueOf(i));

            Assert.assertEquals(response.getStatusCode(), 204);
        }
    }
}
