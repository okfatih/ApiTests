package Odevler;

import baseUrl.AutomationExerciseBase;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Odev1Listler extends AutomationExerciseBase {
    @Test
    public void odev1() {
        spec.pathParams("first","brandsList");
        Response response = given().spec(spec).when().get("/{first}");

        JsonPath json = response.jsonPath();
        json.prettyPrint();
        response.then().assertThat().statusCode(200);
       List<String>HM = json.getList("brands.findAll{it.brand=='H&M'}");

        System.out.println("HM.size() = " + HM.size());
    }
}
 /*

        List<String> polo=response.jsonPath().getList("brands.findAll{it.brand=='Polo'}");
        System.out.println("Polo=" + polo.size());

        List<String> hm= response.jsonPath().getList("brands.findAll{it.brand=='H&M'}");
        System.out.println("H&M=" + hm.size());

        Given
            https://automationexercise.com/api/brandsList
        When
            User sends a GET Request to the url
        Then
            HTTP Status Code should be 200
        And
            Content Type should be "text/html; charset=utf-8"
        And
            Status Line should be HTTP/1.1 200 OK
        And
             Number of H&M brands must be equal to Polo(H&M marka sayısı Polo marka sayısına eşit olmalı)
//3: Map ile ve Pojo Class ile ayrı ayrı Gson kullanarak yapınız.
/*
        Given
            1) https://reqres.in/api/users/2
            2) {
                "name": "morpheus",
                "job": "zion president"
                }
        When
            I send Put Request to the Url
        Then
            Status code is 200
            And response body should be like {
                                                "name": "morpheus",
                                                "job": "zion president",
                                                "updatedAt": "2022-10-02T11:35:05.693Z"
                                            }
*/
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
//5:
    /*
    https://petstore.swagger.io/ d

  */