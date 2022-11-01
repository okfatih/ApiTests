import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.junit.Assert.*;
import org.testng.asserts.SoftAssert;
import test_data.JspnPlaceHolderTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Get08 extends JsonPlaceHolderBaseUrl {

    /*
       Bu derste De-serialization ve Serialization konularını işleyecez
       De-Serialization: Json datayı Java objesine çevirme
       Serialization: Java objesini Json formatına çevirme
        */
    @Test
    public void name() {
        SoftAssert softAssert = new SoftAssert();
        spec.pathParams("first", "todos", "second", 2);
        Response response = given().when().spec(spec).get("/{first}/{second}");
        response.prettyPrint();
       //get methoudyla çağırdıgımız datayı javadan bir maple karşılaştırabimek için
        // json objesinin benzeri bir map oluşturduk

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("title", "quis ut nam facilis et offcia qui");
        expectedData.put("completed", false);

        System.out.println("ExpectedData " + expectedData);

        //Api den gelen json formatındaki datayı Java'nın anlayacağı formata
        // çevirelim, yani deserialization yapalım
        Map<String,Object>actualData = response.as(HashMap.class);

        //Assertion yapalım

        softAssert.assertEquals(expectedData.get("userID"),actualData.get("userID"),"userIDs are not same");
        softAssert.assertEquals(expectedData.get("id"),actualData.get("id"));
        softAssert.assertEquals(expectedData.get("title"),actualData.get("title"));
        softAssert.assertEquals(expectedData.get("completed"),actualData.get("completed"));

    }
    @Test
    public void get08(){
        spec.pathParams("first","todos","second",2);
        Response response = given()
                .when()
                .spec(spec)
                .get("/{first}/{second}");
                response.prettyPrint();

        JspnPlaceHolderTestData jspTestData = new JspnPlaceHolderTestData();
        //JsonPlaceHolderTestData dosyasından cagırdımız expectedDataMap
        Map<String, Object>expectedDataMap = jspTestData.expectedDataMetjod(1,"quis ut nam facilis et officia qui",false);

        //Testin başında get methodu ile cagırdımız json datalarının response as ile cevirilmis oldugu actual map
        Map<String, Object>actualDataMap = response.as(HashMap.class);

        System.out.println("actualDataMap = " + actualDataMap);

        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(expectedDataMap.get("userId"),actualDataMap.get("userId"),"userId not match");
        softAssert.assertEquals(expectedDataMap.get("title"),actualDataMap.get("title"));



    }
}
