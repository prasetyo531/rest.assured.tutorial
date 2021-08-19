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

public class put_userreview_apps extends base {

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
    public void putuserreview_apps() {

        RestAssured.baseURI = prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_android")).
                header("key", prop.getProperty("key_android")).log().all().
                header("Authorization", prop.getProperty("token_put_android"));

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("product_id", "702"); //ambil dari review
        loginCredentials.addProperty("text", "produk paling bermanfaat. dipake klo pas mau tidur klo lagi jerawatan, bisa lsg kempes. susah bgt beli du toko offline, sering bgt kehabisan. dan g tau kenapa cepet habis nya hahaha karna sering bgt dipake klo dah dirumah.");
        loginCredentials.addProperty("rating", "5.0");
        loginCredentials.addProperty("package_quality", "4.6");
        loginCredentials.addProperty("repurchase", "y");
        loginCredentials.addProperty("think_price", "e");
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.put("/user/review/646280"); //nubr_Reviewer

        Assert.assertEquals(response.getStatusCode(),200);
    }
}
