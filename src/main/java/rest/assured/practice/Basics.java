package rest.assured.practice;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import request.body.RequestBody_Maps;
import utility.ReusableMethods;

public class Basics {

	public static void main(String[] args) {
		
		//Verify if add place API of Google Maps is working as expected.
		
		//given() - all given details.
		//when() - resource/endpoint, http method
		//then() - validate the response
		
		
		ReusableMethods utility = new ReusableMethods();
		
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String post_response = given().log().all().queryParam("key", "qaclick123")
						  .header("Content-Type","application/json")
						  .body(RequestBody_Maps.addPlace())
						  .when().post("/maps/api/place/add/json")
						  .then().assertThat().statusCode(200).body("scope", equalTo("APP"))
						  .header("server", "Apache/2.4.41 (Ubuntu)").extract().response().asString();
		
		//System.out.println("Response is ===> "+post_response);
		JsonPath js = utility.raw_to_json(post_response);
		String place_id = js.getString("place_id");
		String new_address = "70 winter walk, Vermont";
		
		
		//Update address with PUT method:	
						  given().log().all().queryParam("key", "qaclick123")
						  .header("Content-Type","application/json")
						  .body(RequestBody_Maps.updatePlace(place_id, new_address))
						  .when().put("/maps/api/place/update/json")
						  .then().log().all().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
	
						  
		//Verify if address has been updated using GET method:
		String get_response = given().log().all().queryParam("key", "qaclick123")
						  .queryParam("place_id", place_id)
						  .when().get("/maps/api/place/get/json")
						  .then().log().all().assertThat().statusCode(200).body("address", equalTo("70 winter walk, Vermont")).extract().response().asString();
						  
		JsonPath json = utility.raw_to_json(get_response);
		String actual_address = json.getString("address");
		utility.verify_strings_equal(actual_address, new_address);
		
	}

}
