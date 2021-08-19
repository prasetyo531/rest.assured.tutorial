package test.writeJsonData;

import base.base;
import base.writeJSONResponse;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.Test;

public class writeResponse extends base{

    @Test
    public void getFollowing() {
        // TODO Auto-generated method stub
        //BaseURL or Host
        RestAssured.baseURI = prop.getProperty("base_url");
        RequestSpecification httpRequest = RestAssured.given();

        httpRequest.header("Content-Type", "application/json")
                .header("version", prop.getProperty("api_version"))
                .header("device", prop.getProperty("device_web"))
                .pathParam("userId", "49055")
                .queryParam("limit", 1)
                .log().all();

        Response response = httpRequest.get("/user/follow/ings/{userId}");
        Assert.assertEquals(response.getStatusCode(), 200);

        //this is the key why write json following json format
        String body = response.getBody().prettyPrint();

        JsonPath jsonPathEvaluator = response.jsonPath();

        Integer metaCode = jsonPathEvaluator.get("meta.code");
        String metaMsg = jsonPathEvaluator.get("meta.msg");

        //get current test method
        //https://www.geeksforgeeks.org/get-name-of-current-method-being-executed-in-java/
        String currentMethodName = new Throwable().getStackTrace()[0].getMethodName();

        String jsonData = workingDir+"/src_test/test/jsonData/"+currentMethodName+"-data.json";

        //wrote string json body to file json data
        //http://tutorials.jenkov.com/java-io/filewriter.html
        String status = writeJSONResponse.writeJsonResponse(body, jsonData);
        System.out.println("Write status: "+status);
    }
}
