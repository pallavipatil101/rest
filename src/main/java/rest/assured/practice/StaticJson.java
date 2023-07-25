package rest.assured.practice;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import utility.ReusableMethods;

public class StaticJson {

@Test
public void addBook() throws IOException
{

	ReusableMethods utility = new ReusableMethods();


	RestAssured.baseURI = "https://rahulshettyacademy.com";
	String post_response = given().log().all().queryParam("key", "qaclick123")
					  .header("Content-Type","application/json")
					  .body(GenerateStringFromResource(System.getProperty("user.dir")+"/src/main/resources/JsonFile.json"))
					  .when().post("/maps/api/place/add/json")
					  .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
					  .header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
	
	JsonPath js = utility.raw_to_json(post_response);
    String id=js.get("ID");
    System.out.println(id);

}

	public static String GenerateStringFromResource(String path) throws IOException {
		return new String(Files.readAllBytes(Paths.get(path)));
	}

}

