package test.tcPut;

import base.base;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class put_userpost_apps extends base {

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {

    }

    @Test(priority = 1)
    public void runpostlogin_apps() {
        RestAssured.baseURI = prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_android")).
                header("key", prop.getProperty("key_android")).log().all().
                header("Authorization", prop.getProperty("token_android"));

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("username", "putwid");
        loginCredentials.addProperty("password", "tester123");
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.post("/login");

        Assert.assertEquals(response.getStatusCode(),200);

        String response_token = response.path("data.token");
        System.out.println(response_token);
//        Assert.assertNotNull(response_token);
        Assert.assertNotSame(response_token,null);
    }

    @Test(priority = 2)
    public void putuserpost_apps() {

        RestAssured.baseURI = prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_android")).
                header("key", prop.getProperty("key_android")).log().all().
                header("Authorization", prop.getProperty("token_android"));

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("caption", "Dapet gratisan, lumayan lah yaaa");
        loginCredentials.addProperty("product_ids", "0");
        loginCredentials.addProperty("id", "0");
        loginCredentials.addProperty("product_id", "248");
        loginCredentials.addProperty("text", "boleh add review lagi prod 248 si 234 Classpass, the beloved fitness app for booking workout classes across studios and gyms through one monthly subscription, is planning to do more than just get you fit. It wants to help expand your schedule to “all types of experiences, including music, massage, and nearby concerts you haven’t heard about yet. Payal Kadakia, Classpass founder & CEO, shared the news at this year’s Startup Grind Conference in a conversation with Trinity Ventures’ Anjula Acharia-Bath to chat about the company’s story from their early beginnings to its visionary future.Kadakia created two");
        loginCredentials.addProperty("rating", "4.5");
        loginCredentials.addProperty("package_quality", "4.6");
        loginCredentials.addProperty("repurchase", "y");
        loginCredentials.addProperty("think_price", "e");
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.put("/user/post/0"); //nubr_post

        Assert.assertEquals(response.getStatusCode(),200);
    }

}
