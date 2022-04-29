import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.response.ValidatableResponseOptions;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

import files.payload;


public class Basics {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//validate if add palce API working as expected
		//given - all input details
		//when - submit the API
		//Then - Validate the response
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		 String response = given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		.body(payload.AddPlace()).when().post("maps/api/place/add/json")
		         .then().assertThat().statusCode(200).body("scope",equalTo("APP"))
		         .header("server","Apache/2.4.41 (Ubuntu)").extract().response().asString();
		 System.out.println(response);
		 JsonPath js = new JsonPath(response);
		 String place_id = js.getString("place_id");
		 
		 System.out.println(place_id);
		 
		 //add place-> update place with new Address -> Get Place to validateb if new Adress is present in response 
		 
		 //Update Place
		 String newAddress = "GondeDumala";
		 given().log().all().queryParam("key","qaclick123").header("Content-Type","application/json")
		 .body("{\r\n"
		 		+ "\"place_id\":\""+place_id+"\",\r\n"
		 		+ "\"address\":\""+newAddress+"\",\r\n"
		 		+ "\"key\":\"qaclick123\"\r\n"
		 		+ "}").
		 when().put("maps/api/place/update/json")
		 .then().assertThat().log().all().statusCode(200).body("msg",equalTo("Address successfully updated"));
		 
		 //Get place
		 
		String getPlaceResponse= given().log().all().queryParam("key","qaclick123")
				.queryParam("place_id",place_id)
				.when().get("maps/api/place/get/json")
				.then().assertThat().log().all().statusCode(200).extract().response().asString();
		
		JsonPath js1 = new JsonPath(getPlaceResponse);
		String actualAddress = js1.getString("address");
		System.out.println(actualAddress);
	
		
		//Assert.assertEquals(actualAddress,newAddress);
		
		//cucumber Junit, Testng

	}
	}

	


