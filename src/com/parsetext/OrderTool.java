package com.parsetext;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JTextPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.Box;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import java.awt.Panel;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class OrderTool extends JFrame {

	private JPanel contentPane;
	//private JTextArea textPane;
	private JTextField orderField;
	private JTextField skuField;
	private JTextField asinField;
	private JTextField nameField;
	private JTextField add1Field;
	private JTextField add2Field;
	private JTextField cityField;
	private JTextField stateField;
	private JTextField zip1Field;
	private JTextField zip2Field;
	private JTextField phoneField;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField productField;
	private JTextField refundField;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private String orderID;
	private String fullName;
	private String address1;
	private String address2;
	private String city;
	private String state;
	private String zipcode1;
	private String zipcode2;
	private String purchaseDate;
	private String sku;
	private String asin;
	private String refund;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					OrderTool frame = new OrderTool();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public OrderTool() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 881, 582);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUnuCustomerSupport = new JLabel("UNU Customer Support Tool Kit");
		lblUnuCustomerSupport.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblUnuCustomerSupport.setBounds(248, 11, 309, 14);
		lblUnuCustomerSupport.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblUnuCustomerSupport);
		
		JTextArea inputText = new JTextArea();
		inputText.setBounds(10, 47, 335, 439);
		contentPane.add(inputText);
		
		JButton btnProcess = new JButton("Process");
		//btnNewButton.setAction(action);
		
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						BufferedWriter out = new BufferedWriter(new FileWriter("test.txt"));
						inputText.write(out);
						out.close();
					} 
					catch (Exception e1)
					{
							System.out.println(e1);
					}
				try {
					String path = "C:/Users/unu/Downloads/intern/";	
					ProcessBuilder pb = new ProcessBuilder ("python", path + "extract4.py");
					Process p = pb.start();
					
					BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
					
					String ret = in.readLine();
					String res = "";
					while (ret != null){
						//System.out.println(ret);
						res += ret;
						ret = in.readLine();
					}
					System.out.println(res);
					//FileReader reader = new FileReader("data.json");
					JSONObject jsonData = (JSONObject) new JSONParser().parse(res);
					
					orderID = (String) jsonData.get("orderID");
					fullName = (String) jsonData.get("name");
					address1 = (String) jsonData.get("address1");
					address2 = (String) jsonData.get("address2");
					city = (String) jsonData.get("city");
					state = (String) jsonData.get("state");
					zipcode1 = (String) jsonData.get("zipcode1");
					zipcode2 = (String) jsonData.get("zipcode2");
					purchaseDate = (String) jsonData.get("purchase_date");
					sku = (String) jsonData.get("sku");
					asin = (String) jsonData.get("asin");
					refund = (String) jsonData.get("refund");
					
					orderField.setText(orderID);
					}
					catch (Exception err) 
					{
						System.out.println(err);
					}
				}
		});
		
		btnProcess.setBounds(20, 497, 89, 23);
		contentPane.add(btnProcess);
		
		JButton btnCleaer = new JButton("Clear");
		btnCleaer.setBounds(137, 497, 89, 23);
		contentPane.add(btnCleaer);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setAction(action_2);
		btnExit.setBounds(256, 497, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblInputWindow = new JLabel("Input Window");
		lblInputWindow.setBounds(10, 32, 89, 14);
		contentPane.add(lblInputWindow);
		
		JLabel lblOrderProductInfo = new JLabel("Order/ Product Info");
		lblOrderProductInfo.setBounds(370, 47, 108, 14);
		contentPane.add(lblOrderProductInfo);
		
		JLabel lblOrder = new JLabel("Order #");
		lblOrder.setBounds(370, 69, 46, 14);
		contentPane.add(lblOrder);
		
		JLabel lblSku = new JLabel("SKU #");
		lblSku.setBounds(370, 94, 46, 14);
		contentPane.add(lblSku);
		
		JLabel lblAsin = new JLabel("ASIN #");
		lblAsin.setBounds(370, 121, 46, 14);
		contentPane.add(lblAsin);
		
		JLabel lblCustomerInfo = new JLabel("Customer Info");
		lblCustomerInfo.setBounds(370, 230, 108, 14);
		contentPane.add(lblCustomerInfo);
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(370, 255, 46, 14);
		contentPane.add(lblFullName);
		
		JLabel lblAddress = new JLabel("Address1");
		lblAddress.setBounds(370, 280, 46, 14);
		contentPane.add(lblAddress);
		
		JLabel lblAddress_1 = new JLabel("Address2");
		lblAddress_1.setBounds(370, 305, 46, 14);
		contentPane.add(lblAddress_1);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(370, 330, 46, 14);
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(370, 355, 46, 14);
		contentPane.add(lblState);
		
		JLabel lblZip = new JLabel("Zip1");
		lblZip.setBounds(370, 380, 46, 14);
		contentPane.add(lblZip);
		
		JLabel lblZip_1 = new JLabel("Zip2");
		lblZip_1.setBounds(370, 405, 46, 14);
		contentPane.add(lblZip_1);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(370, 430, 46, 14);
		contentPane.add(lblPhone);
		
		orderField = new JTextField();
		orderField.setBounds(442, 69, 131, 20);
		contentPane.add(orderField);
		orderField.setColumns(10);
		
		skuField = new JTextField();
		skuField.setBounds(442, 94, 131, 20);
		contentPane.add(skuField);
		skuField.setColumns(10);
		
		asinField = new JTextField();
		asinField.setBounds(442, 121, 131, 20);
		contentPane.add(asinField);
		asinField.setColumns(10);
		
		nameField = new JTextField();
		nameField.setBounds(442, 249, 131, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		add1Field = new JTextField();
		add1Field.setBounds(442, 274, 131, 20);
		contentPane.add(add1Field);
		add1Field.setColumns(10);
		
		add2Field = new JTextField();
		add2Field.setBounds(442, 299, 131, 20);
		contentPane.add(add2Field);
		add2Field.setColumns(10);
		
		cityField = new JTextField();
		cityField.setBounds(442, 324, 131, 20);
		contentPane.add(cityField);
		cityField.setColumns(10);
		
		stateField = new JTextField();
		stateField.setBounds(442, 349, 131, 20);
		contentPane.add(stateField);
		stateField.setColumns(10);
		
		zip1Field = new JTextField();
		zip1Field.setBounds(442, 374, 131, 20);
		contentPane.add(zip1Field);
		zip1Field.setColumns(10);
		
		zip2Field = new JTextField();
		zip2Field.setBounds(442, 399, 131, 20);
		contentPane.add(zip2Field);
		zip2Field.setColumns(10);
		
		phoneField = new JTextField();
		phoneField.setBounds(442, 424, 131, 20);
		contentPane.add(phoneField);
		phoneField.setColumns(10);
		
		JLabel lblUtilitiesDataTools = new JLabel("Utilities Data Tools");
		lblUtilitiesDataTools.setBounds(604, 47, 145, 14);
		contentPane.add(lblUtilitiesDataTools);
		
		JLabel lblReplacementStdData = new JLabel("Replacement Std Data Row");
		lblReplacementStdData.setBounds(604, 69, 172, 14);
		contentPane.add(lblReplacementStdData);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setBounds(604, 91, 145, 20);
		contentPane.add(textField_11);
		textField_11.setColumns(10);
		
		JLabel lblReplacementFastData = new JLabel("Replacement Fast Data Row");
		lblReplacementFastData.setBounds(604, 143, 172, 14);
		contentPane.add(lblReplacementFastData);
		
		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setBounds(604, 168, 145, 20);
		contentPane.add(textField_12);
		textField_12.setColumns(10);
		
		JLabel lblReturnLabelData = new JLabel("Return Label Data Row");
		lblReturnLabelData.setBounds(604, 220, 172, 14);
		contentPane.add(lblReturnLabelData);
		
		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setBounds(604, 245, 145, 20);
		contentPane.add(textField_13);
		textField_13.setColumns(10);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setBounds(370, 152, 46, 14);
		contentPane.add(lblProduct);
		
		productField = new JTextField();
		productField.setColumns(10);
		productField.setBounds(442, 152, 131, 20);
		contentPane.add(productField);
		
		JLabel lblRefundTotal = new JLabel("Refund Total");
		lblRefundTotal.setBounds(370, 458, 62, 14);
		contentPane.add(lblRefundTotal);
		
		refundField = new JTextField();
		refundField.setColumns(10);
		refundField.setBounds(442, 452, 131, 20);
		contentPane.add(refundField);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Process");
			putValue(SHORT_DESCRIPTION, "Process Data");
		}
		public void actionPerformed(ActionEvent e) {
			/*
			try {
			BufferedWriter out = new BufferedWriter(new FileWriter("test.txt"));
			textPane.write(out);
			out.close();
				
	
			} 
			catch (Exception e1)
			{
				System.out.println(e1);
			}
			if (textPane == null)
				System.err.println("textpane is null");
			else{
			String[] lines = textPane.getText().split("\\n");
			for (int i = 0; i<lines.length; i++)
				System.out.println(lines[i]);
			try {
				String path = "C:/Users/unu/Downloads/intern/";	
				ProcessBuilder pb = new ProcessBuilder ("python", path + "extract4.py");
				Process p = pb.start();
				
				BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
				String ret = in.readLine();
				while (!ret.equals(null)){
					System.out.println(ret);
					ret = in.readLine();
				}
				}
				catch (Exception err) 
				{
					System.out.println(err);
				}
			
		} */
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "SwingAction_1");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Exit the Application");
		}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
