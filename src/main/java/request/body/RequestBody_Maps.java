package request.body;

public class RequestBody_Maps {

	public static String addPlace() {
		return "{\n"
				+ "\"location\": {\n"
				+ "    \"lat\": -38.383494,\n"
				+ "    \"lng\": 33.427362\n"
				+ "},\n"
				+ "\"accuracy\":50,\n"
				+ "\"name\": \"Frontline house\",\n"
				+ "  \"phone_number\": \"(+91) 983 893 3937\",\n"
				+ "  \"address\": \"29, side layout, cohen 09\",\n"
				+ "  \"types\": [\n"
				+ "    \"shoe park\",\n"
				+ "    \"shop\"\n"
				+ "  ],\n"
				+ "  \"website\": \"http://google.com\",\n"
				+ "  \"language\": \"French-IN\"\n"
				+ "}\n"
				+ "";
	}
	
	public static String updatePlace(String place_id, String new_address) {
		return "{\n"
		  		+ "\"place_id\":\""+place_id+"\",\n"
		  		+ "\"address\":\""+new_address+"\",\n"
		  		+ "\"key\":\"qaclick123\"\n"
		  		+ "}\n"
		  		+ "";

	}
}
