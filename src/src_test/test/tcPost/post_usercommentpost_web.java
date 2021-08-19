package test.tcPost;

import base.base;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

public class post_usercommentpost_web extends base {

    @BeforeMethod
    public void getData() throws IOException, InterruptedException {

    }

    /*
    this test only skip insert image

    check on feed page
    cek table nubr_post
    pk id nubr_post
     */
    @Test
    public void postusercommentpost_web() {

        RestAssured.baseURI = prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json");

        // Create new JSON Object
        JsonObject loginCredentials = new JsonObject();
        loginCredentials.addProperty("exclude", "[12345][45678]");
        loginCredentials.addProperty("page", "1");//empty on purpose
        httpRequest.body(loginCredentials.toString());
        Response response = httpRequest.post("/hot_feed");

        Assert.assertEquals(response.getStatusCode(),200);

        //empty on purpose
//        Boolean response_isfollow = response.path("data.is_follow");
//        System.out.println(response_isfollow);
////        Assert.assertNotNull(response_token);
//        Assert.assertNotSame(response_isfollow,null);
    }
}
