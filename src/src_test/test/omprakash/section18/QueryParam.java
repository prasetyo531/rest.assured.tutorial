package test.omprakash.section18;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class QueryParam {

    //responseSpecification
    ResponseSpecification responseSpecification;

    @BeforeMethod
    public void beforeMethod() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://5053ffd6-f4c6-47c8-81be-af1340755720.mock.pstmn.io");

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void single_query_parameter(){
        given().
                baseUri("https://postman-echo.com").
                        queryParam("foo2", "bar2").
                log().all().
                when().
                get("/get").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void multi_value_query_parameter(){
        given().
                baseUri("https://postman-echo.com").
                queryParam("foo1", "bar1;bar2;bar3").
                log().all().
                when().
                get("/get").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void path_parameter(){
        given().
                baseUri("https://reqres.in").
                pathParam("userId", "2").
                log().all().
                when().
                get("/api/users/{userId}").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }
}
