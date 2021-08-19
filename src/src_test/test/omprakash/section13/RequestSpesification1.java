package test.omprakash.section13;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.core.IsEqual.equalTo;

public class RequestSpesification1 {

    //requestSpecification
    RequestSpecification requestSpecification;

    @BeforeMethod
    public void beforeMethod() {
        requestSpecification = given().
                baseUri("http://api-dev.femaledaily.net/app/v1").
                header("version", "1.5").
                header("device", "3").
                header("key", "client03-TSbs94s3q5H9PP2yNPBr").log().all();
    }

    @Test
    public void validateStatusCode() {
        Response response = requestSpecification.get("/product/list/0?sort=popular&q=vera&next_id=1").then().log().all().extract().response();
        assertThat(response.statusCode(), equalTo(200));
    }

    @Test
    public void validateResponseBody() {
        Response response = requestSpecification.get("/product/list/0?sort=popular&q=vera&next_id=1").then().log().all().extract().response();
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.path("data[0].id").toString(), equalTo("10337"));
    }
}
