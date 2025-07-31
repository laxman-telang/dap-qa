package journeys.walletManagementJourney.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class WalletCustodyAPITest {

    private Response response;
    private String walletPayload;

    @Given("the wallet payload with account ID {string}, sort code {string}, and customer ID {string}")
    public void prepareWalletPayload(String accountId, String sortCode, String customerId) {
        walletPayload = String.format("""
            {
              "account_id": "%s",
              "sort_code": "%s",
              "customer_id": "%s",
              "created_by": "test-user"
            }
        """, accountId, sortCode, customerId);
    }

    @When("the user sends a POST request to {string}")
    public void sendPostRequest(String endpoint) {
        response = given()
            .header("Content-Type", "application/json")
            .body(walletPayload)
            .when()
            .post(endpoint);
    }

    @When("the user sends a GET request to {string}")
    public void sendGetRequest(String endpoint) {
        response = given()
            .header("Accept", "application/json")
            .when()
            .get(endpoint);
    }

    @Then("the response status code should be {int}")
    public void verifyStatusCode(int statusCode) {
        response.then().statusCode(statusCode);
    }

    @Then("the response should contain {string}")
    public void verifyResponseContains(String key) {
        response.then().body("$", hasKey(key));
    }
}
