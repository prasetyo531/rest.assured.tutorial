package test.tcPost;

import base.base;
import com.github.javafaker.Faker;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class post_register_apps extends base {

    /*
    if not static, variable are not able to use in other class
     */
    public static Integer userId;
    public static String response_username;
    public static String response_token;
    public static String response_msg;

    private Faker faker;

    String password = "test123";

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {
        faker = new Faker();

    }

    @Test(priority = 1)
    public void run_post_register() {

        RestAssured.baseURI= prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_android")).
                header("key", prop.getProperty("key_android")).log().all();

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("username", faker.name().lastName()); //username sengaja kosong
        loginCredentials.addProperty("email", faker.internet().safeEmailAddress());
        loginCredentials.addProperty("password", password);
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.post("/register");

        Assert.assertEquals(response.getStatusCode(),200);

        ResponseBody body = response.getBody();
        String responsetext = body.prettyPrint();
        System.out.println("response is"+" "+responsetext);

        //empty on purpose
        userId = response.path("data.id");
        System.out.println(userId);
        //Assert.assertNotSame(response_body,null);

        response_username = response.path("data.username");
        System.out.println(response_username);
        //Assert.assertNotSame(response_username,null);
    }


    @Test(priority = 2)
    public void run_post_login() {
        RestAssured.baseURI = prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_android")).
                header("key", prop.getProperty("key_android")).log().all();
                //header("Authorization", prop.getProperty("token_web"));

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("username", response_username);
        loginCredentials.addProperty("password", password);
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.post("/login");
        response.toString();

        ResponseBody body = response.getBody();
        String responsetext = body.prettyPrint();
        System.out.println("response is"+" "+responsetext);

        Assert.assertEquals(response.getStatusCode(),200);

        response_token = response.path("data.token");
        System.out.println(response_token);
        //Assert.assertNotNull(response_token);
        Assert.assertNotSame(response_token,null);
    }


    @Test(priority = 3)
    public void getuserprofile_apps() throws InterruptedException {

        //Thread.sleep(5000);

        RestAssured.baseURI = prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_android")).
                header("key", prop.getProperty("key_android")).log().all().
                header("Authorization", response_token);

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("username", response_username);
        loginCredentials.addProperty("password", password);
        httpRequest.body(loginCredentials.toString());
        //https://api.femaledaily.com/app/v1/user/profile/me
        Response response = httpRequest.get("/user/profile/me");

        ResponseBody body = response.getBody();
        String responsetext = body.prettyPrint();
        System.out.println("response is"+" "+responsetext);

        Assert.assertEquals(response.getStatusCode(),200);

        response_msg = response.path("meta.msg");
        System.out.println(response_msg);
        Assert.assertNotSame(response_msg,"An error occured while load profile");


    }
//
//    @Test(priority = 4)
//    public void getuserprofile_web()
//    {
//        // TODO Auto-generated method stub
//        //BaseURL or Host
//        RestAssured.baseURI=prop.getProperty("base_url");
//
//        Response res=given().
//                header("version", prop.getProperty("api_version")).
//                header("device", prop.getProperty("device_android")).
//                header("key", prop.getProperty("key_android")).log().all().
//                header("Authorization", response_token).
//                when().
//                get("/user/profile/"+response_username).
//                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
//                header("Server","istio-envoy").log().body().  //log untuk log request
//                extract().response();
//
//
//    }
}
