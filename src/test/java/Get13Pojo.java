import Pojos.GoRestDataPojo;
import Pojos.GoRestPojo;
import baseUrl.GoRestBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get13Pojo extends GoRestBaseUrl {
    SoftAssert softAssert = new SoftAssert();
    @Test
    public void get13Pojo() {
        spec.pathParams("first","users","second",2508);

       //Set the expected data
        GoRestDataPojo goRestDataPojo = new GoRestDataPojo(2508,"Sharmila Desphande VM","deshpande_sharmila_vm@becker.name","female","active");
        System.out.println(goRestDataPojo);
        GoRestPojo expectedData = new GoRestPojo(null,goRestDataPojo);
        System.out.println(expectedData);
        //Send the get request to the url
        Response response = given().spec(spec).when().get("/{first}/{second}");
        response.prettyPrint();

        GoRestPojo actualData = response.as(GoRestPojo.class);
        System.out.println(actualData);
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getMeta(),actualData.getMeta());
        assertEquals(goRestDataPojo.getId(),actualData.getData().getId());
        assertEquals(goRestDataPojo.getEmail(),actualData.getData().getEmail());
        assertEquals(goRestDataPojo.getGender(),actualData.getData().getGender());
        assertEquals(goRestDataPojo.getStatus(),actualData.getData().getStatus());
        softAssert.assertAll();

    }
}


/*
        Given
            https://gorest.co.in/public/v1/users/2508
        When
            User send GET Request to the URL
        Then
            Status Code should be 200
        And
            Response body should be like
          {
            "meta": null,
            "data": {
                "id": 2508,
                "name": "Sharmila Deshpande VM",
                "email": "deshpande_sharmila_vm@becker.name",
                "gender": "female",
                "status": "active"
                 }
          }
    */