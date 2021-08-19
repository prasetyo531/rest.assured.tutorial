package test.omprakash.section15;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostBDD {

    @BeforeMethod
    public void beforeMethod() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("http://api-dev.femaledaily.net/app/v1");
        requestSpecBuilder.addHeader("version","1.5");
        requestSpecBuilder.addHeader("device", "3");
        requestSpecBuilder.addHeader("key", "client03-TSbs94s3q5H9PP2yNPBr");
        requestSpecBuilder.setContentType(ContentType.JSON);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validatePost() {
        String payload = "{\n" +
                "\t\"username\": \"mixrevamp13\",\n" +
                "\t\"password\": \"test1234\",\n" +
                "\t\"is_fb\": false\n" +
                "}";
        given().
                body(payload).
                when().
                post("/login").
                then().
                log().all().
                //https://regex101.com/
                        assertThat().
                body("data.rows.id", equalTo(439643));

    }
}
