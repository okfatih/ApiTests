package PostRequest;

import Pojos.DummyDataPojo;
import Pojos.DummyResponsePojo;
import Utils.ObjectMapperUtils;
import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Post06 extends DummyBaseUrl {
    @Test
    public void test01() {
        //Set the Url
        spec.pathParam("first", "create");
        DummyDataPojo dummyDataPojo = new DummyDataPojo("Tom Hanks", 1111, 23, "perfect_image");
        DummyResponsePojo expectedData = new DummyResponsePojo("success", dummyDataPojo, "Successfully! Record has been added.");
        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(dummyDataPojo)
                .when()
                .post("/{first}");
        //   response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        jsonPath.prettyPrint();
        DummyResponsePojo actualresponse = response.as(DummyResponsePojo.class);
        System.out.println("actualresponse = " + actualresponse);
        assertEquals(dummyDataPojo.getEmployee_name(), actualresponse.getData().getEmployee_name());
        assertEquals(expectedData.getStatus(), actualresponse.getStatus());
        assertEquals(expectedData.getData().getEmployee_age(), actualresponse.getData().getEmployee_age());


        DummyResponsePojo actualData = ObjectMapperUtils.convertJsontoJava(response.asString(), DummyResponsePojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_name());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getMessage(),actualData.getMessage());
        assertEquals(expectedData.getData().getEmployee_age(),actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getProfile_image(),actualData.getData().getProfile_image());


    }
    /*
    given https://dummy.restapiexample.com/api/v1/create
       {
                        "employee_name": "Tom Hanks",
                         "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image",
                        "id":4891

       }
       When
       user sends post request
       Then
       Status code is 200
       And
       Response body should be like following
      {
                        "status": "success",
                        "data": {
                            "employee_name": "Tom Hanks",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image",
                            "id": 4891
                        },
                        "message": "Successfully! Record has been added."
                    }


     */
}
