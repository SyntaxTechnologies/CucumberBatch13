Feature: API workflow resource

  Background:
    Given a JWT is generated

  @api
  Scenario: First scenario to create the employee
    Given a request is prepared for creating an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global variable

  @api
  Scenario: Retrieving recently created employee
    Given a request is prepared to get the created employee
    When a GET call is made to get the details of the employee
    Then the status code is 200
    And the employee id "employee.employee_id" must match with globally stored employee id
    And the received data from "employee" object matches with the data used to create employee
    |emp_firstname|emp_lastname|emp_middle_name|emp_gender|emp_birthday|emp_status|emp_job_title|
    |azeddine     |sterling    |ms             |Male      |2012-09-14  |normal    |QA Engineer  |












