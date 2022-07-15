import io.restassured.response.Response;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UnauthorizedUserNegativeTest {
    @Test
    public void getErrorTypeInvalidKeyLiveEndpointTest() {
        Response response = given().get(Consts.URL + Consts.LIVE_ENDPOINT + "efiwhbf");
        System.out.println(response.asString());
        response.then().body("error.type", equalTo( "invalid_access_key"));
    }

    @Test
    public void getErrorTypeMissingKeyLiveEndpointTest() {
        Response response = given().get(Consts.URL + Consts.LIVE_ENDPOINT);
        response.then().body("error.type", equalTo( "missing_access_key"));
    }
    @Test
    public void getErrorTypeMissingKeyHistoricalEndpointTest() {
        Response response = given().get(Consts.URL + Consts.HISTORICAL_ENDPOINT);
        response.then().body("error.type", equalTo( "missing_access_key"));
    }
    @Test
    public void getErrorMessageInvalidKeyHistoricalEndpointTest() {
        Response response = given().get(Consts.URL + Consts.HISTORICAL_ENDPOINT + "ufjhcjkj");
        response.then().body("error.info", containsString( "You have not supplied a valid API Access Key"));
    }
}
