package test.omprakash.section26PracticePost;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import org.testng.ITestContext;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.with;

public class SerializeReview {

    @Test(priority = 1)
    public void validateLogin(ITestContext context) {
        String token;
        RestAssured.baseURI= "http://api-dev.femaledaily.net/app/v1";
        RequestSpecification httpRequest = RestAssured.given();
        httpRequest.header("Content-Type", "application/json").
                header("version", "1.5").
                header("device", "3").
                header("key", "client03-TSbs94s3q5H9PP2yNPBr").log().all();

        String payload = "{\n" +
                "\t\"username\": \"verifyphone02\",\n" +
                "\t\"password\": \"test123\",\n" +
                "\t\"is_fb\": false\n" +
                "}";

        httpRequest.body(payload);
        Response response = httpRequest.post("/login");

        ResponseBody body = response.getBody();
        token= body.path("data.token");
        System.out.println("check token :"+token);
        context.setAttribute("token", token);
    }

    @Test(priority = 2)
    public void validatePost(ITestContext context) {
        String token = (String) context.getAttribute("token");
        RequestSpecBuilder requestSpecBuilder2 = new RequestSpecBuilder();
        requestSpecBuilder2.setBaseUri("http://api-dev.femaledaily.net/app/v1");
        requestSpecBuilder2.addHeader("version", "1.5");
        requestSpecBuilder2.addHeader("device", "3");
        requestSpecBuilder2.addHeader("key", "client03-TSbs94s3q5H9PP2yNPBr");
        requestSpecBuilder2.addHeader("Authorization",token);
        requestSpecBuilder2.setContentType(ContentType.JSON);

        RestAssured.requestSpecification = requestSpecBuilder2.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();

        String payload = "{\n" +
                "\t\"duration_of_use\": 2,\n" +
                "\t\"is_firsttime_use\": \"y\",\n" +
                "\t\"is_recommended\": \"n\",\n" +
                "\t\"place_to_get\": {\n" +
                "\t\t\"place\": \"Offline\",\n" +
                "    \t\"sub\": {\n" +
                "    \t\"place\": \"JakartaXBeauty\",\n" +
                "    \t\"sub\": {}\n" +
                "    \t}\n" +
                "\t},\n" +
                "    \"product_id\": \"699\",\n" +
                "    \"rating\": 4.8,\n" +
                "    \"text\": \"test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review test review \",\n" +
                "    \"package_quality\": 4.6,\n" +
                "    \"repurchase\": \"y\",\n" +
                "    \"think_price\": \"e\"\n" +
                "}";

        Response response = with().body(payload).post("/user/review");
        response.getBody().prettyPrint();

        ResponseBody body = response.getBody();
        System.out.println(body);
    }
}
