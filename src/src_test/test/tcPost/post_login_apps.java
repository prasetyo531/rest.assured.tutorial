package test.tcPost;

import base.base;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class post_login_apps extends base {

    //asu
    @Test(invocationCount = 5)
    public void runpostlogin_apps() {
        RestAssured.baseURI = prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_android")).
                header("key", prop.getProperty("key_android")).
                header("Authorization", prop.getProperty("token_android")).
                log().all();

        // Create new JSON Object
        JsonObject payload = new JsonObject();
        payload.addProperty("username", "putwid");
        payload.addProperty("password", "tester123");
        httpRequest.body(payload.toString());

        Response response = httpRequest.post("/login");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();
        System.out.println("response is" + (body).prettyPrint());

        Assert.assertEquals(response.getStatusCode(),200);

        String response_token = response.path("data.token");
        Integer response_userapposId = response.path("data.rows.id");
        System.out.println(response_token);
        System.out.println(response_userapposId);

        //Assert.assertNotNull(response_token);
        Assert.assertNotSame(response_token,null);
        Assert.assertNotSame(response_userapposId,null);
    }

}
