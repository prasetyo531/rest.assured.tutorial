package test.omprakash;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class ExtractResponse {

    @Test
    public void extractResponse(){
        Response res = (Response) given().
                baseUri("http://api-dev.femaledaily.net/app/v1").
                header("Server", "istio-envoy").
                when().
                get("/blog/article").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body("data.title", hasItems("testing meta data")).
                extract().response(); //extract().response() should located at the end

        System.out.println(res.asString());
    }
}
