package baseUrl;

import Utils.Authentication;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;


public class GmiBankBaseUrl extends Authentication {
    protected RequestSpecification spec;
    @Before
    public void setUp(){
        spec = new RequestSpecBuilder().setBaseUri("https://www.gmibank.com/api").build();
    }
}
