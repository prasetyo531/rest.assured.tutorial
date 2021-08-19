package test.deserialize;

import base.base;
import io.restassured.RestAssured;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class listFollow extends base {

    /*
    http://api-dev.femaledaily.net/web/v1/newsreviews/0/5
    https://jsoneditoronline.org/
     */

    /*
    tips create nya
    1. buat getter setter object yg g di dalem nested json
    2. buat getter setter object di dlm nested loop
     */

    public static void main(String[] args) {

        // TODO Auto-generated method stub
        //BaseURL or Host
        RestAssured.baseURI= "http://api-dev.femaledaily.net/app/v1";
        RequestSpecification httpRequest = RestAssured.given();


        //tipe data string
        httpRequest.header("Content-Type", "application/json").
                header("version", "1.5").
                header("device", "3").
                queryParam("limit","1").
                log().all();

        Response response = httpRequest.get("/user/follow/ings/49055");
        Assert.assertEquals(response.getStatusCode(), 200);

        ResponseBody body = response.getBody();
        System.out.println("response is" + ((ResponseBody) body).prettyPrint());

        Integer response_prod = response.path("pagination.limit");
        System.out.println(response_prod);

        //using pojo classes
        //to make content-type as json , parser.json
        Rows gf = given().expect().defaultParser(Parser.JSON).
                when().
                get("/user/follow/ings/49055").as(Rows.class);

        System.out.println(gf.getEmail());

        Rows gx = given().expect().defaultParser(Parser.XML).
                when().
                get("/user/follow/ings/49055").as(Rows.class);

        Boolean a1 = (gf.getEmail()==gx.getEmail());

        if(a1.equals("FALSE")){
            Assert.fail("ya beda");
        }
    }


    //json nested
    //array in json[]
}
