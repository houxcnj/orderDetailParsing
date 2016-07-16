package com.parsetext;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.zendesk.client.v2.Zendesk;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPasswordField;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;
import java.awt.Color;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JPasswordField passField;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private String loginID;
	private String passwd;
	private JTextPane txtpnInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblWelcomeToUnu = new JLabel("Welcome to UNU Customer Support Toolkit");
		lblWelcomeToUnu.setBounds(5, 5, 424, 27);
		lblWelcomeToUnu.setFont(new Font("Tahoma", Font.PLAIN, 22));
		contentPane.add(lblWelcomeToUnu);
		
		JLabel lblLoginId = new JLabel("Login ID: ");
		lblLoginId.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblLoginId.setBounds(74, 53, 89, 33);
		contentPane.add(lblLoginId);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(74, 110, 89, 33);
		contentPane.add(lblPassword);
		
		idField = new JTextField();
		idField.setBounds(159, 57, 190, 28);
		contentPane.add(idField);
		idField.setColumns(10);
		
		passField = new JPasswordField();
		passField.setColumns(10);
		passField.setBounds(159, 115, 190, 28);
		contentPane.add(passField);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setAction(action);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnLogin.setBounds(74, 168, 105, 33);
		contentPane.add(btnLogin);
		
		JButton btnExit = new JButton("Exit");
		btnExit.setAction(action_1);
		btnExit.setBounds(255, 168, 105, 33);
		contentPane.add(btnExit);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(74, 212, 286, 38);
		contentPane.add(scrollPane);
		
		txtpnInfo = new JTextPane();
		txtpnInfo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtpnInfo.setForeground(Color.BLUE);
		scrollPane.setViewportView(txtpnInfo);
		txtpnInfo.setEditable(false);
	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "Login");
			putValue(SHORT_DESCRIPTION, "Login to main window");
		}
		public void actionPerformed(ActionEvent e) {
			loginID = idField.getText();
			passwd = new String(passField.getPassword());
			try {
				Zendesk zd = new Zendesk.Builder("https://unu.zendesk.com")
						.setUsername(loginID).setPassword(passwd)
				        // .setToken("g0YVCKIJdndLlGFZLouccN38rrgefbAiIL5SrACZ") // or .setPassword("...")
				        .build();
				zd.getTickets().iterator().hasNext();
				zd.close();
				txtpnInfo.setText("Login successful");
				OrderTool tool = new OrderTool();
				tool.setVisible(true);
				tool.setLoginID(loginID);
				tool.setPassword(passwd);
				dispose();
			} catch (Exception e1) {
				txtpnInfo.setText(e1.getMessage() + ": Please check your ID and Password!");
				
			}
			
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Exit the application");
		}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
}
