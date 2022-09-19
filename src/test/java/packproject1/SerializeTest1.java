package packproject1;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlace;
import pojo.Location;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

public class SerializeTest1 {
	public static void main(String[] args) {
		AddPlace Ap=new AddPlace();
		Ap.setAccuracy(50);
		Ap.setName("Frontline house");
		Ap.setPhone_number("(+91) 983 893 3937");
		Ap.setAddress("29, side layout, cohen 09");
		Ap.setWebsite("http://google.com");    
		Ap.setLanguage("French-IN");
        List<String> mylist=new ArrayList<String>();
        mylist.add("shoe park");
        mylist.add("shop");
        Ap.setTypes(mylist);
		Location l=new Location();
		l.setLat(-38.383494);
		l.setLng(33.427362);
		Ap.setLocation(l);
		
		RequestSpecification req=new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key","qaclick123")
				.setContentType(ContentType.JSON).build();
		
        ResponseSpecification resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		RequestSpecification res=given().spec(req).body(Ap);
		
		Response response=res.when().post("/maps/api/place/add/json").then().
		spec(resspec).extract().response();
		
		String responseString=response.asString();
		System.out.println(responseString);
	} 
	

}
