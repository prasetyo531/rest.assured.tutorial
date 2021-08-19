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

public class get_newestreview extends base{

    @BeforeMethod
    public void getData() throws IOException, InterruptedException {

    }

    @Test
    public void getnewestreview_web()
    {
        // TODO Auto-generated method stub
        //BaseURL or Host
        RestAssured.baseURI=prop.getProperty("base_url2");

        Response res=given().
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_web")).
                header("Authorization", prop.getProperty("token_web")).
                when().
                get("/newsreviews/1/10").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                header("Server","nginx/1.10.3 (Ubuntu)").log().body().  //log untuk log request
                extract().response();

        String response_name_new = res.path("data.username[0]");
        System.out.println("response_name_new"+" "+response_name_new);

        //result should not null
        Assert.assertNotSame(response_name_new, null);
    }

}
