package APIsteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.junit.Assert;
import utils.APIConstants;
import utils.APIPayloadConstants;

import java.util.List;
import java.util.Map;
import java.util.Set;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class APIWorkflowSteps {

    RequestSpecification request;
    Response response;
    public static String employee_id;


    @Given("a request is prepared for creating an employee")
    public void a_request_is_prepared_for_creating_an_employee() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                body(APIPayloadConstants.createEmployeePayload());
    }

    @When("a POST call is made to create an employee")
    public void a_post_call_is_made_to_create_an_employee() {
      response = request.when().post(APIConstants.CREATE_EMPLOYEE_URI);
    }

    @Then("the status code for creating an employee is {int}")
    public void the_status_code_for_creating_an_employee_is(Integer statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee created contains key {string} and value {string}")
    public void the_employee_created_contains_key_and_value(String key, String value) {
        response.then().assertThat().body(key, equalTo(value));
    }

    @Then("the employee id {string} is stored as global variable")
    public void the_employee_id_is_stored_as_global_variable(String empID) {
        employee_id =  response.jsonPath().getString(empID);
        System.out.println(employee_id);
    }

    @Given("a request is prepared to get the created employee")
    public void a_request_is_prepared_to_get_the_created_employee() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                queryParam("employee_id", employee_id);
    }

    @When("a GET call is made to get the details of the employee")
    public void a_get_call_is_made_to_get_the_details_of_the_employee() {
        response = request.when().get(APIConstants.GET_ONE_EMPLOYEE_URI);
    }
    @Then("the status code is {int}")
    public void the_status_code_is(int statusCode) {
        response.then().assertThat().statusCode(statusCode);
    }

    @Then("the employee id {string} must match with globally stored employee id")
    public void the_employee_id_must_match_with_globally_stored_employee_id(String employeeID) {
      String tempEmployeeId =  response.jsonPath().getString(employeeID);
      Assert.assertEquals(tempEmployeeId, employee_id);
    }

    @Then("the received data from {string} object matches with the data used to create employee")
    public void the_received_data_from_object_matches_with_the_data_used_to_create_employee(String empObject, io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> expectedData = dataTable.asMaps();
        Map<String,String> actualData = response.body().jsonPath().get(empObject);

        for (Map<String, String> map :expectedData
             ) {
            //to get all the keys, we use below line snippet
            Set<String> keys =  map.keySet();
           //bcz set doesn't allow duplicate values
            //we are using another for each loop to get keys one by one
            for (String key:
                 keys) {
                //it will return the value against the key
              String expectedValue =  map.get(key);

              //it will return the value from the response keys
              String actualValue = actualData.get(key);
              Assert.assertEquals(expectedValue, actualValue);
            }
        }
    }

    @Given("a request is prepared for creating an employee via json payload")
    public void a_request_is_prepared_for_creating_an_employee_via_json_payload() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                body(APIPayloadConstants.createEmployeePayloadJson());
    }

    @Given("a request is prepared for creating an employee via more dynamic payload {string} , {string} , {string} , {string} , {string} , {string} , {string}")
    public void a_request_is_prepared_for_creating_an_employee_via_more_dynamic_payload
            (String firstName, String lastName, String middleName,
             String gender, String birthday,
             String empStatus, String jobTitle) {

        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                body(APIPayloadConstants.createDynamicEmployeePayloadJson
                        (firstName, lastName, middleName, gender,
                                birthday, empStatus, jobTitle));
    }

    @Given("a request is prepared for updating an employee")
    public void a_request_is_prepared_for_updating_an_employee() {
        request = given().header(APIConstants.HEADER_CONTENT_TYPE, APIConstants.CONTENT_TYPE_VALUE).
                header(APIConstants.HEADER_AUTHORIZATION, GenerateTokenSteps.token).
                body(APIPayloadConstants.updateEmployeePayload());
    }

    @When("a PUT call is made to update the employee")
    public void a_put_call_is_made_to_update_the_employee() {
       response = request.when().put(APIConstants.UPDATE_EMPLOYEE_URI);
    }

}
