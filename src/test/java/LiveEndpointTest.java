import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class LiveEndpointTest {
    @Test
    public void getCurrentRateResponseCode(){
        Response response = given().get(Consts.URL + Consts.LIVE_ENDPOINT + Consts.API_KEY);
        System.out.println(response.asString());
        response.then().statusCode(200);
    }
    @Test
    public void getCurrentRateResultTest() throws InterruptedException {
        Response response = given().contentType("application/json").get(Consts.URL + Consts.LIVE_ENDPOINT + Consts.API_KEY);
        response.then().body("source", equalTo("USD"));
       response.then().body("success", equalTo(true));
       response.then().body("terms", containsString("terms"));
       Thread.sleep(3000);
    }
    @Test
    public void getResponseWrongCurrencyCodeTest(){
        Response response = given().contentType("application/json").get(Consts.URL + Consts.LIVE_ENDPOINT + Consts.API_KEY + "&currencies=CMV,OJF");
        System.out.println(response.asString());
        response.then().body("error.code", equalTo(202));
        response.then().body("success", equalTo(false));
        response.then().body("error.info", containsString("You have provided one or more invalid Currency Codes"));
    }
}
