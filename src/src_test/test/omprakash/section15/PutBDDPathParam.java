package test.omprakash.section15;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PutBDDPathParam {

    @BeforeMethod
    public void beforeMethod() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("http://api-dev.femaledaily.net/app/v1");
        requestSpecBuilder.addHeader("version","1.5");
        requestSpecBuilder.addHeader("device", "3");
        requestSpecBuilder.addHeader("key", "client03-TSbs94s3q5H9PP2yNPBr");
        requestSpecBuilder.addHeader("token", "5da6e16403bdfdf1baf1c6c6291386a6");
        requestSpecBuilder.setContentType(ContentType.JSON);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validatePut() {
        String idbanner = "39b00e65-f5f4-41c0-ac0f-2b9f01c2d193";
        String payload = "{\n" +
                "  \"description\": \"FD Babes Fav homepage prasetyo\",\n" +
                "  \"device\": \"android, ios, web\",\n" +
                "  \"end_date\": \"2021-07-26\",\n" +
                "  \"image\": \"https://s3-ap-southeast-1.amazonaws.com/assets.femaledaily.com/banner/FD_Babes_Fav.png\",\n" +
                "  \"name\": \"FD Babes Fav\",\n" +
                "  \"placement\": \"Betty\",\n" +
                "  \"start_date\": \"2020-07-26\",\n" +
                "  \"status\": \"active\",\n" +
                "  \"url\": \"https://www.instagram.com/femaledailynetwork/\"\n" +
                "}";
        given().
                body(payload).
                pathParam("idbanner", idbanner).
        when().
                put("/image/banner/admin/{idbanner}").
        then().
                log().all().extract().response();
                //https://regex101.com/
//                assertThat().
//                body("data.rows.id", equalTo(439643));

    }
}
