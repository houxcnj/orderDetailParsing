package com.parsetext;
import java.io.*;


public class Extract {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
		String path = "/Users/unu/Downloads/intern/";	
		ProcessBuilder pb = new ProcessBuilder ("python", path + "extract4.py");
		Process p = pb.start();
		
		BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
		String ret = in.readLine();
		while (!ret.equals(null)){
			System.out.println(ret);
			ret = in.readLine();
		}
		}
		catch (Exception e) 
		{
			System.out.println(e);
		}
	}

}
