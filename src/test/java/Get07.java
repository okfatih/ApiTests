import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import org.testng.asserts.*;

import java.util.List;

import static io.restassured.RestAssured.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Get07 extends JsonPlaceHolderBaseUrl {

    @Test
    public void get01() {
        spec.pathParam("first", "todos");

        //Send The Request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        // response.prettyPrint();
        //Do Assertions

        //1)Status code is 200 == > Status kodu 200 olmali
        response.then().assertThat().statusCode(200);
        assertEquals(200, response.getStatusCode());
        SoftAssert softAssert = new SoftAssert();


        //2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
        //  Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk o
        JsonPath json = response.jsonPath();
        List<Integer> idler = json.getList("findAll{it.id>190}.id");//Groovy Language = Java temelli programlama
        System.out.println("Idsi 190 dan buyuk olanlar: " + idler);
        assertEquals("id 190 dan buyuk olan eslemedi", 10, idler.size());

        // 3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
        //   Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
        List<Integer>userId = json.getList("findAll{it.id<5}.userId");
        System.out.println("IDsi 5 ten kucuk olan userIdler: " + userId);
        assertEquals("5 ten kucuk olan user id bulunamadı",4,userId.size());

        //4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
        //      Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
        //      basliginin "delectus aut autem" icerdigini dogrulayin
        List<String>titles = json.getList("findAll{it.id<5}.title");
        assertTrue("Title'lardan bir tanesi içermemektedir",titles.contains("delectus aut autem"));
        assertTrue("Title'lardan bir tanesi delectus aut autem içermemktedir",
        titles.stream().anyMatch(t->t.equals("delectus aut autem")));

        List<String>titles2 = json.getList("findAll{it.id>5}.title");
        System.out.println("titles2 = " + titles2);

    }
}
 /*
      Given
       https://jsonplaceholder.typicode.com/todos
When
    I send GET Request to the URL == > URL'e Get Request gonderin
Then
    1)Status code is 200 == > Status kodu 200 olmali
    2)Print all ids greater than 190 on the console ==> id si 190 dan buyuk olanlari konsola yazdirin
      Assert that there are 10 ids greater than 190 == > 10 tane id nin 190 dan buyuk oldugunu dogrulayin
    3)Print all userIds whose ids are less than 5 on the console ==> id si 5 den kucuk olan tum userid lerini konsolunu yazdirin
      Assert that the number of userIds whose ids are less than 5 is 4 ==> id si 5 den kucuk olan 4 tane userId oldugunu dogrulayin
    4)Print all titles whose ids are less than 5 ==> ıd si 5 den kucuk olan tum basliklari yazdirin
      Assert that "delectus aut autem" is one of the titles whose id is less than 5 ==> id si 5 den kucuk olan datalarin birinin
      basliginin "delectus aut autem" icerdigini dogrulayin
   */