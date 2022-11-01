import baseUrl.JsonPlaceHolderBaseUrl;
import baseUrl.RestfulBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static org.junit.Assert.*;

import static io.restassured.RestAssured.*;

public class Get04B extends RestfulBaseUrl {
    @Test
    public void get01() {
        spec.pathParam("first","booking").queryParams("firstname","Blanca","lastname","Nieves");
        Response response = given()
                .spec(spec)
                .get("/{first}");
        response.prettyPrint();
        JsonPath jsonPath =response.jsonPath();
        assertEquals(200,response.getStatusCode());
        assertTrue(response.asString().contains("bookingid"));

    }
}
