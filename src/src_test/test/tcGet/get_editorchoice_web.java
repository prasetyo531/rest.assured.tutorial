package test.tcGet;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class get_editorchoice_web extends base.base {

    @BeforeMethod
    public void getData() throws IOException, InterruptedException {

    }

    @Test
    public void geteditorchoice_web() {
        // TODO Auto-generated method stub
        //BaseURL or Host
        RestAssured.baseURI = prop.getProperty("base_url");

        Response res = given().
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_web")).
                header("key", prop.getProperty("key_web")).log().all().
                when().
                get("/products?editor_choice=y").
                then().assertThat().statusCode(200).and().contentType(ContentType.JSON).and().
                header("Server", "nginx/1.10.3 (Ubuntu)").log().body().  //log untuk log request
                extract().response();

        Integer response_commentpost = res.path("data.id[0]");
        System.out.println(response_commentpost);

        //result should not null
        Assert.assertNotSame(response_commentpost, null);

    }

}
