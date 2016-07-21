package com.parsetext;
import java.io.*;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;

import org.joda.time.LocalDateTime;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.python.core.PyObject;
import org.python.util.PythonInterpreter;
import org.zendesk.client.v2.Zendesk;
import org.zendesk.client.v2.model.Comment;
import org.zendesk.client.v2.model.CustomFieldValue;
import org.zendesk.client.v2.model.Ticket;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JScrollPane;
import javax.swing.DropMode;
import javax.swing.ScrollPaneConstants;
 

public class OrderTool extends JFrame {

	private JPanel contentPane;
	private JTextArea inputText;
	private JTextArea logArea; 
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
	private JTextField replaceStdField;
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
	private String product;
	private String phone;
	private JTextField dateField;
	private final Action action_3 = new SwingAction_3();
	private JTextField ticketNField;
	private Ticket ticket_bak;
	private Ticket ticket;
	private final Action action_4 = new SwingAction_4();
	private String loginID;
	private String passwd;
	private LocalDateTime dt;

	/**
	 * Launch the application.
	 */
	/*
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
	*/
	/**
	 * Create the frame.
	 */
	public OrderTool() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1024, 768);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUnuCustomerSupport = new JLabel("UNU Customer Support Tool Kit");
		lblUnuCustomerSupport.setFont(new Font("Times New Roman", Font.PLAIN, 20));
		lblUnuCustomerSupport.setBounds(270, 11, 309, 25);
		lblUnuCustomerSupport.setHorizontalAlignment(SwingConstants.CENTER);
		contentPane.add(lblUnuCustomerSupport);
		
		dt = new LocalDateTime();
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 47, 418, 616);
		contentPane.add(scrollPane_1);
		
		inputText = new JTextArea();
		scrollPane_1.setViewportView(inputText);
		
		// create process button and add the process action
		JButton btnProcess = new JButton("Process");
		//btnNewButton.setAction(action);
		
		btnProcess.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// get input from inputText and write it to test.txt file.
				try {
						BufferedWriter out = new BufferedWriter(new FileWriter("test.txt"));
						inputText.write(out);
						out.close();
					} 
					catch (Exception e1)
					{
							System.out.println(e1);
					}
				// execute the python file. The python file need test.txt as input and will output a data.json file
				try {
					/*	
					ProcessBuilder pb = new ProcessBuilder ("python", "extract4.py");
					Process p = pb.start();
					
					BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
					
					String ret = in.readLine();
					
					// tell if the input is legal based on python output stream.
					while (ret != null){
						if (ret.equals("Sorry, please input again!")) {
							JOptionPane.showMessageDialog(null, "Illegal input text, please try it again!");
							inputText.setText("");
							ret = in.readLine();
						}
						else {
						System.out.println(ret);
						ret = in.readLine();}
					}
					
					// read .json file and parse it.
					FileReader reader = new FileReader("data.json");
					JSONObject jsonData = (JSONObject) new JSONParser().parse(reader);
					*/
					
					PythonInterpreter extract = new PythonInterpreter();
					extract.execfile("extract4.py");
					PyObject error = extract.get("error");
					if (error.toString().equalsIgnoreCase("true")) {
						JOptionPane.showMessageDialog(null, "Illegal input text, please try it again!");
						inputText.setText("");
					}
					else {
					PyObject order = extract.get("res");
					JSONObject jsonData = (JSONObject) new JSONParser().parse(order.toString());
					
					// get value from each key in json file. 
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
					product = (String) jsonData.get("product");
					phone = (String) jsonData.get("phone");
					
					// fill value to the appropriate field 
					orderField.setText(orderID);
					skuField.setText(sku);
					asinField.setText(asin);;
					nameField.setText(fullName);
					add1Field.setText(address1);;
					add2Field.setText(address2);
					cityField.setText(city);
					stateField.setText(state);
					zip1Field.setText(zipcode1);
					zip2Field.setText(zipcode2);
					productField.setText(product);;
					refundField.setText(refund);
					dateField.setText(purchaseDate);
					phoneField.setText(phone);
					// set the font color based on refund total
					if (refund.equals("$0.00"))
						refundField.setForeground(Color.GREEN);
					else
						refundField.setForeground(Color.RED);
					}
					
					}
					catch (Exception err) 
					{
						System.out.println(err);
					}
				}
		});
		
		btnProcess.setBounds(47, 674, 89, 23);
		contentPane.add(btnProcess);
		
		// create clear button and its action.
		JButton btnClear = new JButton("Clear");
		btnClear.setAction(action_1);
		btnClear.setBounds(326, 674, 89, 23);
		contentPane.add(btnClear);
		
		// create exit button and its action.
		JButton btnExit = new JButton("Exit");
		btnExit.setAction(action_2);
		btnExit.setBounds(466, 674, 89, 23);
		contentPane.add(btnExit);
		
		JLabel lblInputWindow = new JLabel("Input Window");
		lblInputWindow.setBounds(10, 32, 89, 14);
		contentPane.add(lblInputWindow);
		
		JLabel lblOrderProductInfo = new JLabel("Order/ Product Info");
		lblOrderProductInfo.setBounds(447, 47, 108, 14);
		contentPane.add(lblOrderProductInfo);
		
		JLabel lblOrder = new JLabel("Order #");
		lblOrder.setBounds(452, 69, 46, 14);
		contentPane.add(lblOrder);
		
		JLabel lblSku = new JLabel("SKU #");
		lblSku.setBounds(452, 94, 46, 14);
		contentPane.add(lblSku);
		
		JLabel lblAsin = new JLabel("ASIN #");
		lblAsin.setBounds(452, 121, 46, 14);
		contentPane.add(lblAsin);
		
		JLabel lblCustomerInfo = new JLabel("Customer Info");
		lblCustomerInfo.setBounds(447, 230, 108, 14);
		contentPane.add(lblCustomerInfo);
		
		JLabel lblFullName = new JLabel("Full Name");
		lblFullName.setBounds(452, 255, 74, 14);
		contentPane.add(lblFullName);
		
		JLabel lblAddress = new JLabel("Address1");
		lblAddress.setBounds(452, 280, 74, 14);
		contentPane.add(lblAddress);
		
		JLabel lblAddress_1 = new JLabel("Address2");
		lblAddress_1.setBounds(452, 305, 74, 14);
		contentPane.add(lblAddress_1);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(452, 330, 74, 14);
		contentPane.add(lblCity);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(452, 355, 74, 14);
		contentPane.add(lblState);
		
		JLabel lblZip = new JLabel("Zip1");
		lblZip.setBounds(452, 380, 74, 14);
		contentPane.add(lblZip);
		
		JLabel lblZip_1 = new JLabel("Zip2");
		lblZip_1.setBounds(452, 405, 74, 14);
		contentPane.add(lblZip_1);
		
		JLabel lblPhone = new JLabel("Phone");
		lblPhone.setBounds(452, 430, 74, 14);
		contentPane.add(lblPhone);
		
		// field action: double click and select all and copied to clipboard
		orderField = new JTextField();
		orderField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					orderField.selectAll();
					String sltext = orderField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.insert(dt.toString() + ": Order number has been copied to clipboard.\n", 0);
				}
			}
		});
		orderField.setBounds(552, 69, 172, 20);
		contentPane.add(orderField);
		orderField.setColumns(10);
		
		skuField = new JTextField();
		skuField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					skuField.selectAll();
					String sltext = skuField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.insert(dt.toString() + ": SKU number has been copied to clipboard.\n", 0);
				}
			}
		});
		skuField.setBounds(552, 94, 172, 20);
		contentPane.add(skuField);
		skuField.setColumns(10);
		
		asinField = new JTextField();
		asinField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					asinField.selectAll();
					String sltext = asinField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.insert(dt.toString() + ": ASIN number has been copied to clipboard.\n", 0);
				}
			}
		});
		asinField.setBounds(552, 121, 172, 20);
		contentPane.add(asinField);
		asinField.setColumns(10);
		
		nameField = new JTextField();
		nameField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					nameField.selectAll();
					String sltext = nameField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.insert(dt.toString() + ": Customer name has been copied to clipboard.\n", 0);
				}
			}
		});
		nameField.setBounds(552, 249, 172, 20);
		contentPane.add(nameField);
		nameField.setColumns(10);
		
		add1Field = new JTextField();
		add1Field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					add1Field.selectAll();
					String sltext = add1Field.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.insert(dt.toString() + ": Address Line 1 has been copied to clipboard.\n", 0);
				}
			}
		});
		add1Field.setBounds(552, 274, 172, 20);
		contentPane.add(add1Field);
		add1Field.setColumns(10);
		
		add2Field = new JTextField();
		add2Field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					add2Field.selectAll();
					String sltext = add2Field.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.insert(dt.toString() + ": Address Linee 2 has been copied to clipboard.\n", 0);
				}
			}
		});
		add2Field.setBounds(552, 299, 172, 20);
		contentPane.add(add2Field);
		add2Field.setColumns(10);
		
		cityField = new JTextField();
		cityField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					cityField.selectAll();
					String sltext = cityField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.insert(dt.toString() + ": City has been copied to clipboard.\n", 0);
				}
			}
		});
		cityField.setBounds(552, 324, 172, 20);
		contentPane.add(cityField);
		cityField.setColumns(10);
		
		stateField = new JTextField();
		stateField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					stateField.selectAll();
					String sltext = stateField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.insert(dt.toString() + ": State has been copied to clipboard.\n", 0);
				}
			}
		});
		stateField.setBounds(552, 349, 172, 20);
		contentPane.add(stateField);
		stateField.setColumns(10);
		
		zip1Field = new JTextField();
		zip1Field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					zip1Field.selectAll();
					String sltext = zip1Field.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.insert(dt.toString() + ": Zipcode(main) has been copied to clipboard.\n", 0);
				}
			}
		});
		zip1Field.setBounds(552, 374, 172, 20);
		contentPane.add(zip1Field);
		zip1Field.setColumns(10);
		
		zip2Field = new JTextField();
		zip2Field.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					zip2Field.selectAll();
					String sltext = zip2Field.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.insert(dt.toString() + ": Sub Zipcode has been copied to clipboard.\n", 0);
				}
			}
		});
		zip2Field.setBounds(552, 399, 172, 20);
		contentPane.add(zip2Field);
		zip2Field.setColumns(10);
		
		phoneField = new JTextField();
		phoneField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					phoneField.selectAll();
					String sltext = phoneField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.insert(dt.toString() + ": phone number has been copied to clipboard.\n", 0);
				}
			}
		});
		phoneField.setBounds(552, 424, 172, 20);
		contentPane.add(phoneField);
		phoneField.setColumns(10);
		
		JLabel lblUtilitiesDataTools = new JLabel("Utilities Data Tools");
		lblUtilitiesDataTools.setBounds(789, 47, 145, 14);
		contentPane.add(lblUtilitiesDataTools);
		
		JLabel lblReplacementStdData = new JLabel("Replacement Std Data Row");
		lblReplacementStdData.setBounds(789, 69, 172, 14);
		contentPane.add(lblReplacementStdData);
		
		replaceStdField = new JTextField();
		replaceStdField.setEditable(false);
		replaceStdField.setBounds(789, 91, 145, 20);
		contentPane.add(replaceStdField);
		replaceStdField.setColumns(10);
		replaceStdField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					replaceStdField.selectAll();
					String sltext = replaceStdField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(dt.toString() + ": Raw Date has been copied to clipboard.\n");
				}
			}
		});
		
		JLabel lblReplacementFastData = new JLabel("Replacement Fast Data Row");
		lblReplacementFastData.setBounds(789, 143, 172, 14);
		contentPane.add(lblReplacementFastData);
		
		textField_12 = new JTextField();
		textField_12.setEditable(false);
		textField_12.setBounds(789, 168, 145, 20);
		contentPane.add(textField_12);
		textField_12.setColumns(10);
		
		JLabel lblReturnLabelData = new JLabel("Return Label Data Row");
		lblReturnLabelData.setBounds(789, 220, 172, 14);
		contentPane.add(lblReturnLabelData);
		
		textField_13 = new JTextField();
		textField_13.setEditable(false);
		textField_13.setBounds(789, 245, 145, 20);
		contentPane.add(textField_13);
		textField_13.setColumns(10);
		
		JLabel lblProduct = new JLabel("Product");
		lblProduct.setBounds(452, 152, 46, 14);
		contentPane.add(lblProduct);
		
		productField = new JTextField();
		productField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					productField.selectAll();
					String sltext = productField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.insert(dt.toString() + ": Product information has been copied to clipboard.\n", 0);
				}
			}
		});
		productField.setColumns(10);
		productField.setBounds(552, 152, 172, 20);
		contentPane.add(productField);
		
		JLabel lblRefundTotal = new JLabel("Refund Total");
		lblRefundTotal.setBounds(452, 458, 74, 14);
		contentPane.add(lblRefundTotal);
		
		refundField = new JTextField();
		// refundField.setForeground(Color.GREEN);
		refundField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					refundField.selectAll();
					String sltext = refundField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.insert(dt.toString() + ": Total refund has been copied to clipboard.\n", 0);
				}
			}
		});
		refundField.setColumns(10);
		refundField.setBounds(552, 452, 172, 20);
		contentPane.add(refundField);
		
		JLabel lblDate = new JLabel("Date");
		lblDate.setBounds(452, 186, 46, 14);
		contentPane.add(lblDate);
		
		dateField = new JTextField();
		dateField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					dateField.selectAll();
					String sltext = dateField.getSelectedText();
					StringSelection data = new StringSelection(sltext);
					Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
					clipboard.setContents(data, data);
					logArea.append(dt.toString() + ": Purchase Date has been copied to clipboard.\n");
				}
			}
		});
		dateField.setColumns(10);
		dateField.setBounds(552, 183, 172, 20);
		contentPane.add(dateField);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(447, 573, 487, 90);
		contentPane.add(scrollPane);
		
		// log area, show the mouse action info 
		logArea = new JTextArea();
		scrollPane.setViewportView(logArea);
		logArea.setForeground(Color.BLUE);
		logArea.setEditable(false);
		
		JButton btnExport = new JButton("Export");
		btnExport.setBounds(187, 674, 89, 23);
		contentPane.add(btnExport);
		btnExport.setAction(action_3);
		
		JLabel lblZendeskTicket = new JLabel("ZenDesk ticket #");
		lblZendeskTicket.setBounds(789, 302, 145, 14);
		contentPane.add(lblZendeskTicket);
		
		ticketNField = new JTextField();
		ticketNField.setColumns(10);
		ticketNField.setBounds(789, 324, 145, 20);
		contentPane.add(ticketNField);
		
		JButton btnPopulate = new JButton("Populate");
		btnPopulate.setAction(action);
		btnPopulate.setBounds(768, 355, 89, 23);
		contentPane.add(btnPopulate);
		
		JButton btnRestore = new JButton("Restore");
		btnRestore.setAction(action_4);
		btnRestore.setBounds(867, 355, 90, 23);
		contentPane.add(btnRestore);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Populate");
			putValue(SHORT_DESCRIPTION, "Populate data to zendesk");
		}
		public void actionPerformed(ActionEvent e) {
			int selectedOption = JOptionPane.showConfirmDialog(null, 
					"Please make sure the ticket number and information are correct!",
					"Warning",
					JOptionPane.YES_NO_OPTION);
			if (selectedOption == JOptionPane.YES_OPTION) {
			
			String str = ticketNField.getText();
			int ticketID = Integer.parseInt(str);
			try {
				Zendesk zd = new Zendesk.Builder("https://unu.zendesk.com")
						.setUsername(loginID).setPassword(passwd)
				        // .setToken("g0YVCKIJdndLlGFZLouccN38rrgefbAiIL5SrACZ") // or .setPassword("...")
				        .build();
				ticket_bak = new Ticket();
				ticket_bak = zd.getTicket((long) ticketID);
				ticket = new Ticket();
				ticket = zd.getTicket((long) ticketID);
				
				ArrayList<CustomFieldValue> custom_fields = new ArrayList<CustomFieldValue>();
				
				CustomFieldValue c1 = new CustomFieldValue((long)23988203, fullName);
				CustomFieldValue c2 = new CustomFieldValue((long)24037856, address1);
				CustomFieldValue c3 = new CustomFieldValue((long)24037866, address2);
				CustomFieldValue c4 = new CustomFieldValue((long)24037876, city);
				CustomFieldValue c5 = new CustomFieldValue((long)23998853, state);
				CustomFieldValue c6 = new CustomFieldValue((long)23998863, zipcode1);
				CustomFieldValue c7 = new CustomFieldValue((long)24037886, "US");
				CustomFieldValue c8 = new CustomFieldValue((long)23998873, phone);
				CustomFieldValue c9 = new CustomFieldValue((long)23988223, purchaseDate);
				//CustomFieldValue c10 = new CustomFieldValue((long)23988233, orderID);
				//CustomFieldValue c10 = new CustomFieldValue((long)24058526, product);
			
				custom_fields.add(c1);
				custom_fields.add(c2);
				custom_fields.add(c3);
				custom_fields.add(c4);
				custom_fields.add(c5);
				custom_fields.add(c6);
				custom_fields.add(c7);
				custom_fields.add(c8);
				custom_fields.add(c9);
				//custom_fields.add(c10);
				ticket.setCustomFields(custom_fields);
				
				zd.updateTicket(ticket);
				zd.close();
				String mm = ": ticket " + Integer.toString(ticketID) + "has been updated.\n";
				logArea.insert(dt.toString() + mm, 0);
			}
			catch (Exception err)
			{
				System.out.println(err);
				err.getMessage();
				logArea.insert(dt.toString() + "Information can't be update to the ticket, please double check!", 0);
			}
			}
		}
	}
	// clear action
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Clear");
			putValue(SHORT_DESCRIPTION, "Clear all text");
		}
		public void actionPerformed(ActionEvent e) {
			orderField.setText("");
			skuField.setText("");
			asinField.setText("");;
			nameField.setText("");
			add1Field.setText("");;
			add2Field.setText("");
			cityField.setText("");
			stateField.setText("");
			zip1Field.setText("");
			zip2Field.setText("");
			productField.setText("");;
			refundField.setText("");
			dateField.setText("");
			phoneField.setText("");
			
			inputText.setText("");
		}
	}
	//exit action
	private class SwingAction_2 extends AbstractAction {
		public SwingAction_2() {
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Exit the Application");
		}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "Export");
			putValue(SHORT_DESCRIPTION, "Export data to row");
		}
		public void actionPerformed(ActionEvent e) {
			String outRow = "\t" + orderID + "\t\t\t\t" + sku + "\t\t\t" + fullName +"\t\t" + address1 + "\t" + address2 + "\t" + city + "\t" + state +"\t" + zipcode1 + "\tUS\t\t" + phone;
			replaceStdField.setText(outRow);
			
		}
	}
	private class SwingAction_4 extends AbstractAction {
		public SwingAction_4() {
			putValue(NAME, "Restore");
			putValue(SHORT_DESCRIPTION, "Restore ticket to previous status");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				
				Zendesk zd = new Zendesk.Builder("https://unu.zendesk.com")
						.setUsername("zendesk@myunu.com")
				        .setToken("g0YVCKIJdndLlGFZLouccN38rrgefbAiIL5SrACZ") // or .setPassword("...")
				        .build();
				/*
				if (ticket_bak.equals(null))
					System.out.println("Sorry, no previous data.");
				else
					zd.updateTicket(ticket_bak);
				*/
				System.out.println(ticket_bak.getCustomFields().get(0).getValue());
				zd.updateTicket(ticket_bak);
				zd.close();
			}
			catch (Exception err) {
				System.out.println(err);
			}
		}
	}
	public void setLoginID (String str) {
		loginID = str;
	}
	public void setPassword (String pass) {
		passwd = pass;
	}
}
