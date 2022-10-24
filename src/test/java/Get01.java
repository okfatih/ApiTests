import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class Get01 {
    /*
    1)Postman manuel API testleri için kullandık
    2) Otomasyon tesleri için de Rest Assured Library kullanacağız
    3) Otmasyon teslerimizi yaparken aşağıdaki adımları izliyoruz:
        a) Gereksinimleri anlamak
        b) Test Case yazıyoruz
            i) Test Case yazımında Gherkin Language kullanacağız
            Gherkin dilinde kullanacağımız keywordler;
            -Given: Ön koşullar
            - When:  Yapılacak aksiyonlar için kullanacağız (get,put,post,patch ve delete methodlar)
            - Then:  Request gönderdikten sonraki doğrulama kısımlarında kullanacağız
            - And:   Çoklu işlemlerde kullanacağız


          c) Test kodlarımız yazmaya başlayacağız

             i) Set the Url
             ii) Set the expectedData (Beklenen datanın oluşturulması, bunları Post put ve patch ile yapacağız)
             iii) Type code to send request  (Talep göndermek için kod yazımı)
             iv) Do Assertion (doğrulama yapacağız)
             /*
Given
        https://restful-booker.herokuapp.com/booking/101
    When
        User sends a GET Request to the url
    Then
        HTTP Status Code should be 200
    And
        Content Type should be JSON
    And
        Status Line should be HTTP/1.1 200 OK
 */

    @Test
    public void get01() {
        // i) Set the Url
        String url = "https://restful-booker.herokuapp.com/booking/101";

        //ii) Set the expectedData (Beklenen datanın oluşturulması, bunları Post put ve patch ile yapacağız)
        // Bizden post put veya post put patch istenmioyr

       // iii) Type code to send request  (Talep göndermek için kod yazımı)
      Response response =   given().when().get(url);
      response.prettyPrint();

      //Do Assertion yap
        response.then().assertThat().statusCode(200).contentType("application/json").statusLine("HTTP/1.1 200 OK");
    }
}
