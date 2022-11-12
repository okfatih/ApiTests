package Utils;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {

    public static String generateToken(){
        String username = "Batch81";
        String password = "Batch81+";

        Map<String, Object> postbody = new HashMap<>();
        postbody.put("username",username);
        postbody.put("password", password);
        postbody.put("rememberMe",true);
        String endpoint = "https://www.gmibank.com/api/authenticate";

        Response response = given().contentType(ContentType.JSON).body(postbody).when().post(endpoint);
        JsonPath token = response.jsonPath();

        return token.getString("id_token");
    }
}
