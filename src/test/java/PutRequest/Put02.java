package PutRequest;

import Pojos.DummyDataPojo;
import Pojos.DummyResponsePojo;
import Utils.ObjectMapperUtils;
import baseUrl.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class Put02 extends DummyBaseUrl {



    @Test
    public void test01() {
        //Given URL: https://dummy.restapiexample.com/api/v1/update/21
        /*
          Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
         */
        spec.pathParams("first","update","second",21);
        DummyDataPojo dummyDataPojo = new DummyDataPojo("Ali Can",11111,23,"PerfectImage");
        DummyResponsePojo expectedData = new DummyResponsePojo("success",dummyDataPojo,"Successfully! Record has been updated");


        Response response = given()
                .spec(spec)
                .contentType(ContentType.JSON)
                .body(dummyDataPojo)
                .post("/{first}/{second}");
        response.prettyPrint();
    DummyResponsePojo actualData = ObjectMapperUtils.convertJsontoJava(response.asString(),DummyResponsePojo.class);
        System.out.println("actualData = " + actualData);
        assertEquals(200,response.getStatusCode());
        assertEquals(expectedData.getStatus(),actualData.getStatus());
        assertEquals(expectedData.getData().getEmployee_name(),actualData.getData().getEmployee_age());


    }


    //When User sends put request

    //Then


}
/*
URL: https://dummy.restapiexample.com/api/v1/update/21
       HTTP Request Method: PUT Request
       Request body: {
                        "employee_name": "Ali Can",
                        "employee_salary": 111111,
                        "employee_age": 23,
                        "profile_image": "Perfect image"
                     }
       Test Case: Type by using Gherkin Language
       Assert:
                i) Status code is 200
                ii) Response body should be like the following
                    {
                        "status": "success",
                        "data": {
                            "employee_name": "Ali Can",
                            "employee_salary": 111111,
                            "employee_age": 23,
                            "profile_image": "Perfect image"
                        },
                        "message": "Successfully! Record has been updated."
                    }
     */
