package test.omprakash;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class HasItemsTutor {

    @Test
    public void HasitemTutor(){
        given().
                baseUri("http://api-dev.femaledaily.net/app/v1").
                header("Server", "istio-envoy").
                when().
                get("/blog/article").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body("data.title", hasItems("testing meta data"));
    }
}
