package test.tcPost;

import base.base;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class post_register_web extends base {

    public static String response_username;

    private Faker faker;
    String password = "test123";

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        faker = new Faker();

    }

    @Test(priority = 1, invocationCount = 5)
    public void run_post_register() {

        RestAssured.baseURI= prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_web")).
                header("key", prop.getProperty("key_web")).log().all();

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("username", "");
        loginCredentials.addProperty("email", faker.internet().safeEmailAddress());
        loginCredentials.addProperty("password", password);
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.post("/register");

        Assert.assertEquals(response.getStatusCode(),200);

        //empty on purpose
//        Integer response_body = response.path("data.id");
//        System.out.println(response_body);
//        Assert.assertNotSame(response_body,null);
//
//        response_username = response.path("data.username");
//        System.out.println(response_username);
//        Assert.assertNotSame(response_username,null);
    }

    /*
    @Test(priority = 2)
    public void run_post_login() {
        RestAssured.baseURI = prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_web")).
                header("key", prop.getProperty("key_web")).log().all().
                header("Authorization", prop.getProperty("token_web"));

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("username", response_username);
        loginCredentials.addProperty("password", password);
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.post("/login");

        Assert.assertEquals(response.getStatusCode(),200);

        String response_token = response.path("data.token");
        System.out.println(response_token);
//        Assert.assertNotNull(response_token);
        Assert.assertNotSame(response_token,null);
    }


    @Test(priority = 3)
    public void getuserprofile_web()
    {
        // TODO Auto-generated method stub
        //BaseURL or Host
        RestAssured.baseURI=prop.getProperty("base_url");

        Response res=given().
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_web")).
                header("key", prop.getProperty("key_web")).log().all().
                header("Authorization", prop.getProperty("token_web")).
                when().
                get("/user/profile/"+response_username).
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                header("Server","nginx/1.10.3 (Ubuntu)").log().body().  //log untuk log request
                extract().response();

    }
    **/

    @Test(priority = 2)
    public void getuserprofile_web()
    {
        // TODO Auto-generated method stub
        //BaseURL or Host
        RestAssured.baseURI=prop.getProperty("base_url");

        Response res=given().
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_web")).
                header("key", prop.getProperty("key_web")).log().all().
                header("Authorization", prop.getProperty("token_web")).
                when().
                get("/user/profile/putwid").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                header("Server","nginx/1.10.3 (Ubuntu)").log().body().  //log untuk log request
                extract().response();

    }
}
