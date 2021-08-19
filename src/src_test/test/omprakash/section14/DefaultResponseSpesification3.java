package test.omprakash.section14;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class DefaultResponseSpesification3 {

    @BeforeMethod
    public void beforeMethod() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("http://api-dev.femaledaily.net/app/v1");
        requestSpecBuilder.addHeader("version","1.5");
        requestSpecBuilder.addHeader("device", "3");
        requestSpecBuilder.addHeader("key", "client03-TSbs94s3q5H9PP2yNPBr");

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validateStatusCode() {
      get("/product/list/0?sort=popular&q=vera&next_id=1");
    }

    @Test
    public void validateResponseBody() {
        Response response= get("/product/list/0?sort=popular&q=vera&next_id=1").then().log().all().extract().response();
        assertThat(response.statusCode(), equalTo(200));
        assertThat(response.path("data[0].id").toString(), equalTo("10337"));
    }
}
