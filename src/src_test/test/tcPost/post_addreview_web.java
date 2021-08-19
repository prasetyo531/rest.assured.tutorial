package test.tcPost;

import base.base;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class post_addreview_web extends base {

    @BeforeMethod
    public void getData() throws IOException, InterruptedException {

    }

    /*
    this test only skip insert image
     */
    @Test
    public void postaddreview_web() {

        RestAssured.baseURI = prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_web")).
                header("Authorization", prop.getProperty("token_web"));

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("product_id", "0");
        loginCredentials.addProperty("text", "");  //empty on purpose
        loginCredentials.addProperty("rating", "4.8");
        loginCredentials.addProperty("package_quality", "4.6");
        loginCredentials.addProperty("repurchase", "y");
        loginCredentials.addProperty("think_price", "e");
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.post("/user/review");

        Assert.assertEquals(response.getStatusCode(),200);

//        Boolean response_isfollow = response.path("data.is_follow");
//        System.out.println(response_isfollow);
////        Assert.assertNotNull(response_token);
//        Assert.assertNotSame(response_isfollow,null);
    }
}
