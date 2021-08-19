package test.omprakash;

import org.testng.Assert;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class HamcrestAssert {

    @Test
    public void hamcrestAssert(){
        String res = given().
                baseUri("http://api-dev.femaledaily.net/app/v1").
                header("Server", "istio-envoy").
                when().
                get("/blog/article").
                then().
                log().all().
                assertThat().
                statusCode(200).
                extract().
                response().path("data[0].title"); //extract().response() should located at the end

        Assert.assertEquals(res,"testing meta data");
    }
}
