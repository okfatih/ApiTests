import Pojos.BookingDatesPojo;
import Pojos.BookingPojo;
import baseUrl.RestfulBaseUrl;
import groovy.transform.stc.POJO;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get12Pojo extends RestfulBaseUrl {
    @Test
    public void test01() {
        //Set the Url
        spec.pathParams("first", "booking", "second", 18);


        //Set the expected Data
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2018-01-01", "2019-01-01");
        System.out.println(bookingDatesPojo.toString());

        BookingPojo expectedData = new BookingPojo("Dane", "Combs", 111, true, bookingDatesPojo, "Breakfast");
        System.out.println("Expected Data: " + expectedData.toString());

        //Send the request and get the response
        Response response = given().when().spec(spec).get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        BookingPojo actualData = response.as(BookingPojo.class);
        System.out.println("actualData :  " + actualData);
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getTotalprice(), actualData.getTotalprice());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());

        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());
        //1. 1 Yol
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(), actualData.getBookingdates().getCheckout());

        //2. Yol ==> Tavsiye edilen
        assertEquals(bookingDatesPojo.getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(bookingDatesPojo.getCheckout(), actualData.getBookingdates().getCheckout());
    }
}
/*
     Given
         https://restful-booker.herokuapp.com/booking/18
     When
    I send GET Request to the URL
   Then
    Status code is 200
And
    Response body is like:
                        {
                             "firstname": "omto",
                             "lastname": "nena",
                             "totalprice": 112,
                             "depositpaid": true,
                             "bookingdates": {
                                 "checkin": "2018-01-01",
                                 "checkout": "2019-01-01"
                             },
                             "additionalneeds": "Breakfast"
                         }
  */