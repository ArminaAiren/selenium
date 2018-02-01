package hui.utils;

import org.junit.Test;
import io.restassured.response.Response;
import static io.restassured.RestAssured.get;
import static org.assertj.core.api.Assertions.assertThat;

import org.json.JSONObject;
import org.json.JSONException;

public class apiTest {

    @Test
    public void getSearchParams() throws JSONException {

        Response response = get("https://auto.ru/convert-listing-url-to-searcher-params/?url=/pechenga/skutery/all/");

        JSONObject jsonResponse = new JSONObject(response.asString());
        String category = jsonResponse.getJSONObject("query").getString("moto_category");

        assertThat(category).isEqualTo("scooters");
    }
}
