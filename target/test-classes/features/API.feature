Feature: API workflow resource

  @api
  Scenario: First scenario to create the employee
    Given a JWT is generated
    Given a request is prepared for creating an employee
    When a POST call is made to create an employee
    Then the status code for creating an employee is 201
    And the employee created contains key "Message" and value "Employee Created"
    And the employee id "Employee.employee_id" is stored as global variable





