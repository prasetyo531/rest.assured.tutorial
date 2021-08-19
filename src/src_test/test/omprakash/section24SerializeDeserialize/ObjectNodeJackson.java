package test.omprakash.section24SerializeDeserialize;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ObjectNodeJackson {

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
                //expectContentType(ContentType.JSON).
                        log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void objectNodeJackson() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ObjectNode payload = objectMapper.createObjectNode();
        payload.put("username", "vnsphl");
        payload.put("password", "dora12345");
        payload.put("is_fb", "false");

        ObjectNode mainObj = objectMapper.createObjectNode();
        String mainObjSrring = objectMapper.writeValueAsString(mainObj);

        given().
                body(mainObjSrring);
        when().post("/login").
                then().
                log().all();
    }
}
