import io.restassured.response.Response;

import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class HistoricalEndpointTest {

    @Test
    public void getHistoricalRateResponseTest() {
        Response response = given().get(Consts.URL + Consts.HISTORICAL_ENDPOINT + Consts.API_KEY + "&Date=2020-12-20");
        response.then().statusCode(200);
        response.then().body("date", equalTo("2020-12-20"));
        response.then().body("source", equalTo("USD"));
    }

    @Test
    public void getHistoricalCADRUBRateResponseTest() throws InterruptedException {
        Response response = given().get(Consts.URL + Consts.HISTORICAL_ENDPOINT + Consts.API_KEY + "&Date=2020-12-20&currencies=CAD,RUB");
        response.then().body("quotes.USDCAD", equalTo(1.281145f));
        response.then().body("quotes.USDRUB", equalTo(73.493503f));
        Thread.sleep(3000);
    }
    @Test
    public void getHistoricaRateMissingDateNegativeTest() throws InterruptedException {
        Response response = given().get(Consts.URL + Consts.HISTORICAL_ENDPOINT + Consts.API_KEY + "&Date=");
        response.then().body("success", equalTo(false));
        response.then().body("error.code", equalTo(301));
        response.then().body("error.info", containsString("You have not specified a date"));
        Thread.sleep(3000);
    }
}
