package apitesting;

import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class AuthUtils {

    // Method to get Access Token by first logging in and then requesting token
    public static String getAccessToken() {
        // 1. Login - Get auth_code
    	Response loginResponse = given()
    		    .contentType("application/x-www-form-urlencoded")
    		    .formParam("username", "user1")
    		    .formParam("password", "pass123")
    		    .when()
    		    .post("/OAuthRestApi/webapi/auth/login");


        System.out.println("Login response status code: " + loginResponse.getStatusCode());
        System.out.println("Login response body: " + loginResponse.asString());

        if (loginResponse.getStatusCode() != 200) {
            throw new RuntimeException("Login failed! Status: " + loginResponse.getStatusCode());
        }

        String authCode = loginResponse.jsonPath().getString("auth_code");
        if (authCode == null || authCode.isEmpty()) {
            throw new RuntimeException("auth_code not found in login response!");
        }
        System.out.println("Received auth_code: " + authCode);

        // 2. Get Access Token using auth_code
        Response tokenResponse = given()
        	    .contentType("application/x-www-form-urlencoded")
        	    .formParam("auth_code", authCode)
        	    .when()
        	    .post("/OAuthRestApi/webapi/auth/token");


        System.out.println("Token response status code: " + tokenResponse.getStatusCode());
        System.out.println("Token response body: " + tokenResponse.asString());

        if (tokenResponse.getStatusCode() != 200) {
            throw new RuntimeException("Token request failed! Status: " + tokenResponse.getStatusCode());
        }

        String accessToken = tokenResponse.jsonPath().getString("access_token");
        if (accessToken == null || accessToken.isEmpty()) {
            throw new RuntimeException("access_token not found in token response!");
        }
        System.out.println("Received access_token: " + accessToken);

        // Return the extracted access token
        return accessToken;
    }
}
