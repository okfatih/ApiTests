import baseUrl.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Get09b extends RestfulBaseUrl {
    @Test
    public void get09b() {

        //Set the expectedData, Asagıda Json ile gelen data var
        // bu datadan Map içersinde Map oluşturabiliriz
        //once bookingdates ile baslayan kucuk map i olusturalim
        Map<String, String> bookingdatesMap = new HashMap<>();
        bookingdatesMap.put("checkin", "2013-02-23");
        bookingdatesMap.put("checkout", "2014-10-23");

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstname", "Sally");
        expectedData.put("lastname", "Brown");
        expectedData.put("totalprice", 111);
        expectedData.put("depositpaid", true);
        expectedData.put("bookingdates", bookingdatesMap);//Key e value olarak map atadık
        expectedData.put("additionalneeds", "Breakfast");

        System.out.println("expectedData = " + expectedData);


        //Send The Request
        spec.pathParams("first", "booking", "second", 91);
        Response response = given()
                .when()
                .spec(spec)
                .get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        Map<String, Object> actualDataMap = response.as(HashMap.class);
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expectedData.get("firstname"),actualDataMap.get("firstname"));
        softAssert.assertEquals(expectedData.get("lastname"),actualDataMap.get("lastname"));
        softAssert.assertEquals(expectedData.get("totalprice"),actualDataMap.get("totalprice"));
        softAssert.assertEquals(expectedData.get("depositpaid"),actualDataMap.get("depositpaid"));
        softAssert.assertEquals(bookingdatesMap.get("checkin"),((Map)actualDataMap.get("bookingdates")).get("checkin"));
        softAssert.assertEquals(bookingdatesMap.get("checkout"),((Map)actualDataMap.get("bookingdates")).get("checkout"));
        /*
        Key value ikilileleri string object şeklinde olduklarından
        bookingdates value kısmını casting ile Map yaptık
         */
        softAssert.assertEquals(expectedData.get("additionalneeds"),actualDataMap.get("additionalneeds"));
softAssert.assertAll();
    }
}
/*
{
    "firstname": "Sally",
    "lastname": "Brown",
    "totalprice": 111,
    "depositpaid": true,
    "bookingdates": {
        "checkin": "2013-02-23",
        "checkout": "2014-10-23"
    },
    "additionalneeds": "Breakfast"
}
 */