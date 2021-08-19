package test.tcPost;

import base.base;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class post_addproduct extends base {

    @BeforeMethod
    public void getData() throws IOException, InterruptedException {

    }

    /*
    this test only skip insert image
     */
    @Test
    public void run_add_product()
    {
        //Creating Issue/Defect

        RestAssured.baseURI= prop.getProperty("base_url");
        Response res= given().
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_web")).
                header("Authorization", prop.getProperty("token_web")).
                multiPart("brand_id", "").  //empty on purpose
                multiPart("sub_category_id", "65").
                multiPart("name", "qa testapi web").
                multiPart("price", "275000").
                multiPart("currency", "IDR").
//                multiPart("image", new File("/Users/mac/Documents/multimedia/background/Dua-Lipa-HD-Image.jpg")).
                multiPart("desc", "qa test api").

                when().
                post("/user/product").then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                        header("Server","nginx/1.10.3 (Ubuntu)").log().body().  //log untuk log request
                extract().response();

    }

}
