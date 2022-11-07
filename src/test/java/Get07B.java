import baseUrl.JsonPlaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class Get07B extends JsonPlaceHolderBaseUrl {
    @Test
    public void get07B() {
        spec.pathParam("first", "todos");

        //Send The Request and get the response
        Response response = given().spec(spec).when().get("/{first}");
        JsonPath jsonPath = response.jsonPath();
     //   jsonPath.prettyPrint();
        //idsi 190 dan büyük olan useridler
        List<Integer>userId190 = jsonPath.getList("findAll{it.id>190}.userId");
        System.out.println("userId190 = " + userId190);//ID si 190 dan büyük olanların useridleri
        List<Integer>idSayisi = jsonPath.getList("findAll{it.id}");
        System.out.println("idSayisi.size() = " + idSayisi.size());
    }
}
