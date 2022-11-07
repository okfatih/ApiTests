package Odevler;

import baseUrl.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.groovy.control.io.ReaderSource;
import org.junit.Test;
import test_data.ReqresTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev2Mapile extends ReqresBaseUrl {
    @Test
    public void test01() {
        spec.pathParams("first","users");
        ReqresTestData reqresTestData = new ReqresTestData();
          Map<String,Object> expectedData =reqresTestData.dataKeyMapMethod("morpheus","avatar");
        Response response = given().spec(spec).contentType(ContentType.JSON)
                .body(expectedData)
                .when()
                .post("/{first}");
        response.prettyPrint();
        Map<String,String>actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        assertEquals(201,response.getStatusCode());
        assertEquals(expectedData.get("name"),actualData.get("name"));
        assertEquals(expectedData.get("job"),actualData.get("job"));



    }
}
//2:  Map ve Pojo Class ile ayrı ayrı yapınız.
/*
        Given
            1) https://reqres.in/api/users
            2) {
                "name": "morpheus",
                "job": "leader"
                }
        When
            I send POST Request to the Url
        Then
            Status code is 201
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "leader",
                                                "id": "496",
                                                "createdAt": "2022-10-04T15:18:56.372Z"


 */