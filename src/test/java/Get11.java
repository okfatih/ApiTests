import baseUrl.GoRestBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertTrue;

public class Get11 extends GoRestBaseUrl {

    @Test
    public void test11() {
        spec.pathParams("first", "users");
        Response response = given().spec(spec).when().get("/{first}");
        response.prettyPrint();
        //Do Assertion
        response.then().assertThat().body("meta.pagination.limit", equalTo(10),
                "meta.pagination.links.current", equalTo("https://gorest.co.in/public/v1/users?page=1"), "" +
                        "data", hasSize(10), "data.status", hasItem("active"),
                "data.name", hasItems("Pres. Amarnath Dhawan", "Sujata Chaturvedi", "Navin Panicker"));

        JsonPath jsonPath = response.jsonPath();
        //Kadın ve erkek sayılarını karşılaştıralım 1
        // 1.yol
        List<String> genders = response.jsonPath().getList("data.gender");
        int kadinSayisi = 0;
        for (String w :genders) {
            if (w.equalsIgnoreCase("female")){
                kadinSayisi++;
            }



        }
        assertTrue(kadinSayisi<=genders.size()-kadinSayisi);
        //2 yol groovy ile
        List<String>femalenames =    response.jsonPath().getList("data.findAll{it.gender=='female'}.name");
        System.out.println("femalenames = " + femalenames);

    }
}


/*
    Given
        https://gorest.co.in/public/v1/users
    When
        User send GET Request
    Then
        The value of "pagination limit" is 10
    And
        The "current link" should be "https://gorest.co.in/public/v1/users?page=1"
    And
        The number of users should  be 10
    And
        We have at least one "active" status
    And
        Niranjan Gupta, Samir Namboothiri and Gandharva Kaul are among the users
    And
        The female users are less than or equals to male users
 */