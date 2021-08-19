package test.tcPut;

import base.base;
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

public class put_profilecomplete_apps extends base {

    public static String response_token;

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
                header("Authorization", prop.getProperty("token_put_android"));

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("username", "testqa");
        loginCredentials.addProperty("password", "test123");
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.post("/login");

        Assert.assertEquals(response.getStatusCode(),200);

        response_token = response.path("data.token");
        System.out.println(response_token);
//        Assert.assertNotNull(response_token);
        Assert.assertNotSame(response_token,null);
    }

    @Test(priority = 2)
    public void putprofilecomplete_apps() {
        RestAssured.baseURI=prop.getProperty("base_url");

        given().
                contentType("multipart/form-data").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_android")).
                header("Authorization", response_token). //token testqa
                header("userid", "297163").
                header("username", "testqa").
                multiPart("skin_type_id", "3").
                multiPart("skin_tone_id", "6").
                multiPart("skin_undertone_id", "1").
                multiPart("hairtype_id", "2").
                multiPart("birthday", "27-12-1990").
                multiPart("brands", "148").
                multiPart("skin_concerns", "2").
                multiPart("body_concerns", "2,3,4").
                multiPart("hair_concerns", "3,4,5").
                multiPart("hair_texture_id", "2").

                when().
                put("/user/profile").then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).and(). //karna contenttypenya json
                header("Server","nginx/1.10.3 (Ubuntu)").log().body().  //log untuk log request
                extract().response();
//                //ambil dari jsoneditor online
//                        body("data.email",equalTo("fazlur.f.rahman@gmail.com")).and().
//                body("data.username", equalTo("putwid"));
    }
}
