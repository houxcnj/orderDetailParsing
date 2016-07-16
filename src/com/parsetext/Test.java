package com.parsetext;

import java.awt.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.zendesk.client.v2.*;
import org.zendesk.client.v2.model.Comment;
import org.zendesk.client.v2.model.CustomFieldValue;
import org.zendesk.client.v2.model.Ticket;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Zendesk zd = new Zendesk.Builder("https://unu.zendesk.com")
					.setUsername("zendesk@myunu.com")
			        .setToken("g0YVCKIJdndLlGFZLouccN38rrgefbAiIL5SrACZ") // or .setPassword("...")
			        .build();
			//Comment comment0 = new Comment("This is a test!");
			//Ticket ticket0 = new Ticket((long)1234567, "Test", comment0);
			
			//zd.createTicket(ticket0);
			
			Ticket ticket = zd.getTicket(19284);
			
			
			ArrayList<CustomFieldValue> custom_fields = new ArrayList<CustomFieldValue>();
			
			
			//CustomFieldValue cc = new CustomFieldValue((long)24024776, "gogogogo");
			/*
			CustomFieldValue c1 = new CustomFieldValue((long)23988203, "Great Great");
			CustomFieldValue c2 = new CustomFieldValue((long)24037856, "541 W 31st St");
			CustomFieldValue c3 = new CustomFieldValue((long)24037866, "2F");
			CustomFieldValue c4 = new CustomFieldValue((long)24037876, "Chicago");
			CustomFieldValue c5 = new CustomFieldValue((long)23998853, "IL");
			CustomFieldValue c6 = new CustomFieldValue((long)23998863, "60616");
			CustomFieldValue c7 = new CustomFieldValue((long)24037886, "US");
			CustomFieldValue c8 = new CustomFieldValue((long)23998873, "3128061158");
			
			custom_fields.add(c1);
			custom_fields.add(c2);
			custom_fields.add(c3);
			custom_fields.add(c4);
			custom_fields.add(c5);
			custom_fields.add(c6);
			custom_fields.add(c7);
			custom_fields.add(c8);
			ticket.setCustomFields(custom_fields);
			*/
			
			System.out.println(ticket.getId());
			// zd.updateTicket(ticket);
			zd.close();
		}
		catch (Exception e)
		{
			System.err.println(e);
			System.out.println("Please check your id and passward!");
		}

	}

}
