package com.payroll.demo.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.payroll.demo.entity.AddEmployee;

public class CustomDeserializer extends JsonDeserializer<AddEmployee>{
	  @Override
	    public AddEmployee deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
	        String value = p.readValueAs(String.class);
	        AddEmployee addEmployee = new AddEmployee();
	        // Set the properties of MyClass using the value from the JSON node
	        return addEmployee;
	    }

}
