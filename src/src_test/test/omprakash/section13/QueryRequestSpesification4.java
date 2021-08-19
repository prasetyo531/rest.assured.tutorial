package test.omprakash.section13;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.QueryableRequestSpecification;
import io.restassured.specification.SpecificationQuerier;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class QueryRequestSpesification4 {

    @BeforeMethod
    public void beforeMethod() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
        requestSpecBuilder.setBaseUri("http://api-dev.femaledaily.net/app/v1/product/list/0?sort=popular&q=vera&next_id=1");
        requestSpecBuilder.addHeader("version","1.5");
        requestSpecBuilder.addHeader("device", "3");
        requestSpecBuilder.addHeader("key", "client03-TSbs94s3q5H9PP2yNPBr");

        RestAssured.requestSpecification = requestSpecBuilder.build();
    }

    @Test
    public void validateUrl() {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(RestAssured.requestSpecification);
        System.out.println(queryableRequestSpecification.getBaseUri());
    }

    @Test
    public void validateHeaders() {
        QueryableRequestSpecification queryableRequestSpecification = SpecificationQuerier.query(RestAssured.requestSpecification);
        System.out.println(queryableRequestSpecification.getHeaders());
    }
}
