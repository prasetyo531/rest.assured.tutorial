package test.sectonInterview;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.util.List;

public class ResponseBasic {

    // https://toolsqa.com/rest-assured/deserialize-json-array-to-an-array/

    @Test
    public void checkTipeData() throws MalformedURLException {
        RestAssured.baseURI = "https://jsonplaceholder.cypress.io";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.get("/posts");

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        // Read all the books as a List of String. Each item in the list
        // represent a book node in the REST service Response
        List<Integer> allUserId = jsonPathEvaluator.getList("userId");
        List<Integer> allId = jsonPathEvaluator.getList("id");
        List<String> allTitle = jsonPathEvaluator.getList("title");
        List<String> allBody = jsonPathEvaluator.getList("body");

        // Iterate over the list and print individual book item
        for(Integer userId : allUserId) {
            System.out.println("userId: " + userId);
        }
        for(Integer id : allId) {
            System.out.println("id: " + id);
        }
        for(String title : allTitle) {
            System.out.println("title: " + title);
        }
        for(String body : allBody) {
            System.out.println("body: " + body);
        }
    }
}
