package test.tcGet;

import base.base;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class get_userfeed extends base {

    @BeforeMethod
    public void getData() throws IOException, InterruptedException {

    }

    @Test
    public void getuserfeed()
    {
        // TODO Auto-generated method stub
        //BaseURL or Host
        RestAssured.baseURI=prop.getProperty("base_url");

        Response res=given().
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_web")).
                header("Authorization", prop.getProperty("token_web")).
                when().
                get("/user/feed/49055"). //putwid
                then().
                assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                header("Server","nginx/1.10.3 (Ubuntu)").log().body().  //log untuk log request
                extract().response();

        //using tc_post id that have tagged product
        String response_created_date = res.path("data[0].created_at");
        System.out.println(response_created_date);

        //result should not null

        //kena ubah
//        Assert.assertNotSame(response_created_date,null);

    }

}
