package com.parsetext;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;

import javax.swing.Action;

public class Config extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField txtOrderid;
	private JTextField txtName;
	private JTextField txtAddress;
	private JTextField txtAddress_1;
	private JTextField txtCity;
	private JTextField txtState;
	private JTextField txtCountry;
	private JTextField txtZip;
	private JTextField txtZip_1;
	private JTextField txtProduct;
	private JTextField txtAsin;
	private JTextField txtSku;
	private JTextField txtPurchaseDate;
	private JTextField txtRefund;
	private JTextField txtPhone;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	private String loginID;
	private String passwd;
	private String brand;
	private String sellerID;
	
	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			Config dialog = new Config();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

	/**
	 * Create the dialog.
	 */
	public Config(String id, String pass, String bd, String sellid) {
		loginID = id;
		passwd = pass;
		brand = bd;
		sellerID = sellid;
		
		setBounds(100, 100, 522, 394);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		
		txtOrderid = new JTextField();
		txtOrderid.setBounds(360, 6, 130, 26);
		contentPanel.add(txtOrderid);
		txtOrderid.setColumns(10);
		System.out.println(loginID);
		txtOrderid.setText(getDefaultText("orderID"));
		
		txtName = new JTextField();
		txtName.setBounds(103, 6, 130, 26);
		contentPanel.add(txtName);
		txtName.setColumns(10);
		txtName.setText(getDefaultText("fullName"));
		
		txtAddress = new JTextField();
		txtAddress.setBounds(103, 44, 130, 26);
		contentPanel.add(txtAddress);
		txtAddress.setColumns(10);
		txtAddress.setText(getDefaultText("address1"));
		
		txtAddress_1 = new JTextField();
		txtAddress_1.setBounds(103, 82, 130, 26);
		contentPanel.add(txtAddress_1);
		txtAddress_1.setColumns(10);
		txtAddress_1.setText(getDefaultText("address2"));
		
		txtCity = new JTextField();
		txtCity.setBounds(103, 120, 130, 26);
		contentPanel.add(txtCity);
		txtCity.setColumns(10);
		txtCity.setText(getDefaultText("city"));
		
		txtState = new JTextField();
		txtState.setBounds(103, 158, 130, 26);
		contentPanel.add(txtState);
		txtState.setColumns(10);
		txtState.setText(getDefaultText("state"));
		
		txtCountry = new JTextField();
		txtCountry.setBounds(103, 196, 130, 26);
		contentPanel.add(txtCountry);
		txtCountry.setColumns(10);
		txtCountry.setText(getDefaultText("country"));
		
		txtZip = new JTextField();
		txtZip.setBounds(103, 234, 130, 26);
		contentPanel.add(txtZip);
		txtZip.setColumns(10);
		txtZip.setText(getDefaultText("zipcode1"));
		
		txtZip_1 = new JTextField();
		txtZip_1.setBounds(103, 272, 130, 26);
		contentPanel.add(txtZip_1);
		txtZip_1.setColumns(10);
		txtZip_1.setText(getDefaultText("zipcode2"));
		
		txtProduct = new JTextField();
		txtProduct.setBounds(360, 44, 130, 26);
		contentPanel.add(txtProduct);
		txtProduct.setColumns(10);
		txtProduct.setText(getDefaultText("product"));
		
		txtAsin = new JTextField();
		txtAsin.setBounds(360, 82, 130, 26);
		contentPanel.add(txtAsin);
		txtAsin.setColumns(10);
		txtAsin.setText(getDefaultText("asin"));
		
		txtSku = new JTextField();
		txtSku.setBounds(360, 120, 130, 26);
		contentPanel.add(txtSku);
		txtSku.setColumns(10);
		txtSku.setText(getDefaultText("sku"));
		
		txtPurchaseDate = new JTextField();
		txtPurchaseDate.setBounds(360, 158, 130, 26);
		contentPanel.add(txtPurchaseDate);
		txtPurchaseDate.setColumns(10);
		txtPurchaseDate.setText(getDefaultText("purchase_date"));
		
		txtRefund = new JTextField();
		txtRefund.setBounds(360, 196, 130, 26);
		contentPanel.add(txtRefund);
		txtRefund.setColumns(10);
		txtRefund.setText(getDefaultText("refund"));
		
		txtPhone = new JTextField();
		txtPhone.setBounds(360, 234, 130, 26);
		contentPanel.add(txtPhone);
		txtPhone.setColumns(10);
		txtPhone.setText(getDefaultText("phone"));
		
		JLabel lblName = new JLabel("Name");
		lblName.setBounds(6, 11, 85, 16);
		contentPanel.add(lblName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(6, 49, 85, 16);
		contentPanel.add(lblAddress);
		
		JLabel lblAddressnd = new JLabel("Address 2nd");
		lblAddressnd.setBounds(6, 87, 85, 16);
		contentPanel.add(lblAddressnd);
		
		JLabel lblCity = new JLabel("City");
		lblCity.setBounds(6, 125, 85, 16);
		contentPanel.add(lblCity);
		
		JLabel lblState = new JLabel("State");
		lblState.setBounds(6, 163, 85, 16);
		contentPanel.add(lblState);
		
		JLabel lblCountry = new JLabel("Country");
		lblCountry.setBounds(6, 201, 85, 16);
		contentPanel.add(lblCountry);
		
		JLabel lblZip = new JLabel("Zip");
		lblZip.setBounds(6, 239, 85, 16);
		contentPanel.add(lblZip);
		
		JLabel lblSubZip = new JLabel("Sub Zip");
		lblSubZip.setBounds(6, 277, 85, 16);
		contentPanel.add(lblSubZip);
		
		JLabel lblOrder = new JLabel("Order #");
		lblOrder.setBounds(245, 11, 103, 16);
		contentPanel.add(lblOrder);
		
		JLabel lblProductInfo = new JLabel("Product Info");
		lblProductInfo.setBounds(245, 49, 103, 16);
		contentPanel.add(lblProductInfo);
		
		JLabel lblAsin = new JLabel("ASIN");
		lblAsin.setBounds(245, 87, 103, 16);
		contentPanel.add(lblAsin);
		
		JLabel lblSku = new JLabel("SKU");
		lblSku.setBounds(245, 125, 103, 16);
		contentPanel.add(lblSku);
		
		JLabel lblPurchaseDate = new JLabel("Purchase Date");
		lblPurchaseDate.setBounds(245, 163, 103, 16);
		contentPanel.add(lblPurchaseDate);
		
		JLabel lblRefund = new JLabel("Refund Total");
		lblRefund.setBounds(245, 201, 103, 16);
		contentPanel.add(lblRefund);
		
		JLabel lblPhone = new JLabel("Phone #");
		lblPhone.setBounds(245, 239, 103, 16);
		contentPanel.add(lblPhone);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setAction(action);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setAction(action_1);
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "OK");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			try {
				int selectedOption = JOptionPane.showConfirmDialog(null, 
						"Please make sure the field number is correct!",
						"Warning",
						JOptionPane.YES_NO_OPTION);
				if (selectedOption == JOptionPane.YES_OPTION) {
				String filePath = loginID + "config.json";
				BufferedWriter out = new BufferedWriter(new FileWriter(filePath));
				JSONObject obj = new JSONObject();
				
			obj.putIfAbsent("orderID", Integer.parseInt(txtOrderid.getText()));
			obj.putIfAbsent("fullName", Integer.parseInt(txtName.getText()));
			obj.putIfAbsent("address1", Integer.parseInt(txtAddress.getText()));
			obj.putIfAbsent("address2", Integer.parseInt(txtAddress_1.getText()));
			obj.putIfAbsent("city", Integer.parseInt(txtCity.getText()));
			obj.putIfAbsent("state", Integer.parseInt(txtState.getText()));
			obj.putIfAbsent("country", Integer.parseInt(txtCountry.getText()));
			obj.putIfAbsent("zipcode1", Integer.parseInt(txtZip.getText()));
			obj.putIfAbsent("zipcode2", Integer.parseInt(txtZip_1.getText()));
			obj.putIfAbsent("product", Integer.parseInt(txtProduct.getText()));
			obj.putIfAbsent("asin", Integer.parseInt(txtAsin.getText()));
			obj.putIfAbsent("sku", Integer.parseInt(txtSku.getText()));
			obj.putIfAbsent("purchase_date", Integer.parseInt(txtPurchaseDate.getText()));
			obj.putIfAbsent("refund", Integer.parseInt(txtRefund.getText()));
			obj.putIfAbsent("phone", Integer.parseInt(txtPhone.getText()));
			
			out.write(obj.toJSONString());
			out.flush();
			out.close();
			
			JOptionPane.showMessageDialog(null, "Done!");
			OrderTool2 tool = new OrderTool2();
			tool.setVisible(true);
			tool.setLoginID(loginID);
			tool.setPassword(passwd);
			tool.setBrand(brand);
			tool.setSellerID(sellerID);
			dispose();
				}
			}
			catch (Exception e1) {
				e1.getMessage();
				JOptionPane.showMessageDialog(null, "Please input numbers!");
			}
		}
	}
	public void setLoginID (String id) {
		loginID = id;
	}
	
	public void setPassword (String pass) {
		passwd = pass;
	}
	public void setBrand (String bd) {
		brand = bd;
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Cancel");
			putValue(SHORT_DESCRIPTION, "Some short description");
		}
		public void actionPerformed(ActionEvent e) {
			dispose();
		}
	}
	// get the custome field Id from json file.
	private String getDefaultText(String lbl) {
		String result = "0";
		String filePath = loginID + "config.json";
		try {
		
		FileReader reader = new FileReader(filePath);
		JSONObject jsonData = (JSONObject) new JSONParser().parse(reader);
		result = String.valueOf((long) jsonData.get(lbl));
		reader.close();
		}
		catch (Exception e1) {
			System.out.println(e1.getMessage());
			
		}
		return result;
	}
}
