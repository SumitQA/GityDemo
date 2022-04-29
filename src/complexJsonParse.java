import files.payload;
import io.restassured.path.json.JsonPath;

public class complexJsonParse {
	public static void main(String[] args) {
		
	
	
	JsonPath js = new JsonPath(payload.CoursePrice());
	//Print No of courses returned by API
	int count = js.getInt("courses.size()");
	{
		System.out.println(count);
	}
	
	String titlFirstCousrse = js.get("courses [0].title");
	System.out.println(titlFirstCousrse);

	}
}
