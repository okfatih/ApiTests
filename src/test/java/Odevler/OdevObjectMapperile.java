package Odevler;


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

public class OdevObjectMapperile extends ReqresBaseUrl {
    @Test
    public void test01() throws IOException {
        spec.pathParams("first", "users");

        //expectedData olustur
        ReqresTestData req = new ReqresTestData();
        String stringInExpected = req.expectedDataInString("morpehus", "avatar");
        System.out.println("stringInExpected = " + stringInExpected);
        HashMap expectedData = new ObjectMapper().readValue(stringInExpected, HashMap.class);
        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(expectedData)
                .when()
                .post("/{first}");
        response.prettyPrint();
        HashMap actualData = new ObjectMapper().readValue(response.asString(), HashMap.class);
        assertEquals(201, response.getStatusCode());
        assertEquals(expectedData.get("name"), actualData.get("name"));
        assertEquals(expectedData.get("job"), actualData.get("job"));

    }
}
