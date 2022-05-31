package com.solvd.OnlineShopping.api;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import io.restassured.http.ContentType;




public class RestAssuredDemo {

	private static final Logger LOGGER = LogManager.getLogger(RestAssuredDemo.class);
	
	public static void main(String[] args) {
		

		   getResponse();
		     getResponseStatus();
		     methodPatch();
		     methodPut();
		     methodPost();
		     methodDelete();

	}
	
	
	   public static void getResponse(){
	      given().when().get("https://gorest.co.in/public/v2/users/1")
	      .then()
	      .log()
	      .all();

	  given().queryParam("id","2668")
	               .queryParam("name","Meghnad Acharyaa")
	               .queryParam("acharya_meghnad@mayert-kuhn.info")
	               .when()
	               .get("https://gorest.co.in/public/v2/users/1")
	               .prettyPrint();
	   }

	public static void getResponseStatus(){
	   int statusCode= given().queryParam("id","3830")
	           .queryParam("name","Bhargava Dutta")
	           .queryParam("email","bhargava_dutta@zemlak.io")
	           .when().get("https://gorest.co.in/public/v2/users/1").getStatusCode();
	   LOGGER.info("The response status is "+ statusCode);

	   
	}
	
	
	public static void methodPost() {



		JSONObject request = new JSONObject();

		request.put("name", "Heaven");
		request.put("gender", "Female");
		request.put("email", "testAutomation@gmail.com");
		request.put("status", "Active");

		given().
		header("Authorization","0c5526c2613f879d1a04d4017d356f754d7c51bcaa17151252b9f8b8671e6510son").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toString()).
		when().
		post("https://gorest.co.in/public/v2/users").
		then().
		statusCode(201);

	}

	
	public static void methodPatch() {

		JSONObject request = new JSONObject();

		request.put("name", "Heaven");
		request.put("gender", "Female");
		request.put("email", "testAutomation@gmail.com");
		request.put("status", "Active");

		given().
		header("Authorization","0c5526c2613f879d1a04d4017d356f754d7c51bcaa17151252b9f8b8671e6510son").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toString()).
		when().
		patch("https://gorest.co.in/public/v2/users/1").
		then().
		statusCode(200).
		log().all();

}
	
	public static void methodPut() {

		JSONObject request = new JSONObject();

		request.put("name", "Heaven");
		request.put("gender", "Female");
		request.put("email", "testAutomation@gmail.com");
		request.put("status", "Active");

		given().
		header("Authorization","0c5526c2613f879d1a04d4017d356f754d7c51bcaa17151252b9f8b8671e6510son").
		contentType(ContentType.JSON).
		accept(ContentType.JSON).
		body(request.toString()).
		when().
		put("https://gorest.co.in/public/v2/users/1").
		then().
		statusCode(200);

	}
	

	
	
	public static void methodDelete() {
		
		
		when()
		.delete("https://gorest.co.in/public/v2/users/1")
		.then()
		.statusCode(204)
		.log().all();
		
		

	}
	
}
