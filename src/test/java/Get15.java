import Pojos.BookingDatesPojo;
import Pojos.BookingPojo;
import Utils.ObjectMapperUtils;
import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get15 extends RestfulBaseUrl {
    @Test
    public void getRequest() {
        spec.pathParams("first", "booking", "second", 865);
        BookingDatesPojo bookingDatesPojo = new BookingDatesPojo("2022-01-01", "2023-01-01");
        BookingPojo expectedData = new BookingPojo("James", "Brown", 111, true, bookingDatesPojo, "Breakfast");


        Response response = given()
                .spec(spec)
                .when()
                .get("/{first}/{second}");
        response.prettyPrint();

        //Do Assertion
        BookingPojo actualData = response.as(BookingPojo.class);
        assertEquals(expectedData.getFirstname(), actualData.getFirstname());
        assertEquals(expectedData.getLastname(), actualData.getLastname());
        assertEquals(expectedData.getBookingdates().getCheckin(), actualData.getBookingdates().getCheckin());
        assertEquals(expectedData.getBookingdates().getCheckout(),actualData.getBookingdates().getCheckout());
        assertEquals(expectedData.getDepositpaid(), actualData.getDepositpaid());
        assertEquals(expectedData.getAdditionalneeds(), actualData.getAdditionalneeds());

        BookingPojo actualdata = ObjectMapperUtils.convertJsontoJava(response.asString(), BookingPojo.class);
        assertEquals(200, response.getStatusCode());


    }
}
