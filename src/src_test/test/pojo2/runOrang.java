package test.pojo2;

import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class runOrang {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        //BaseURL or Host
        RestAssured.baseURI= "http://reqres.in/api";
        RequestSpecification httpRequest = RestAssured.given();

        Response response = httpRequest.get("/users");
        Assert.assertEquals(response.getStatusCode(), 200);

        ResponseBody body = response.getBody();
        System.out.println("response is" + ((ResponseBody) body).prettyPrint());

        //using pojo classes
        //to make content-type as json , parser.json
        data dt = given().expect().defaultParser(Parser.JSON).
                when().
                get("/users").as(data.class);

        System.out.println(dt.getLast_name());
    }
}
