package utils;

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
}
