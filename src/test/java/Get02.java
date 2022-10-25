import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.*;

public class Get02 {

    @Test
    public void get01() {
        String url = "https://restful-booker.herokuapp.com/booking/1";
        Response response = given().when().get(url);
        response.prettyPrint();
        response.then().assertThat().statusCode(404).statusLine("HTTP/1.1 404 Not Found");
        //Body not found içeriyor mu testi yapıyoruz
       assertTrue(response.asString().contains("Not Found"));
        //Body techproed içerediği testi yapılıyor

       assertFalse(response.asString().contains("TechProEd"));
       //Server ın cowboy olup olmadığı test ediliyor
       assertEquals("Cowboy",response.getHeader("Server"));
    }
}
/* Given
        https://restful-booker.herokuapp.com/booking/1
        When
        User send a GET Request to the url
        Then
        HTTP Status code should be 404
        And
        Status Line should be HTTP/1.1 404 Not Found
        And
        Response body contains "Not Found"
        And
        Response body does not contain "TechProEd"
        And
        Server is "Cowboy"
        */