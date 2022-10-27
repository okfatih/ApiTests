import baseUrl.RestfulBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Get06 extends RestfulBaseUrl {
    @Test
    public void get01() {
        spec.pathParams("first","booking","second",2325);

        //2 Set the Expected Data

        //3 Send the Request and get the response
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();
        //Do Assertion
        response
                .then()
                .assertThat().statusCode(200)
                .contentType(ContentType.JSON)
                .body("firstname",equalTo("Bradley"),
                        "lastname",equalTo("Pearson"),
                        "totalprice",equalTo(132),
                        "depositpaid",equalTo(false),
                        "bookingdates.checkin",equalTo("2022-10-27"),
                        "bookingdates.checkout",equalTo("2022-11-07"),
        "additionalneeds",equalTo("None"));

        //2. yol JasonPath classının kullanımı
        JsonPath json = response.jsonPath();
        assertEquals("Bradley",json.getString("firstname"));
        assertEquals("Pearson",json.getString("lastname"));
        assertEquals(132,json.getInt("totalprice"));
        assertFalse(json.getBoolean("depositpaid"));
        assertEquals("2022-10-27",json.getString("bookingdates.checkin"));
        assertEquals("2022-11-07",json.getString("bookingdates.checkout"));
        assertEquals("None",json.getString("additionalneeds"));

    //3. yol  Soft Assertion
        //soft assertion classını kullanmak için 3 adımda kullanılır
        //1. adım obje oluşturma
        SoftAssert softAssert = new SoftAssert();
        // Do Assertion
        softAssert.assertEquals(json.getString("firstname"),"Bradley","First Name Hatali");
        softAssert.assertEquals(json.getString("lastname"),"Pearson","Last Name incorrect");
        softAssert.assertEquals(json.getInt("totalprice"),132,"Total price hatalı");
        softAssert.assertEquals(json.getBoolean("depositpaid"),false,"deposit paid hatali");
        softAssert.assertEquals(json.getString("bookingdates.checkin"),"2022-10-27","Checkin date wrong");
        softAssert.assertEquals(json.getString("bookingdates.checkout"),"2022-11-07","Checkout hatali");
        softAssert.assertEquals(json.getString("additionalneeds"),"None","Additionalneeds Hatali");
        softAssert.assertAll();

    }
}
/*
       Given
           https://restful-booker.herokuapp.com/booking/2325
       When
           User send a GET request to the URL
       Then
           HTTP Status Code should be 200
       And
           Response content type is "application/json"
       And
           Response body should be like;
        {
          "firstname": "Howard",
       "lastname": "Colque",
       "totalprice": 111,
          "depositpaid": true,
          "bookingdates": {
       "checkin": "2018-01-01",
       "checkout": "2019-01-01"
   },
    "additionalneeds": "none"
}
    */