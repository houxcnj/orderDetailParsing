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
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class OrderTool extends JFrame {

	private JPanel contentPane;
	private JTextArea inputText;
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
	private String product;
	private JTextField dateField;
	private final Action action_3 = new SwingAction_3();

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
		
		inputText = new JTextArea();
		inputText.setBounds(10, 47, 418, 616);
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
					
					
					FileReader reader = new FileReader("data.json");
					JSONObject jsonData = (JSONObject) new JSONParser().parse(reader);
					
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
					if (refund.equals("$0.00"))
						refundField.setForeground(Color.GREEN);
					else
						refundField.setForeground(Color.RED);
					
					}
					catch (Exception err) 
					{
						System.out.println(err);
					}
				}
		});
		
		btnProcess.setBounds(47, 674, 89, 23);
		contentPane.add(btnProcess);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setAction(action_1);
		btnClear.setBounds(168, 674, 89, 23);
		contentPane.add(btnClear);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setAction(action_2);
		btnExit.setBounds(297, 674, 89, 23);
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
					JOptionPane.showMessageDialog(null, "Order number has been copied to clipboard.");
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
					JOptionPane.showMessageDialog(null, "SKU number has been copied to clipboard.");
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
					JOptionPane.showMessageDialog(null, "ASIN number has been copied to clipboard.");
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
					JOptionPane.showMessageDialog(null, "Customer name has been copied to clipboard.");
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
					JOptionPane.showMessageDialog(null, "Address Line 1 has been copied to clipboard.");
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
					JOptionPane.showMessageDialog(null, "Address Linee 2 has been copied to clipboard.");
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
					JOptionPane.showMessageDialog(null, "City has been copied to clipboard.");
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
					JOptionPane.showMessageDialog(null, "State has been copied to clipboard.");
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
					JOptionPane.showMessageDialog(null, "Zipcode(main) has been copied to clipboard.");
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
					JOptionPane.showMessageDialog(null, "Sub Zipcode has been copied to clipboard.");
				}
			}
		});
		zip2Field.setBounds(552, 399, 172, 20);
		contentPane.add(zip2Field);
		zip2Field.setColumns(10);
		
		phoneField = new JTextField();
		phoneField.setBounds(552, 424, 172, 20);
		contentPane.add(phoneField);
		phoneField.setColumns(10);
		
		JLabel lblUtilitiesDataTools = new JLabel("Utilities Data Tools");
		lblUtilitiesDataTools.setBounds(789, 47, 145, 14);
		contentPane.add(lblUtilitiesDataTools);
		
		JLabel lblReplacementStdData = new JLabel("Replacement Std Data Row");
		lblReplacementStdData.setBounds(789, 69, 172, 14);
		contentPane.add(lblReplacementStdData);
		
		textField_11 = new JTextField();
		textField_11.setEditable(false);
		textField_11.setBounds(789, 91, 145, 20);
		contentPane.add(textField_11);
		textField_11.setColumns(10);
		
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
					JOptionPane.showMessageDialog(null, "Product information has been copied to clipboard.");
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
					JOptionPane.showMessageDialog(null, "Total refund has been copied to clipboard.");
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
					JOptionPane.showMessageDialog(null, "Purchase Date has been copied to clipboard.");
				}
			}
		});
		dateField.setColumns(10);
		dateField.setBounds(552, 183, 172, 20);
		contentPane.add(dateField);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Process");
			putValue(SHORT_DESCRIPTION, "Process Data");
		}
		public void actionPerformed(ActionEvent e) {
			
			 
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Clear");
			putValue(SHORT_DESCRIPTION, "Some short description");
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
			
			inputText.setText("");
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
	private class SwingAction_3 extends AbstractAction {
		public SwingAction_3() {
			putValue(NAME, "SwingAction_3");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
		}
	}
}