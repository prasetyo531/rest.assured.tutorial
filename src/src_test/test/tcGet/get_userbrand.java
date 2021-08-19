package test.tcGet;

import base.base;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class get_userbrand extends base {

    @Test
    public void getuserbrand()
    {
        // TODO Auto-generated method stub
        //BaseURL or Host
        RestAssured.baseURI=prop.getProperty("base_url");

        Response res=given().
                when().
                get("/user/brand").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                header("Server","nginx/1.10.3 (Ubuntu)").log().body().  //log untuk log request
                extract().response();

    }

}
