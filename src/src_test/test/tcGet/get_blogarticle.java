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
import static org.hamcrest.Matchers.hasItems;

public class get_blogarticle extends base {

    @BeforeMethod
    public void getData() throws IOException, InterruptedException {

    }

    @Test
    public void getblogarticle() {
        // TODO Auto-generated method stub
        //BaseURL or Host
        RestAssured.baseURI = prop.getProperty("base_url");

        Response res = given().
                    header("version", prop.getProperty("api_version")).
                    header("device", prop.getProperty("device_web")).
                    header("Authorization", prop.getProperty("token_web")).
                when().
                    get("/blog/article").
                then().
                    assertThat().
                    statusCode(200).and().
                    contentType(ContentType.JSON).and().
                    body("data.title", hasItems("testing meta data")).
                    header("Server", "istio-envoy").log().all().  //log untuk log request
                    extract().response();

        String response_article = res.path("data.title[0]");
        System.out.println(response_article);

        //result should not null
        Assert.assertNotSame(response_article, null);

    }
}
