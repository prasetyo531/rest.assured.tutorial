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

public class post_logout_web extends base {

    @BeforeMethod
    public void getData() throws IOException, InterruptedException {

    }

    @Test(priority = 1)
    public void runpostlogin_web() {
        RestAssured.baseURI = prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_web")).
                header("key", prop.getProperty("key_web")).log().all().
                header("Authorization", prop.getProperty("token_web"));

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("username", "testqa");
        loginCredentials.addProperty("password", "test123");
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.post("/login");

        Assert.assertEquals(response.getStatusCode(),200);

        String response_token = response.path("data.token");
        System.out.println(response_token);
//        Assert.assertNotNull(response_token);
        Assert.assertNotSame(response_token,null);
    }

    @Test(priority = 2)
    public void runpostlogout_web()
    {
        //Creating Issue/Defect

        RestAssured.baseURI= prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_web")).
                header("key", prop.getProperty("key_web")).log().all().
                header("Authorization", prop.getProperty("token_web"));

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("device_id", "3");
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.post("/logout");

        Assert.assertEquals(200, response.getStatusCode());

        String response_msg = response.path("meta.msg");
        System.out.println(response_msg);
//        Assert.assertNotNull(response_token);
        Assert.assertEquals(response_msg,"success");

    }
}
