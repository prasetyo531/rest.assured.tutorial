package test.tcGet;

import base.base;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class get_productsearch_apps extends base {

    @BeforeMethod
    public void getData() throws IOException, InterruptedException {


    }

    @Test
    public void getproductsearch_apps() {
        // TODO Auto-generated method stub
        //BaseURL or Host
        RestAssured.baseURI = prop.getProperty("base_url");

        Response res = given().
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_android")).
                header("key", prop.getProperty("key_android")).log().all().
                when().
                get("product/list/0?q=nivea").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                header("Server", "nginx/1.10.3 (Ubuntu)").log().body().  //log untuk log request
                extract().response();

        String response_products = res.path("data.brand_name[0]");
        System.out.println(response_products);

        //result should not null
        Assert.assertNotSame(response_products, null);

    }
}
