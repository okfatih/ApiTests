import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;
import org.junit.Test;

public class JasonPlaceHolderBaseUrl {
    protected RequestSpecification spec;

    @Before
    public void setUp() {
        spec = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }
}
