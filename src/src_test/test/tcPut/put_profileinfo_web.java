package test.tcPut;

import base.base;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class put_profileinfo_web extends base {

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {

    }

    @Test
    public void putprofileinfo_web() {
        RestAssured.baseURI=prop.getProperty("base_url");

        given().
                contentType("multipart/form-data").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_web")).
                header("Authorization", prop.getProperty("token_put_web")).
                multiPart("fullname", "testweb").
                multiPart("birthday", "17-08-1990").
                multiPart("brands", "65").

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
