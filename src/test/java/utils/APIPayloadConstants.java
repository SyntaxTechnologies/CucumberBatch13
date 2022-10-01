package utils;

import APIsteps.APIWorkflowSteps;
import org.json.JSONObject;

public class APIPayloadConstants {

    public static String createEmployeePayload(){
        String createEmployeePayload = "{\n" +
                "  \"emp_firstname\": \"azeddine\",\n" +
                "  \"emp_lastname\": \"sterling\",\n" +
                "  \"emp_middle_name\": \"ms\",\n" +
                "  \"emp_gender\": \"M\",\n" +
                "  \"emp_birthday\": \"2012-09-14\",\n" +
                "  \"emp_status\": \"normal\",\n" +
                "  \"emp_job_title\": \"QA Engineer\"\n" +
                "}";
        return createEmployeePayload;
    }


    public static String createEmployeePayloadJson(){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", "azeddine");
        obj.put("emp_lastname", "sterling");
        obj.put("emp_middle_name", "ms");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2012-09-14");
        obj.put("emp_status", "normal");
        obj.put("emp_job_title", "QA Engineer");
        return obj.toString();
    }

    public static String createDynamicEmployeePayloadJson
            (String firstName, String lastName, String middleName,
             String gender, String birthDay, String empStatus, String jobTitle){
        JSONObject obj = new JSONObject();
        obj.put("emp_firstname", firstName);
        obj.put("emp_lastname", lastName);
        obj.put("emp_middle_name", middleName);
        obj.put("emp_gender", gender);
        obj.put("emp_birthday", birthDay);
        obj.put("emp_status", empStatus);
        obj.put("emp_job_title", jobTitle);
        return obj.toString();
    }

    public static String updateEmployeePayload(){
        JSONObject obj = new JSONObject();
        obj.put("employee_id", APIWorkflowSteps.employee_id);
        obj.put("emp_firstname", "naveed");
        obj.put("emp_lastname", "noorzai");
        obj.put("emp_middle_name", "sms");
        obj.put("emp_gender", "M");
        obj.put("emp_birthday", "2022-08-12");
        obj.put("emp_status", "special employee");
        obj.put("emp_job_title", "QA Lead");
        return obj.toString();
    }
}
