import Pojos.DummyDataPojo;
import Pojos.DummyResponsePojo;
import Utils.ObjectMapperUtils;
import baseUrl.DummyBaseUrl;
import baseUrl.GmiBankBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Get17 extends DummyBaseUrl {
    @Test
    public void test01() {
        //Set the Url:     URL: https://dummy.restapiexample.com/api/v1/employee/1
        spec.pathParams("first", "employee", "second", 1);

        Response response = given()
                .when()
                .spec(spec)
                .get("/{first}/{second}");
        response.prettyPrint();
        DummyDataPojo dummyDataPojo = new DummyDataPojo("Tiger Nixon", 320800, 61, "");
        DummyResponsePojo expectedData = new DummyResponsePojo("success", dummyDataPojo, "Successfully! Record has been fetched.");

        DummyResponsePojo actualData = ObjectMapperUtils.convertJsontoJava(response.asString(), DummyResponsePojo.class);
        assertEquals(expectedData.getMessage(),actualData.getMessage());

        assertEquals(expectedData.getData().getEmployee_age(), actualData.getData().getEmployee_age());
        assertEquals(expectedData.getData().getEmployee_name(), actualData.getData().getEmployee_name());

    }
    /*
    {
    "status": "success",
    "data": {
        "id": 1,
        "employee_name": "Tiger Nixon",
        "employee_salary": 320800,
        "employee_age": 61,
        "profile_image": ""
    },
    "message": "Successfully! Record has been fetched."
     */
}
/*
Given
        URL: https://dummy.restapiexample.com/api/v1/employee/1
    When
        User sends GET Request
    Then
        Status code is 200
    And
        "employee_name" is "Tiger Nixon"
    And
        "employee_salary" is 320800
    And
        "employee_age" is 61
    And
        "status" is "success"
    And
        "message" is "Successfully! Record has been fetched."
     */
