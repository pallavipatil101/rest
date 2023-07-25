package rest.assured.practice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utility.ReusableMethods;

public class JsonFile {

	public static void main(String[] args) throws IOException {
		ReusableMethods utility = new ReusableMethods();

		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String post_response = given().log().all().queryParam("key", "qaclick123")
						  .header("Content-Type","application/json")
						  .body(new String(Files.readAllBytes(Paths.get(System.getProperty("user.dir")+"/src/main/resources/JsonFile.json"))))
						  .when().post("/maps/api/place/add/json")
						  .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
						  .header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		//System.out.println("Response is ===> "+post_response);
		JsonPath js = utility.raw_to_json(post_response);
		String place_id = js.getString("place_id");
		System.out.println(">>>>> Place ID: "+place_id);
	}

}
