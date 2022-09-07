import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;

public class DummyTest {
    @Test
    public void getDummyEmployees(){
        Response response = given().get("https://dummy.restapiexample.com/api/v1/employees");
        System.out.println(response.asString());
        response.then().statusCode(200);

        JsonPath jsonPath = response.jsonPath();
        List<Map<String, String>> responseData = jsonPath.getList("data");

        List<String> empNames = new ArrayList<>();

        for (Map<String, String> responseDatum : responseData) {
            if (responseDatum.containsKey("employee_name")) {
                empNames.add(responseDatum.get("employee_name"));
            }
        }
        System.out.println(empNames);
    }
    @Test
    public void getSalary(){
        Response response = given().get("https://dummy.restapiexample.com/api/v1/employees");

        JsonPath jsonPath = response.jsonPath();
        List<Map<String, Integer>> resp = jsonPath.getList("data");

        List<Integer> salary = new ArrayList<>();

        for(int i = 0; i < resp.size(); i++){
            if(resp.get(i).containsKey("employee_salary")){
                salary.add(resp.get(i).get("employee_salary"));
            }
        }
        System.out.println(salary);
    }
}
