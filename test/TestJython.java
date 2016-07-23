package com.parsetext;

import org.python.util.PythonInterpreter;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.python.core.*; 

public class TestJython {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		PythonInterpreter python = new PythonInterpreter();
		python.execfile("extract4.py");
		PyObject error = python.get("error");
		System.out.println(error.toString());
		PyObject ele = python.get("res");
		System.out.println(ele.toString());
		//FileReader reader = new FileReader("data.json");
		//JSONObject jsonData = (JSONObject) new JSONParser().parse(reader);
		JSONObject jsonData1 = (JSONObject) new JSONParser().parse(ele.asString());
		
		
		String orderID = (String) jsonData1.get("orderID");
		System.out.println(orderID);
		}
		
		catch (Exception e1) {
			System.out.println(e1.getMessage());
		}

	}

}
