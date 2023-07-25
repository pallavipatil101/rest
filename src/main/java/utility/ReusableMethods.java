package utility;

import org.testng.Assert;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ReusableMethods {

	public JsonPath raw_to_json(String response) {
		//JsonPath This class is used to parse json value from the String response above.
		JsonPath js = new JsonPath(response);
		return js;
	}
	
	
	public void verify_strings_equal(String actual, String expected) {
		Assert.assertEquals(actual, expected);
	}
	
	public void verify_numbers_equal(int actual, int expected) {
		Assert.assertEquals(actual, expected);
	}
}
