package com.parsetext;


import java.io.File;
import java.io.FileReader;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Iterator;

import javax.swing.JOptionPane;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TestJOption {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			FileReader reader = new FileReader("refund.json");
			JSONObject json = (JSONObject) new JSONParser().parse(reader);
			Iterator<String> keys = json.keySet().iterator();
			PrintWriter pw = new PrintWriter(new File("test.csv"));
		    while(keys.hasNext()){
		        String key = keys.next();
		        String oneLine = key + "," + getOne(key);
		        pw.write(oneLine + "\n");
		    }
		    pw.flush();
		    pw.close();
		    }
		catch (Exception e) {
			System.out.println(e);
		}

	}
	
	public static String getOne (String orderID) {
		String res = "";
		try {
			FileReader reader = new FileReader("refund.json");
			JSONObject jsonData = (JSONObject) new JSONParser().parse(reader);
			JSONObject thisRefund = (JSONObject) jsonData.get(orderID);
			double oriPrice = 0;
			if (thisRefund.get("Ori_Principal") != null) 
				oriPrice = (double) thisRefund.get("Ori_Principal");
			double rfPrice = (double) thisRefund.get("Principal");
			double shipping = 0;
			if (thisRefund.get("Ori_Shipping") != null) {
				shipping = (double) thisRefund.get("Ori_Shipping");
			}
			double rfShipping = 0;
			if (thisRefund.get("Shipping") != null) {
			rfShipping = (double) thisRefund.get("Shipping");
			}
			else if (thisRefund.get("Ori_Shipping2") != null) {
				rfShipping = (double) thisRefund.get("Ori_Shipping2");
			}
			double promotion = 0;
			if (thisRefund.get("Ori_Principal2") != null)
				promotion = (double) thisRefund.get("Ori_Principal2");
			double rfPromotion = 0;
			if (thisRefund.get("Principal2") != null)
				rfPromotion = (double) thisRefund.get("Principal2");
			double tax = 0;
			if (thisRefund.get("Ori_Tax") != null)
					tax = (double) thisRefund.get("Ori_Tax");
			double rfTax = 0;
			if (thisRefund.get("Tax") != null)
				rfTax = (double) thisRefund.get("Tax");
			
			double refund = oriPrice + rfPrice + shipping + rfShipping + promotion +rfPromotion + tax + rfTax;
			
			res = Double.toString(oriPrice) + "," + Double.toString(rfPrice) + "," + Double.toString(shipping) + "," +
					Double.toString(rfShipping) + "," + Double.toString(promotion) + "," + Double.toString(rfPromotion) + "," +
					Double.toString(tax) + "," + Double.toString(rfTax) + "," + Double.toString(refund);
			
			System.out.println(res);
			reader.close();
			}
			catch (Exception e4) {
				System.out.println(e4.getMessage());
				//logArea.append(LocalDateTime.now().toString() + ": No Refund Information!\n");
			}
		
		return res;
	}

}
