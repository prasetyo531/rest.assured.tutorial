package test.payloadXml;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.File;

public class usingXml {

    @Test
    public void passXMLFileAsPayload()
    {
        // Creating a File instance
        File jsonDataInFile = new File("/Users/fdn-prasetyo/Documents/Automation/project.restassured.com/src_test/test/payloadXml/payload.xml");

        //GIVEN
        RestAssured
                .given()
                .baseUri("https://restful-booker.herokuapp.com/auth")
                // Since I am passing payload as xml. Anyway it is optional as Rest Assured automatically identifies.
                .contentType(ContentType.XML)
                .body(jsonDataInFile)
                // WHEN
                .when()
                .post()
                // THEN
                .then()
                .assertThat()
                .statusCode(200);
    }
}
