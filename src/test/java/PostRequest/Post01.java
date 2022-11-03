package PostRequest;

import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import test_data.JspnPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post01 extends JsonPlaceHolderBaseUrl {
    @Test
    public void post1() {
        spec.pathParam("first", "todos");
        //Set the expected data
        JspnPlaceHolderTestData jsonPlace = new JspnPlaceHolderTestData();
        Map<String, Object> expectedData = jsonPlace.expectedDataMetjod(55, "Tidy your room", false);
        //Send the Request and Get the response
        Response response = given()
                .spec(spec).contentType(ContentType.JSON)
                .body(expectedData)
                .when()
                .post("/{first}");

        response.prettyPrint();
        Map<String, Object> actualData = response.as(HashMap.class);
        SoftAssert softAssert = new SoftAssert();
        assertEquals(expectedData.get("completed"), actualData.get("completed"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("userId"), actualData.get("userId"));

        softAssert.assertAll();

    }
}
 /*
    Given
      1) https://jsonplaceholder.typicode.com/todos
      2){"userId": 55,
            "title": "Tidy your room",
            "completed": false
            }
   When
       I send POST Request to the Url
   Then
       Status code is 201 {

                         }
   And
       response body is like {
                               "userId": 55,
                               "title": "Tidy your room",
                               "completed": false,
                               "id": 201
                               }
*/