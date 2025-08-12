package apitesting;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://webapps.tekstac.com";

        RequestSpecification reqSpec = new RequestSpecBuilder()
            .log(LogDetail.ALL)   // Specify what to log
            .build();

        RestAssured.requestSpecification = reqSpec;
    }
}
