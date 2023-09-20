import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class UserInfoTests {

    @BeforeClass
    public static void setUp() {
        // Set the base URL for your mock API
        RestAssured.baseURI = "https://ayco69dbm3.execute-api.us-east-1.amazonaws.com/singular_qa";
    }

    @Test
    public void testUserInfoWithValidSession() {
        // Obtain a valid session token (you may need to call the /authorize endpoint here)

        // Define the Authorization header with the token
        String validToken = "0ac61bdf-b04d-467e-a620-37b797539b7e"; // Replace with the valid token

        // Send a GET request to /info with the valid token
        given()
                .header("Authorization", validToken)
                .when()
                .get("/info")
                .then()
                .statusCode(200)
                .body("code", equalTo(10))
                .body("message", equalTo("SUCCESS"));


        // Additional assertions can be added as needed
    }

    @Test
    public void testUserInfoWithInvalidSession() {
        // Define an invalid session token (e.g., an expired or incorrect token)
        String invalidToken = "invalid_token"; // Replace with the invalid token

        // Send a GET request to /info with the invalid token
        Response response = given()
                .header("Authorization", invalidToken)
                .when()
                .get("/info")
                .then()
                .statusCode(401) // Adjust the status code as per your API's behavior for invalid sessions
                .extract()
                .response();

        // Additional assertions can be added as needed
    }
}
