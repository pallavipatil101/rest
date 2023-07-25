package request.body;

public class LibraryBody {

	public static String addBookBody(String isle, String isbn) {
		return "{\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\n"
				+ "\"isbn\":\""+isle+"\",\n"
				+ "\"aisle\":\""+isbn+"\",\n"
				+ "\"author\":\"John foer\"\n"
				+ "}\n"
				+ "" ;
	}
	
	public static String deleteBook(String ID) {
		return "{\n"
				+ " \n"
				+ "\"ID\" : \""+ID+"\"\n"
				+ " \n"
				+ "} ";
	}
}
