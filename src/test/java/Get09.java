import baseUrl.AutomationExerciseBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.SoftAssert;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Get09 extends AutomationExerciseBase {

    @Test
    public void test09() {

        spec.pathParams("first", "api", "second", "productsList");
        Response response = given().spec(spec).when().get("/{first}/{second}");


        JsonPath jsonPath = response.jsonPath();
        // jsonPath.prettyPrint();
        SoftAssert softAssert = new SoftAssert();
        softAssert.assertEquals(200, response.getStatusCode(), "status code not 200");

        System.out.println(jsonPath.getList("products.id"));
        List<String> ladies = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");


        List<String> gentelmen = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
        List<String> kids = jsonPath.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");

        List<String>polocat = jsonPath.getList("products.findAll{it.brand=='Polo'}.category.category");//Markası Polo olanların categorileri
        System.out.println("polocat = " + polocat);
        List<String>allprices = jsonPath.getList("products.price");
        List<String>HM = jsonPath.getList("products.findAll{it.brand=='H&M'}.brand");//Markası HM olanlar

        List<String>HMprices = jsonPath.getList("products.findAll{it.brand=='H&M'}.price");//Markası HM olanların priceları
        System.out.println("HMprices = " + HMprices);

        List<String>Hmsex= jsonPath.getList("products.findAll{it.brand=='H&M'}.category.usertype");//Amelece yazılmış markası HM olanları tercih eden cinsiyet
        System.out.println("Hmsex = " + Hmsex);

        List<Integer>poloIds = jsonPath.getList("products.findAll{it.brand=='Polo'}.id");//Markası Polo olanların idleri
        System.out.println("poloIds = " + poloIds);

        List<String>brands1000 = jsonPath.getList("products.findAll{it.price=='Rs. 1000'}.brand");//fiyatı RS 1000 olanların markaları
        System.out.println("brands1000 = " + brands1000);

        List<String> brandsthousand = jsonPath.getList("products.findAll{it.price=='Rs. 1000'}.price");
        System.out.println("brandsthousand.size() = " + brandsthousand.size());

        List<Integer>ids=jsonPath.getList("products.findAll{it.id<6}.id");
        List<String>certainProducts = jsonPath.getList("products.findAll{it.id<6}.name");//idleri 6 tan kucuk olan urunler
        List<String>certainPrices = jsonPath.getList("products.findAll{it.price=='Rs. 1000'}.name"); //fiyatı RS.1000 bin olan ürünler
        List<String>priceIdslowerthan6 = jsonPath.getList("products.findAll{it.id<6}.price");//idleri 6 tan kucuk olanların fiyatları
        System.out.println("priceIdslowerthan6  = " + priceIdslowerthan6 );
        System.out.println("certainPrices = " + certainPrices);
        System.out.println("Idsi 6 dan kucuk urun adlari:  " + certainProducts);

        System.out.println("allprices: " + allprices);
        System.out.println("ids smaller than six:  " + ids.size());
        System.out.println("Number of HM:  " + HM.size());

        List<String> brands = jsonPath.getList("products.brand");
        List<String>polo=jsonPath.getList("products.findAll{it.brand=='Polo'}.brand");
        System.out.println("Number of Polo brands:  " + polo.size());

        softAssert.assertEquals(12, ladies.size());
        softAssert.assertEquals(9, gentelmen.size());
        softAssert.assertEquals(13, kids.size());

        System.out.println("Number of brands :  " + brands.size());
        softAssert.assertAll();

    }
}
/*

        Response response = given().spec(spec).when().get("/{first}");
        response.then().statusCode(200).contentType("text/html; charset=utf-8").statusLine("HTTP/1.1 200 OK");
        JsonPath json = response.jsonPath();
         List<String>women = json.getList("products.category.usertype.findAll{it.usertype=='Women'}.usertype");
         List<String> men = json.getList("products.category.usertype.findAll{it.usertype=='Men'}.usertype");
         List<String> kids = json.getList("products.category.usertype.findAll{it.usertype=='Kids'}.usertype");
         assertEquals(12,women.size());
         assertEquals(9,men.size());
         assertEquals(13,kids.size());
Given
    https://automationexercise.com/api/productsList
When
    User sends a GET Request to the url
Then
    HTTP Status Code should be 200
And
    Content Type should be "text/html; charset=utf-8"
And
    Status Line should be HTTP/1.1 200 OK
And
     There must be 12 Women, 9 Men, 13 Kids usertype in products
Given
    https://automationexercise.com/api/productsList
When
    User sends a GET Request to the url
Then
    HTTP Status Code should be 200
And
    Content Type should be "text/html; charset=utf-8"
And
    Status Line should be HTTP/1.1 200 OK
And
     There must be 12 Women, 9 Men, 13 Kids usertype in products
  */