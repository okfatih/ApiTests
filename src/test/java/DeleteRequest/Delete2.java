package DeleteRequest;

import Pojos.DummyDeletePojo;
import Utils.ObjectMapperUtils;
import baseUrl.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Delete2 extends DummyBaseUrl {
    @Test
    public void test2() {

        DummyDeletePojo expectedData = new DummyDeletePojo("success", "2", "Successfully! Record has been deleted");

        //Given
        spec.pathParams("first", "delete", "second", 2);
        //When
        Response response = given().when().spec(spec).delete("/{first}/{second}");
        response.prettyPrint();
        DummyDeletePojo actualData = response.as(DummyDeletePojo.class);


        //Then
        assertEquals(expectedData.getData(), actualData.getData());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getMessage(),actualData.getMessage());

        //ObjeMapper ile

      DummyDeletePojo actualData2 =   ObjectMapperUtils.convertJsontoJava(response.asString(),DummyDeletePojo.class);
      assertEquals(actualData2.getStatus(),expectedData.getStatus());
      assertEquals(actualData2.getMessage(),expectedData.getMessage());
      assertEquals(actualData2.getData(),expectedData.getData());


    }
}

/*
        URL: https://dummy.restapiexample.com/api/v1/delete/2
        HTTP Request Method: DELETE Request
        Test Case: Type by using Gherkin Language
        Assert:     i) Status code is 200
        ii) "status" is "success"
        iii) "data" is "2"
        iv) "message" is "Successfully! Record has been deleted"
        */
