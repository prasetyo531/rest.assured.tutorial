package test.omprakash.section17;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class RequestPayLoadComplexJson {

    //responseSpecification
    ResponseSpecification responseSpecification;

    @BeforeMethod
    public void beforeMethod() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("https://5053ffd6-f4c6-47c8-81be-af1340755720.mock.pstmn.io");

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validatePostComplexJson() {

        //1 start from child - array
        List<Integer> idArrayList = new ArrayList<Integer>();
        idArrayList.add(5);
        idArrayList.add(9);

        //2 second from, param object from previous collection list
        HashMap<String, Object> batterPathHashMap2 = new HashMap<String, Object>();
        batterPathHashMap2.put("id", idArrayList);
        batterPathHashMap2.put("type", "Chocolate");

        //parameter should object
        HashMap<String, Object> batterPathHashMap1 = new HashMap<String, Object>();
        batterPathHashMap1.put("id", "1001");
        batterPathHashMap1.put("type", "Regular");

        //3
        List<HashMap<String, Object>> batterArrayList = new ArrayList<HashMap<String, Object>>();
        batterArrayList.add(batterPathHashMap1);
        batterArrayList.add(batterPathHashMap2);

        //4
        HashMap<String, Object> battersList = new HashMap<String, Object>();
        battersList.put("batters", batterArrayList);

        List<String> idArrayList2 = new ArrayList<String>();
        idArrayList2.add("Glazed");
        idArrayList2.add("Glazed2");

        HashMap<String, Object> toppingPathHashMap2 = new HashMap<String, Object>();
        toppingPathHashMap2.put("id", "5002");
        toppingPathHashMap2.put("type", idArrayList2);

        HashMap<String, Object> toppingPathHashMap1 = new HashMap<String, Object>();
        toppingPathHashMap1.put("id", "5001");
        toppingPathHashMap1.put("type", "None");

        List<HashMap<String, Object>> toppingArrayList = new ArrayList<HashMap<String, Object>>();
        toppingArrayList.add(toppingPathHashMap1);
        toppingArrayList.add(toppingPathHashMap2);

        HashMap<String, Object> completepayload = new HashMap<String, Object>();
        completepayload.put("id", "0005");
        completepayload.put("type", "donut");
        completepayload.put("name", "Cake");
        completepayload.put("ppu", "0.55");
        completepayload.put("batters", batterArrayList);
        completepayload.put("topping", toppingArrayList);

        given().
                body(completepayload).
                when().
                post("/post").
                then().spec(responseSpecification).
                assertThat().
                body("msg", equalTo("successfull"));
    }
}
