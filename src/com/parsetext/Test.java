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
			
			
			CustomFieldValue cc = new CustomFieldValue((long)24024776, "gogogogo");
			CustomFieldValue cc1 = new CustomFieldValue((long)23988203, "Xiaocheng Hou");
			
			custom_fields.add(cc);
			custom_fields.add(cc1);
			ticket.setCustomFields(custom_fields);
			
			zd.updateTicket(ticket);
			
			
			
			zd.close();
		}
		catch (Exception e)
		{
			System.out.println(e);
		}

	}

}
