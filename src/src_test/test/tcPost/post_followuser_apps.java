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

public class post_followuser_apps extends base {

    @BeforeMethod
    public void getData() throws IOException, InterruptedException {


    }

    @Test(priority = 1)
    public void postfollowuser_apps() {
        RestAssured.baseURI = prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_android")).
                header("Authorization", prop.getProperty("token_android"));

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("follow_user_id", ""); //empty on purpose
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.post("/follow");

        Assert.assertEquals(response.getStatusCode(),200);

    }
}
