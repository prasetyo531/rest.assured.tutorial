package test.tcGet;

import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import base.base;

import static io.restassured.RestAssured.given;

public class get_reviewlist_web extends base {

    @BeforeMethod
    public void getData() throws IOException, InterruptedException {

    }

    @Test
    public void getreview_web() {
        RestAssured.baseURI = prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("product_id", "109");
        loginCredentials.addProperty("text", "Prodak ini bagus coba lagi ahhhh jumblascasbas");
        loginCredentials.addProperty("rating", "5.0");
        loginCredentials.addProperty("package_quality", "y");
        loginCredentials.addProperty("repurchase", "y");
        loginCredentials.addProperty("think_price", "e");
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.get("/reviews");

        Assert.assertEquals(response.getStatusCode(),200);

        Integer response_text = response.path("code");
        System.out.println(response_text);
//        Assert.assertNotNull(response_token);
        Assert.assertNotSame(response_text,200);
    }
}
