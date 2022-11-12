import baseUrl.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Test;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;

public class Get16 extends DummyBaseUrl {
    @Test
    public void test01() {
        spec.pathParam("first", "employees");
        //Given URL: https://dummy.restapiexample.com/api/v1/employees
        //When User sends get request
        Response response = given()

                .when()
                .spec(spec)
                .get("/{first}");
        response.prettyPrint();


        //Then Do Assertions
        //  iii) "Tiger Nixon" and "Garrett Winters" are among the employees
        response.then().assertThat().body("data", hasSize(24),
                "data.employee_name", hasItems("Tiger Nixon", "Garrett Winters")
        );
        //   iv) The greatest age is 66
        List<Integer> ages = response.jsonPath().getList("data.employee_age");
        System.out.println("ages = " + ages);
        Collections.sort(ages);
        Integer theOldest = ages.get(ages.size()-1);
        System.out.println("Ages Sorted: " + ages);
        System.out.println(" = " + ages.get(ages.size() - 1));
        assertEquals(66, (int)theOldest);

        //    v) The name of the lowest age is "Tatyana Fitzpatrick"
        String youngestEmployee = response.jsonPath().getString("data.findAll{it.employee_age==" + ages.get(0) + "}.employee_name");
        System.out.println("youngestEmployee = " + youngestEmployee);
        assertEquals("[Tatyana Fitzpatrick]", youngestEmployee);

        //Ek assertion tum maaşları listeye al
        List<Integer> allSalaries = response.jsonPath().getList("data.employee_salary");

        Collections.sort(allSalaries);
        System.out.println("allSalaries Sorted = " + allSalaries);
        System.out.println("The lowest salary is: " + allSalaries.get(0));
        System.out.println("The highest salary is " + allSalaries.get(allSalaries.size()-1));
        // Ek Assertion 1 Verify that the name of the person with the lowest salary is Doris Wilder
      String poorGuy =   response.jsonPath().getString("data.findAll{it.employee_salary =="  +allSalaries.get(0)+"}.employee_name");
        System.out.println("poorGuy = " + poorGuy);
        assertEquals("[Doris Wilder]",poorGuy);


        //     vi) Total salary of all employees is 6,644,770
        List<Integer> salaries = response.jsonPath().getList("data.employee_salary");
        System.out.println("salaries = " + salaries);
        int sum = 0;
        for (Integer e : salaries
        ) {
            sum += e;

        }
        System.out.println(sum);
        assertEquals(6644770, sum);
        //2. yol lambda ile
        Integer sumLabda = salaries.stream().reduce(0, (t, u) -> t + u);
        int sumLambda2 = salaries.stream().reduce(0, Integer::sum);
        int sumLambda3 = salaries.stream().reduce(0, Math::addExact);
    }

}
/*
           URL: https://dummy.restapiexample.com/api/v1/employees
           HTTP Request Method: GET Request
           Test Case: Type by using Gherkin Language
           Assert:  i) Status code is 200
                   ii) There are 24 employees




    */