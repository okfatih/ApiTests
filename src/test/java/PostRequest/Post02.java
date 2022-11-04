package PostRequest;

import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;
import test_data.RestfulTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post02 extends RestfulBaseUrl {
    @Test
    public void test01() {
        spec.pathParam("first","booking");

        //Set the expected data
        RestfulTestData restful = new RestfulTestData();
        Map<String,String> bookingDatesMap =  restful.bookingDatesMethod("2021-09-09","2021-09-21");
        Map<String,Object>expectedData = restful.expectedDataMethod("John","Doe",11111,true,bookingDatesMap);
        //Set the request and get the response
        Response response = given().spec(spec).contentType(ContentType.JSON).body(expectedData).when().post("/{first}");
        response.prettyPrint();

        //Do Assertion
        Map<String,Object>actualData = response.as(HashMap.class); //De-serialization yaptık
        SoftAssert softAssert = new SoftAssert();

       assertEquals(expectedData.get("firstname"),((Map)actualData.get("booking")).get("firstname"));
       assertEquals(expectedData.get("lastname"),((Map)actualData.get("booking")).get("lastname"));
       assertEquals(expectedData.get("totalprice"),((Map)actualData.get("booking")).get("totalprice"));
       assertEquals(expectedData.get("depositpaid"),((Map)actualData.get("booking")).get("depositpaid"));

       assertEquals(bookingDatesMap.get("checkin"),((Map)((Map) actualData.get("booking")).get("bookingdates")).get("checkin"));
       assertEquals(bookingDatesMap.get("checkout"),((Map)((Map)actualData.get("booking")).get("bookingdates")).get("checkout"));
       softAssert.assertAll();


    }
}

/*
{
        "firstname": "John",
        "lastname": "Doe",
        "totalprice": 11111,
        "depositpaid": true,
        "bookingdates": {
        "checkin": "2021-09-09",
        "checkout": "2021-09-21"
        }

        }

 */