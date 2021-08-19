package test.mixmatch;

import base.base;
import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class postQueryParameter extends base {

    @Test
    public void postqueryparameter(){

        RestAssured.baseURI = prop.getProperty("base_url_v2");
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", prop.getProperty("api_version")).
                header("device", prop.getProperty("device_android")).
                queryParam("testing_token","530ec607b1e73e922f3767aa2ca64f05b5a1dc31").
                header("key", prop.getProperty("key_android")).log().all();

        JsonObject loginBody = new JsonObject();
        loginBody.addProperty("auth_name", "6281284915951");
        loginBody.addProperty("channel", "sms");
        loginBody.addProperty("verification_code", "111111");
        httpRequest.body(loginBody.toString());
        Response response = httpRequest.post("/login/verify_code");

        // Retrieve the body of the Response
        ResponseBody body = response.getBody();
        String responsetext = body.prettyPrint();
        System.out.println("response is" + responsetext);

        Assert.assertEquals(response.getStatusCode(),200);

        //udemy rest automation api testing course 33
        JsonPath js = new JsonPath(responsetext);
        Integer iduser = js.getInt("data.rows.id");
        System.out.println("user id"+" "+iduser);


    }

    @DataProvider
    public static Object[][] dataProviderApi(){

        //initialize
        Object[][] data = new Object[3][0];

        //kiri for numbers of times testcase must execute
        //kanan for no parameter you send
        //data = new Object[1][3];

        data[0][0]= "webselenium1@test.com";
        data[1][0]= "webselenium1";
        data[2][0]= "test123";

        return data;
    }
}
