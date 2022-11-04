package PutRequest;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import test_data.JsonPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put01 extends JsonPlaceHolderBaseUrl {
    @Test
    public void put1() {
        spec.pathParams("first", "todos", "second", 98);
        //Set the expected Data
        JsonPlaceHolderTestData jsp = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = jsp.expectedDataMetjod(21, "Wash the dishes", false);
        System.out.println("expectedData = " + expectedData);

        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(expectedData)
                .when().put("/{first}/{second}");
        response.prettyPrint();

        //Do Assertions
        Map<String, Object> actualMap = response.as(HashMap.class);
        SoftAssert softAssert = new SoftAssert();
        assertEquals(expectedData.get("completed"), actualMap.get("completed"));
        assertEquals(expectedData.get("title"), actualMap.get("title"));
        assertEquals(expectedData.get("userId"), actualMap.get("userId"));


    }
}
/*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198
     2) {
             "userId": 21,
             "title": "Wash the dishes",
             "completed": false
           }
    When
I send PUT Request to the Url
 Then
      Status code is 200
      And response body is like   {
                   "userId": 21,
                   "title": "Wash the dishes",
                   "completed": false
                  }
 */