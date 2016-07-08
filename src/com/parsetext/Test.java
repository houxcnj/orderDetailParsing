package com.parsetext;

import java.io.BufferedReader;
import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		FileReader reader = new FileReader("data.json");
		BufferedReader in = new BufferedReader(reader);
		String str = in.readLine();
		String s = "";
		while (str != null) {
			s += str;
			str = in.readLine();
		}
		System.out.println(s);
		JSONObject jsonData = (JSONObject) new JSONParser().parse(s);
		System.out.println(jsonData.get("orderID"));
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

	}

}
