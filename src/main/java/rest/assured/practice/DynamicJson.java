package rest.assured.practice;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import request.body.LibraryBody;
import utility.ReusableMethods;

public class DynamicJson {
	ReusableMethods utility = new ReusableMethods();

	
	@Test(dataProvider = "booksData", priority = 0)
	public void addBook(String isbn, String isle) {
		RestAssured.baseURI = "http://216.10.245.166";
		String post_response =  given().log().all().header("Content-Type","application/json").
							   body(LibraryBody.addBookBody(isbn,isle)).
							   when().
							   post("/Library/Addbook.php").
							   then().log().all().assertThat().statusCode(200).extract().response().asString();
							   
	JsonPath js = utility.raw_to_json(post_response);
	String id = js.get("ID");
	System.out.println(">>>>>>>>>>>>ID is: "+id+" Msg is: "+js.getByte("Msg"));
	deleteBook(id);
	}
	
	@Test
	public void getBook() {
		
	}
	
	//@Test(priority = 1)
	public void deleteBook(String id) {
		RestAssured.baseURI = "http://216.10.245.166";
		String delete_response = given().log().all().header("Content-Type","application/json").
				body(LibraryBody.deleteBook(id)).
				when().
				post("/Library/DeleteBook.php").
				then().log().all().assertThat().statusCode(200).extract().response().asString();

	}
	
	@DataProvider(name = "booksData")
	public Object[][] data(){
		return new Object[][] {{"zen","2"},{"Tan","4"},{"Ino","7"},{"Nez","8"},{"Ren","0"}};
	}
	
}
