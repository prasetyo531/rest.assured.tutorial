package test.omprakash.section26PracticePras;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.with;

public class Deserialize {

    @BeforeMethod
    public void beforeMethod() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("http://reqres.in/api");
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void getUser() {
        Response response = with().get("/users");
        Assert.assertEquals(response.getStatusCode(), 200);

        ResponseBody body = response.getBody();
        System.out.println("response is" + ((ResponseBody) body).prettyPrint());

        DataRoot dataRoot = given().expect().defaultParser(Parser.JSON).
                when().
                get("/users").as(DataRoot.class);

        String coba = dataRoot.getSupport().getText();
        System.out.println(coba);
    }
}
