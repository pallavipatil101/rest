package rest.assured.practice;

import io.restassured.path.json.JsonPath;
import mock.response.Courses;
import utility.ReusableMethods;

public class CoursesResponseValidation {

	public static void main(String[] args) {
		ReusableMethods utility = new ReusableMethods();
		JsonPath js = utility.raw_to_json(Courses.coursesResponse());
		int sumOfPrice = 0;
		
		//Print No of courses returned by API
		int count = js.getInt("courses.size()");	//It'll give the count of courses from mock response. courses is array[]
		System.out.println(count);
		
		//Print Purchase Amount
		int totalAmount = js.getInt("dashboard.purchaseAmount");
		System.out.println(totalAmount);
		
		//Print Title of the first course
		String firstTitle = js.getString("courses[0].title");
		System.out.println(firstTitle);
		
		//Print All course titles and their respective Prices
		for(int i=0; i<count; i++)
		{
			int price = js.getInt("courses["+i+"].price");
			String title = js.getString("courses["+i+"].title");
			System.out.println("Title of "+i+"th book is: "+title+" and price is: "+price);
		}
		
		//Print no of copies sold by RPA Course
		for(int i=0; i<count; i++)
		{
			String title = js.getString("courses["+i+"].title");
			if(title.equalsIgnoreCase("RPA"))
			{
				System.out.println("Copiessold by RPA: "+js.getInt("courses["+i+"].copies"));
				break;
			}
		}
		
		//Verify if Sum of all Course prices matches with Purchase Amount
		for(int i=0; i<count; i++) {
			int numOfCopies = js.getInt("courses["+i+"].copies");
			int price = js.getInt("courses["+i+"].price");
			sumOfPrice = (numOfCopies*price)+sumOfPrice;
		}
		
		System.out.println(sumOfPrice);
		utility.verify_numbers_equal(sumOfPrice, totalAmount);
	
	}

}
