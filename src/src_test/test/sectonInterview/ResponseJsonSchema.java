package test.sectonInterview;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.with;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class ResponseJsonSchema {

    // https://www.swtestacademy.com/json-schema-validation-with-rest-assured/

    @BeforeMethod
    public void beforeMethod () {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://jsonplaceholder.cypress.io");
        requestSpecBuilder.setContentType(ContentType.JSON);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(201).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validatePost () throws IOException {
        String payload = "{\n" +
                "  \"title\": \"delectus aut autem\",\n" +
                "  \"body\": \"motorcycle\",\n" +
                "  \"userId\": 1\n" +
                "}";

        Response response = with().body(payload).post("/posts");
        response.getBody().prettyPrint();

        // put json schema at packeges resources
        response.then().assertThat().body(matchesJsonSchemaInClasspath("posts.json"));

        String title = response.path("title");
        String body = response.path("body");
        Integer userId = response.path("userId");
        Integer id = response.path("id");

        // ObjectMapper instantiation
        ObjectMapper objectMapper = new ObjectMapper();

        // Deserialization into the `Posts` class
        Data data = objectMapper.readValue(payload, Data.class);

        //compare by equals
        System.out.println("\tis id equals?: " + title.equals(data.getTitle()));
        System.out.println("\tis id equals?: " + body.equals(data.getBody()));
        System.out.println("\tis id equals?: " + userId.equals(data.getUserId()));
    }
}
