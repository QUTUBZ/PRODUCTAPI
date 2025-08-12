
package apitesting;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.Iterator;
import java.util.List;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import models.Product;
import utils.TestDataUtils;
import io.restassured.RestAssured;

public class ProductTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://webapps.tekstac.com";
    }

    // DataProvider to load Product data from JSON file
    @DataProvider(name = "productData")
    public Iterator<Object[]> productDataProvider() {
        List<Product> products = TestDataUtils.getProductsFromJson("testdata/products.json");
        return products.stream()
                       .map(product -> new Object[] { product })
                       .iterator();
    }

    // Data-driven addProduct test using JSON data
    @Test(dataProvider = "productData")
    public void addProduct(Product product) {
        String token = AuthUtils.getAccessToken();

        given()
            .header("Authorization", token)
            .contentType("application/json")
            .body(product)
        .when()
            .post("/OAuthRestApi/webapi/addProduct")
        .then()
            .log().ifValidationFails()
            .statusCode(200)
            .body("", not(empty()))
            .body("id", everyItem(notNullValue()))
            .body("name", everyItem(notNullValue()))
            .body("amount", everyItem(notNullValue()))
            .body("id", hasItem(String.valueOf(product.getId())))
            .body("name", hasItem(product.getName()))
            .body("amount", hasItem(String.valueOf(product.getAmount())));
    }

    @Test
    public void viewAllProducts() {
        String token = AuthUtils.getAccessToken();

        given()
            .header("Authorization", token)
        .when()
            .get("/OAuthRestApi/webapi/getAllProducts")
        .then()
            .log().ifValidationFails()
            .statusCode(200)
            .body("", not(empty()))
            .body("id", everyItem(notNullValue()))
            .body("name", everyItem(notNullValue()))
            .body("amount", everyItem(notNullValue()));
    }

    @Test
    public void viewProductById() {
        String token = AuthUtils.getAccessToken();

        given()
            .header("Authorization", token)
        .when()
            .get("/OAuthRestApi/webapi/getProductbyId/30")
        .then()
            .log().ifValidationFails()
            .statusCode(200)
            .body("id", equalTo("1"))
            .body("name", notNullValue())
            .body("amount", notNullValue());
    }

    @Test
    public void viewProductByName() {
        String token = AuthUtils.getAccessToken();

        given()
            .header("Authorization", token)
        .when()
            .get("/OAuthRestApi/webapi/viewProductByName?name=Apple")
        .then()
            .log().ifValidationFails()
            .statusCode(200)
            .body("name", containsString("Mobile"))
            .body("id", notNullValue())
            .body("amount", notNullValue());
    }

    @Test
    public void updateProduct() {
        String token = AuthUtils.getAccessToken();
        Product updatedProduct = new Product(2, "Shirt", 4500);

        given()
            .header("Authorization", token)
            .contentType("application/json")
            .body(updatedProduct)
        .when()
            .put("/OAuthRestApi/webapi/updateProduct/2")
        .then()
            .log().ifValidationFails()
            .statusCode(200)
            .body("", not(empty()))
            .body("id", everyItem(notNullValue()))
            .body("name", everyItem(notNullValue()))
            .body("amount", everyItem(notNullValue()))
            .body("id", hasItem(String.valueOf(updatedProduct.getId())))
            .body("name", hasItem(updatedProduct.getName()))
            .body("amount", hasItem(String.valueOf(updatedProduct.getAmount())));
    }

    @Test
    public void deleteProduct() {
        String token = AuthUtils.getAccessToken();
        int deletedId = 1;

        given()
            .header("Authorization", token)
        .when()
            .delete("/OAuthRestApi/webapi/delProduct/" + deletedId)
        .then()
            .log().ifValidationFails()
            .statusCode(200)
            .body("", notNullValue())
            .body("id", not(hasItem(String.valueOf(deletedId))));
    }
}

