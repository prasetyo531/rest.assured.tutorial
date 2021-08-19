package test.tcPut;

import base.base;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class put_profilebeauty extends base {

    @BeforeMethod
    public void setUp() throws IOException, InterruptedException {

    }

    @Test
    public void put_profile_info() {
        RestAssured.baseURI=prop.getProperty("base_url");

        given().
                contentType("multipart/form-data").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_web")).
                header("Authorization", prop.getProperty("token_web")).
                multiPart("skin_type_id", "1").
                multiPart("skin_tone_id", "2").
                multiPart("skin_undertone_id", "3").
                multiPart("hairtype_id", "1").
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
