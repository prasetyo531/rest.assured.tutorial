package test.tcPost;

import base.base;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class post_userposttagging_apps extends base {

    @BeforeMethod
    public void getData() throws IOException, InterruptedException {


    }

    @Test
    public void postuserposttagging_apps()
    {
        //Creating Issue/Defect

        RestAssured.baseURI= prop.getProperty("base_url");
        Response res= given().
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_android")).
                header("Authorization", prop.getProperty("token_android")).
//                multiPart("image", new File("/Users/mac/Documents/multimedia/background/Dua-Lipa-HD-Image.jpg")).
                    multiPart("caption", "fakkk").
                    multiPart("product_ids", "21809").

        when().
                        post("/user/post").then().
                        assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                        header("Server","nginx/1.10.3 (Ubuntu)").log().body().  //log untuk log request
                extract().response();

    }


}
