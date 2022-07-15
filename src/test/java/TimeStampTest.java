import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import static io.restassured.RestAssured.*;

public class TimeStampTest {
    @Test
    public void timeStampTest(){
        Response   response = given().get(Consts.URL + Consts.LIVE_ENDPOINT + Consts.API_KEY);
        System.out.println(response.asString());
        //get today date as String
        String expected = LocalDate.now().toString();

        // get yesterday date as String
        // String yesterdayExpected = LocalDate.now().minusDays(1).toString();

        //get timestamp from response
        Integer actualMs = response.path("timestamp");

        //create format to match expected String date
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        //get Date from the timestamp in request - set it to long and multiply by 1000
        //as in this case API returns UNIX time and not epoch time
        Date date2 = new Date((long)actualMs*1000);

        //format date from response to match expected String date
        String actual = format.format(date2.getTime());

        Assertions.assertEquals(expected, actual);}
}
