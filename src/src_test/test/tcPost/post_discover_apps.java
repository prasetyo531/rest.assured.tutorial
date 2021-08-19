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

public class post_discover_apps extends base {

    @BeforeMethod
    public void getData() throws IOException, InterruptedException {

    }

    @Test
    public void postdiscover_apps() {

        RestAssured.baseURI = prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_web")).
                header("Authorization", prop.getProperty("token_web"));

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("type", "post");
        loginCredentials.addProperty("object_id", "198185");
        loginCredentials.addProperty("text", ""); //empty on purpose
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.post("/user/comment");

        Assert.assertEquals(response.getStatusCode(), 200);


    }


}
