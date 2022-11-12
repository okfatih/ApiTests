package Odevler;

import Pojos.ReqresPojo;
import baseUrl.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import test_data.ReqresTestData;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Odev4Patch extends ReqresBaseUrl {
    @Test
    public void patchR() throws IOException {
        spec.pathParams("first", "users", "second", 2);
        ReqresTestData obj = new ReqresTestData();
        Map<String, Object> expecteddata = obj.dataKeyMapMethod("fatih", null);
        ReqresPojo reqresPojo = new ReqresPojo("Fatih", null);
        System.out.println("expecteddata = " + expecteddata);
        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(expecteddata)
                .patch("/{first}/{second}");
        response.prettyPrint();



        HashMap actualData = response.as(HashMap.class);
        assertEquals(200, response.statusCode());
        assertEquals(actualData.get("name"), expecteddata.get("name"));

        //Objectmapper ile

        String jsoninString = obj.expectedDataInString("Fatih", null);
        HashMap expectedDataObjectMapper = new ObjectMapper().readValue(jsoninString, HashMap.class);


    }
}
//4: Map ile ve Pojo Class ile ayrı ayrı Object Mapper kullanarak yapınız.
  /*
       Given
           1) https://reqres.in/api/users/2
           2) {
                "name": "neo"
               }
       When
            I send PATCH Request to the Url
       Then
             Status code is 200
             And response body is like   {
                                                "name": "neo",
                                                "updatedAt": "2022-10-02T12:53:21.675Z"
                                         }
    */
