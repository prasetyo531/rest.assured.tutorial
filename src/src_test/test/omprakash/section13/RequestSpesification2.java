package test.omprakash.section13;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class RequestSpesification2 {

    //RequestSpecBuilder
    RequestSpecification requestSpecification;

    @BeforeMethod
    public void beforeMethod() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("http://api-dev.femaledaily.net/app/v1");
        requestSpecBuilder.addHeader("version","1.5");
        requestSpecBuilder.addHeader("device", "3");
        requestSpecBuilder.addHeader("key", "client03-TSbs94s3q5H9PP2yNPBr");

        requestSpecification = requestSpecBuilder.build();
    }

    @Test
    public void validateStatusCode() {
        Response response = given(requestSpecification).get("/product/list/0?sort=popular&q=vera&next_id=1").then().log().all().extract().response();
        assertThat(response.statusCode(), equalTo(200));
    }

    @Test
    public void validateResponseBody() {
        Response response = given(requestSpecification).get("/product/list/0?sort=popular&q=vera&next_id=1").then().log().all().extract().response();
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.path("data[0].id").toString(), equalTo("10337"));
    }
}
