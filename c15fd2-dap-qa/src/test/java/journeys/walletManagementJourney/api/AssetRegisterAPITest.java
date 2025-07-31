package journeys.walletManagementJourney.api;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class AssetRegisterAPITest {

    private Response response;
    private String payload;

    @Given("the asset registration payload with asset ID {string}, type {string}, and owner party ID {string}")
    public void prepareAssetPayload(String assetId, String type, String partyId) {
        payload = String.format("""
            {
              "asset_id": "%s",
              "asset_type": "%s",
              "description": "Gold bar",
              "owner_party_id": "%s",
              "valuation": 100000,
              "metadata": {"source": "UI"},
              "registered_by": "test-user"
            }
        """, assetId, type, partyId);
    }

    @When("the user sends a POST request to {string}")
    public void sendPostRequest(String endpoint) {
        response = given()
            .header("Content-Type", "application/json")
            .body(payload)
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
