package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class HardCodedExamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    //CRUD operations we perform
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjQzMjI4ODMsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY2NDM2NjA4MywidXNlcklkIjoiNDM2NiJ9.DZSBsuigL9XpDfqlhbusLrC9Q2w8g0egtOM4P_QbHH0";

    @Test
    //to create a regular/normal employee
    public void createEmployee(){
        //prepare the request
        //POST
    RequestSpecification preparedRequest = given().header("Content-Type", "application/json").
                header("Authorization", token).body("{\n" +
                        "  \"emp_firstname\": \"azeddine\",\n" +
                        "  \"emp_lastname\": \"sterling\",\n" +
                        "  \"emp_middle_name\": \"ms\",\n" +
                        "  \"emp_gender\": \"M\",\n" +
                        "  \"emp_birthday\": \"2012-09-14\",\n" +
                        "  \"emp_status\": \"normal\",\n" +
                        "  \"emp_job_title\": \"QA Engineer\"\n" +
                        "}");

      //hitting the endpoint
      Response response = preparedRequest.when().post("/createEmployee.php");
      //printing the response in console
      response.prettyPrint();

      //assertions and validation
        //verofying the correct  status code of the request
        response.then().assertThat().statusCode(201);





    }

}
