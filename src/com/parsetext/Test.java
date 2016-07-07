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

public class Test extends JFrame {

	private JPanel contentPane;
	private JTextArea textPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField textField_10;
	private JTextField textField_11;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private final Action action_2 = new SwingAction_2();
	private String myStr;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	public Test() {
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
		
		final JTextArea textPane = new JTextArea();
		textPane.setBounds(10, 47, 335, 439);
		contentPane.add(textPane);
		
		JButton btnNewButton = new JButton("Process");
		//btnNewButton.setAction(action);
		
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
						BufferedWriter out = new BufferedWriter(new FileWriter("test.txt"));
						textPane.write(out);
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
					while (!ret.equals(null)){
						System.out.println(ret);
						ret = in.readLine();
					}
					}
					catch (Exception err) 
					{
						System.out.println(err);
					}
				}
		});
		
		btnNewButton.setBounds(20, 497, 89, 23);
		contentPane.add(btnNewButton);
		
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
		
		textField = new JTextField();
		textField.setBounds(442, 69, 131, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(442, 94, 131, 20);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		textField_2 = new JTextField();
		textField_2.setBounds(442, 121, 131, 20);
		contentPane.add(textField_2);
		textField_2.setColumns(10);
		
		textField_3 = new JTextField();
		textField_3.setBounds(442, 249, 131, 20);
		contentPane.add(textField_3);
		textField_3.setColumns(10);
		
		textField_4 = new JTextField();
		textField_4.setBounds(442, 274, 131, 20);
		contentPane.add(textField_4);
		textField_4.setColumns(10);
		
		textField_5 = new JTextField();
		textField_5.setBounds(442, 299, 131, 20);
		contentPane.add(textField_5);
		textField_5.setColumns(10);
		
		textField_6 = new JTextField();
		textField_6.setBounds(442, 324, 131, 20);
		contentPane.add(textField_6);
		textField_6.setColumns(10);
		
		textField_7 = new JTextField();
		textField_7.setBounds(442, 349, 131, 20);
		contentPane.add(textField_7);
		textField_7.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setBounds(442, 374, 131, 20);
		contentPane.add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setBounds(442, 399, 131, 20);
		contentPane.add(textField_9);
		textField_9.setColumns(10);
		
		textField_10 = new JTextField();
		textField_10.setBounds(442, 424, 131, 20);
		contentPane.add(textField_10);
		textField_10.setColumns(10);
		
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
		
		textField_14 = new JTextField();
		textField_14.setColumns(10);
		textField_14.setBounds(442, 152, 131, 20);
		contentPane.add(textField_14);
		
		JLabel lblRefundTotal = new JLabel("Refund Total");
		lblRefundTotal.setBounds(370, 458, 62, 14);
		contentPane.add(lblRefundTotal);
		
		textField_15 = new JTextField();
		textField_15.setColumns(10);
		textField_15.setBounds(442, 452, 131, 20);
		contentPane.add(textField_15);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Process");
			putValue(SHORT_DESCRIPTION, "Process Data");
		}
		public void actionPerformed(ActionEvent e) {

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
			
		}
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
