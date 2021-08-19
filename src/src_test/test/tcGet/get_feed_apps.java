package test.tcGet;

import base.base;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class get_feed_apps extends base {

    public static String token;

    //asu
    @Test
    public void runpostlogin_android() {
        RestAssured.baseURI = prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_android")).
                header("key", prop.getProperty("key_android")).log().all();

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("username", "putwid");
        loginCredentials.addProperty("password", "tester123");
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.post("/login");

        Assert.assertEquals(response.getStatusCode(),200);

        token = response.path("data.token");
        System.out.println("ini token nya"+" "+token);
//        Assert.assertNotNull(response_token);
        Assert.assertNotSame(token,null);
    }

    @Test(dependsOnMethods = "runpostlogin_android")
    public void getfeed_android() {
        // TODO Auto-generated method stub
        //BaseURL or Host
        RestAssured.baseURI=prop.getProperty("base_url");

        Response res=given().
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_android")).
                header("key", prop.getProperty("key_android")).log().all().
                header("Authorization", token).
                when().
                get("/feed/364623").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                header("Server","nginx/1.10.3 (Ubuntu)").log().body().  //log untuk log request
                extract().response();

        String response_caption = res.path("data[0].caption");
        System.out.println(response_caption);

        //result should not null
        Assert.assertNotSame(response_caption,null);

    }

}
