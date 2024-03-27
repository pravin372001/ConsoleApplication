package com.zsgs.interviewpanel.serialize;

import com.google.gson.*;
import com.zsgs.interviewpanel.model.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class EmployeeDeserializer implements JsonDeserializer<Map<Integer, Employee>> {

    @Override
    public Map<Integer, Employee> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();
        Map<Integer, Employee> employeeMap = new HashMap<>();

        for (Map.Entry<String, JsonElement> entry : jsonObject.entrySet()) {
            int employeeId = Integer.parseInt(entry.getKey());
            JsonObject employeeJson = entry.getValue().getAsJsonObject();

            int credentialId = employeeJson.get("credentialId").getAsInt();
            String employeeName = employeeJson.get("employeeName").getAsString();
            String employeeEmail = employeeJson.get("employeeEmail").getAsString();
            String employeePhone = employeeJson.has("employeePhone") ? employeeJson.get("employeePhone").getAsString()
                    : "";
            EmployeeType employeeType = EmployeeType.valueOf(employeeJson.get("employeeType").getAsString());

            Employee employee = createEmployee(employeeType, employeeName, employeeEmail, employeePhone, credentialId);
            employee.setEmployeeId(employeeId);
            employeeMap.put(employeeId, employee);
        }
        return employeeMap;
    }

    private Employee createEmployee(EmployeeType employeeType, String employeeName, String employeeEmail,
            String employeePhone, int credentialId) {
        switch (employeeType) {
            case Admin:
                return new Admin(employeeName, employeeEmail, employeePhone, credentialId, employeeType);
            case Receptionist:
                return new Receptionist(employeeName, employeeEmail, employeePhone, credentialId, employeeType);
            case HR:
                return new HumanResource(employeeName, employeeEmail, employeePhone, credentialId);
            default:
                throw new JsonParseException("Unknown employee type: " + employeeType);
        }
    }
}
