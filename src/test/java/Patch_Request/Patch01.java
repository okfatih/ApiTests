package Patch_Request;

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

public class Patch01 extends JsonPlaceHolderBaseUrl {
    @Test
    public void test01() {
        //Set the Url
        spec.pathParams("first", "todos", "second", 198);
        //Set the expected Data
        JsonPlaceHolderTestData obj = new JsonPlaceHolderTestData();
        Map<String, Object> expectedData = obj.expectedDataMetjod(null, "Wash the dishes", null);
        System.out.println("expectedData = " + expectedData);
        //Send the Request and Get the Response
        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(expectedData)
                .patch("/{first}/{second}");
        response.prettyPrint();


        //Do Assertion
        Map<String, Object> actualData = response.as(HashMap.class);
        System.out.println("actualData = " + actualData);
        SoftAssert softAssert = new SoftAssert();
        assertEquals(200, response.getStatusCode());
        assertEquals(expectedData.get("title"), actualData.get("title"));
        softAssert.assertAll();


    }
}
 /*
    Given
     1) https://jsonplaceholder.typicode.com/todos/198
     2) {
             "title": "Wash the dishes"
           }
    When
I send PATCH Request to the Url
 Then
      Status code is 200
      And response body is like   {
                   "userId": 10,
                   "title": "Wash the dishes",
                   "completed": true,
                   "id": 198
                   }
 */