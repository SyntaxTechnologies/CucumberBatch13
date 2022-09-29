package APIsteps;

import io.cucumber.java.en.Given;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class GenerateTokenSteps {
String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
public static String token;

    @Given("a JWT is generated")
    public void a_jwt_is_generated() {
        //prepare  the request
        RequestSpecification preparedRequest = given().
                header("Content-Type", "application/json").
                body("{\n" +
                        "  \"email\": \"batch13api@abc.com\",\n" +
                        "  \"password\": \"Test@123\"\n" +
                        "}");

        Response response = preparedRequest.when().post("/generateToken.php");

       token = "Bearer " + response.jsonPath().getString("token");
       System.out.println(token);
    }
}
