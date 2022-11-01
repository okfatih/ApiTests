import baseUrl.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import test_data.GoRestTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Get10 extends GoRestBaseUrl {

    @Test
    public void get10() {

        SoftAssert softAssert = new SoftAssert();

        GoRestTestData gorestObje = new GoRestTestData();
        Map<String, String> dataKeyMapi = gorestObje.dataKeyMap("Kanaka Jain", "kanaka_jain@stark.net", "male", "active");
        Map<String, Object> expectedData = gorestObje.expectedDataMethod(null, dataKeyMapi);
        System.out.println("expectedData = " + expectedData);
        spec.pathParams("first", "users", "second", 2986);
        Response response = given().when().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        Map<String,Object> actualdataMap = response.as(HashMap.class);
        System.out.println("actualdataMap = " + actualdataMap);
        softAssert.assertEquals(expectedData.get("meta"),actualdataMap.get("meta"));
        softAssert.assertEquals(dataKeyMapi.get("name"),((Map)actualdataMap.get("data")).get("name"));
        softAssert.assertEquals(dataKeyMapi.get("email"),((Map)actualdataMap.get("data")).get("email"));
        softAssert.assertEquals(dataKeyMapi.get("gender"),((Map)actualdataMap.get("data")).get("gender"));
        softAssert.assertEquals(dataKeyMapi.get("status"),((Map)actualdataMap.get("data")).get("status"));
        softAssert.assertEquals(200,response.getStatusCode());
        softAssert.assertAll();
    }

    /*
    https://gorest.co.in/public/v1/users/2986


    Response Body
   {
    "meta": null,
    "data": {
        "id": 2986,
        "name": "Kanaka Jain",
        "email": "kanaka_jain@stark.net",
        "gender": "male",
        "status": "active"
    }

     */
}
