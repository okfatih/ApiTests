import baseUrl.RestfulBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get05 extends RestfulBaseUrl {
    @Test
    public void get01() {

        // https://restful-booker.herokuapp.com/booking?firstname=Ali&lastname=Cengiz
        // 1. Set The URL
        spec.pathParam("first","booking").queryParams("firstname","Jim","lastname","Brown");

        // 2. Set The Expected Data

        // 3. Send The Request And Get The Response
        Response response=given().spec(spec).when().get("/{first}");
        response.prettyPrint();

        // 4. Do Assertion
        assertEquals(200,response.getStatusCode());
        assertTrue(response.asString().contains("bookingid"));

    }
}
/*
    Given
        https://restful-booker.herokuapp.com/booking
    When
        User sends get request to the URL
    Then
        Status code is 200
And
   Among the data there should be someone whose firstname is "Johhny" and lastname is "Dear"
 */