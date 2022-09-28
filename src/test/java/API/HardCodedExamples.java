package API;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class HardCodedExamples {

    String baseURI = RestAssured.baseURI = "http://hrm.syntaxtechs.net/syntaxapi/api";
    //CRUD operations we perform
    String token = "Bearer eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE2NjQzMjI4ODMsImlzcyI6ImxvY2FsaG9zdCIsImV4cCI6MTY2NDM2NjA4MywidXNlcklkIjoiNDM2NiJ9.DZSBsuigL9XpDfqlhbusLrC9Q2w8g0egtOM4P_QbHH0";
    static String employee_id;

    @Test
    public void bgetCreatedEmployee(){
        //prepare the request
        RequestSpecification preparedRequest =
                given().header("Content-Type", "application/json").
                        header("Authorization", token).
                        queryParam("employee_id", employee_id);

        Response response = preparedRequest.when().get("/getOneEmployee.php");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test
    //to create a regular/normal employee
    public void acreateEmployee(){
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
        //verifying the correct  status code of the request
        response.then().assertThat().statusCode(201);
        response.then().assertThat().body("Employee.emp_firstname", equalTo("azeddine"));
        response.then().assertThat().body("Employee.emp_lastname", equalTo("sterling"));
        response.then().assertThat().body("Employee.emp_middle_name", equalTo("ms"));
        response.then().assertThat().body("Message", equalTo("Employee Created"));

        //verify the response headers
        response.then().assertThat().header("Server", "Apache/2.4.39 (Win64) PHP/7.2.18");

        //to get the employee id from the body
       employee_id = response.jsonPath().getString("Employee.employee_id");
       System.out.println(employee_id);
    }


    @Test
    public void cUpdateEmployee(){
        RequestSpecification preparedRequest = given().header("Authorization", token).
                header("Content-Type", "application/json").body("{\n" +
                        "  \"employee_id\": \""+employee_id+"\",\n" +
                        "  \"emp_firstname\": \"ozkana\",\n" +
                        "  \"emp_lastname\": \"zee\",\n" +
                        "  \"emp_middle_name\": \"pati chai\",\n" +
                        "  \"emp_gender\": \"F\",\n" +
                        "  \"emp_birthday\": \"2005-08-24\",\n" +
                        "  \"emp_status\": \"fired\",\n" +
                        "  \"emp_job_title\": \"Manager\"\n" +
                        "}");

        Response response = preparedRequest.when().put("/updateEmployee.php");
        response.then().assertThat().statusCode(200);
    }

}
