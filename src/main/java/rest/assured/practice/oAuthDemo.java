package rest.assured.practice;
import static io.restassured.RestAssured.given;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.restassured.path.json.JsonPath;
import utility.ReusableMethods;


public class oAuthDemo {

	public static void main(String[] args) throws InterruptedException {
		ReusableMethods utility = new ReusableMethods();
//
//		WebDriverManager.chromedriver().setup();
//		WebDriver driver = new ChromeDriver();
//		driver.get("https://accounts.google.com/o/oauth2/v2/auth/identifier?access_type=offline&client_id=587594460880-u53ikl5ast2sup28098ofsm9iku8vvm6.apps.googleusercontent.com&code_challenge=kT90i4Yzboa6Yp7bs7kwEkE4Z12bJ9RB3obnuIVBBQk&code_challenge_method=S256&include_granted_scopes=true&prompt=select_account%20consent&redirect_uri=https%3A%2F%2Fsso.teachable.com%2Fidentity%2Fcallbacks%2Fgoogle%2Fcallback&response_type=code&scope=email%20https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email%20https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile%20openid%20profile&state=eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJnb29nbGUiLCJpYXQiOjE2ODA3NzQ4ODEsImV4cCI6MTY4MDc3NjY4MSwianRpIjoiNTE0YTkzZTItNTAzZS00ZTRmLWI0ZDgtY2NhMmQ1OTE4YThhIiwiaXNzIjoic2tfeno4dHc2ZGciLCJzdWIiOiJpVkdFNzFCc1BQTERkRlA0UzVHdjZCeEVzN3l2UldnMnp5dGtPRnR1a1Z5Yjl3RWRXUlJ2VHVTVmNxb3JhcVdpUXZkdkN2VFFOT0pZWTBsOGVsbVNiZyJ9.FVT91phvA0Enn9Ib3HUWIuxRb9vriVehnJAwiBV8Ggk&service=lso&o2v=2&flowName=GeneralOAuthFlow");
//		driver.findElement(By.cssSelector("input[type='Email']")).sendKeys("pallavi.v.patil11@gmail.com");
//		driver.findElement(By.cssSelector("input[type='Email']")).sendKeys(Keys.ENTER);
//		Thread.sleep(3000);
//		driver.findElement(By.cssSelector("input[type='Password']")).sendKeys("B1bli0phile@101");
//		driver.findElement(By.cssSelector("input[type='Password']")).sendKeys(Keys.ENTER);
//		Thread.sleep(3000);
//		
//		String url = driver.getCurrentUrl();
		String url = "login and paste url here";
		String partial_code = url.split("code=")[1];
		String code = partial_code.split("&scope")[0];
		System.out.println(code);
		
		System.out.println("-----------------------------------------------------------------------------");
		
		String AccessTokenRes = given().urlEncodingEnabled(false)
				.queryParam("code", code)
				.queryParam("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
				.queryParam("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
				.queryParam("redirect_uri","https://rahulshettyacademy.com/getCourse.php").queryParam("grant_type", "authorization_code")
				.when().log().all()
				.post("https://www.googleapis.com/oauth2/v4/token").asString();	
		
		JsonPath js = utility.raw_to_json(AccessTokenRes);
		String acsessToken = js.getString("access_token");

		System.out.println("-----------------------------------------------------------------------------");

		
		String res = given().log().all().queryParam("access_token", "acsessToken").when()
		.get("https://rahulshettyacademy.com/getCourse.php").asString();
		
		System.out.println(res);
		
	
	}

}
