package test.omprakash;

import base.base;
import io.restassured.config.LogConfig;
import io.restassured.http.ContentType;
import org.junit.Test;
import org.testng.annotations.BeforeMethod;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;

public class BlacklistHeader extends base {

    @BeforeMethod
    public void getData() throws IOException, InterruptedException {

    }

    @Test
    public void getblogarticle() {
        // TODO Auto-generated method stub
        //BaseURL or Host
//        RestAssured.baseURI = prop.getProperty("base_url");

        Set<String> headers = new HashSet<String>();
                    headers.add("date");
                    headers.add("server");

        given().
                baseUri("http://sdasdasd/baseurl").
                config(config.logConfig(LogConfig.logConfig().blacklistHeaders(headers))).
                log().headers().
                    header("version", prop.getProperty("api_version")).
                    header("device", prop.getProperty("device_web")).
                    header("Authorization", prop.getProperty("token_web")).
                when().
                    get("/blog/article").
                then().
                    assertThat().
                    statusCode(200).and().
                    contentType(ContentType.JSON).and().
                    body("data.title", hasItems("testing meta data")).
                    header("Server", "istio-envoy").log().body().  //log untuk log request
                    extract().response();
    }
}
