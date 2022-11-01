import baseUrl.ReqresBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class Get05B extends ReqresBaseUrl {

    @Test
    public void get01() {

        //Get methoudyla çektiğimiz json verisinin body kısmını sorgulama yapıyoruz
        //2 adet testim var birisi klasik body içersine inerek
        // diğeri ise softassert yöntemiyle jsonpath.get methodu kullanarak
        spec.pathParams("first","users","second",3);
        Response response = given()
                            .spec(spec)
                .get("/{first}/{second}");
        response.prettyPrint();

        response.then().contentType(ContentType.JSON)
                .body("data.email",equalTo("emma.wong@reqres.in"),
                        "data.first_name",equalTo("Emma"),
                        "data.last_name",equalTo("Wong"),
                        "support.text",equalTo("To keep ReqRes free, contributions towards server costs are appreciated!")); //body ' e girip assertion yapıyorum

    }

    @Test
    public void get02() {
        spec.pathParams("first","unknown","second",3);
        Response response = given().spec(spec)
                .get("/{first}/{second}");
        response.prettyPrint();
        JsonPath jsonPath = response.jsonPath();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(jsonPath.getInt("data.id"),3,"data id not 3");
        softAssert.assertEquals(jsonPath.getString("support.text"),"To keep ReqRes free, contributions towards server costs are appreciated!","text not match");
        softAssert.assertEquals(jsonPath.getString("data.name"),"true red","name not matchin");
        softAssert.assertEquals(jsonPath.getInt("data.year"),2002,"year not 2000");
        softAssert.assertEquals(jsonPath.getString("data.color"),"#BF1932","color not match");
        softAssert.assertEquals(jsonPath.getString("data.pantone_value"),"19-1664", "pantone value not matching");
        softAssert.assertAll();
        response.then().body("data.id",equalTo(3));

    }
}
