package com.parsetext;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;


public class TestAddress {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		InetAddress ip;
		try {
				
			ip = InetAddress.getLocalHost();
			/*
			System.out.print("Current IP address : ");
			byte[] ipadd = ip.getAddress();
			StringBuilder ipa = new StringBuilder();
			for (int i = 0; i< ipadd.length; i++) {
				ipa.append(String.format("%02X", ipadd[i]));
			}
			System.out.println(ipa.toString());
			*/
			try {
			URL whatismyip = new URL("http://myexternalip.com/raw");
			BufferedReader in = new BufferedReader(new InputStreamReader(
			                whatismyip.openStream()));

			String ip1 = in.readLine(); //you get the IP as a String
			System.out.println("IP address is: " + ip1);
			}
			catch (Exception e1) {
				e1.getMessage();
			}
			
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
				
			byte[] mac = network.getHardwareAddress();
				
			System.out.print("Current MAC address : " );
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X", mac[i]));		
			}
			System.out.println(sb.toString());
				
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			
		} catch (SocketException e){
				
			e.printStackTrace();
				
		}
		

	}

}
