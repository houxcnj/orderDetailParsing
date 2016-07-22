package com.parsetext;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.codec.digest.DigestUtils;
import org.zendesk.client.v2.Zendesk;

import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import java.awt.Color;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField idField;
	private JPasswordField passField;
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();
	private String loginID;
	private String passwd;
	private JTextArea txtpnInfo;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if (isThisComputer()) {
					Login frame = new Login();
					frame.setLocationRelativeTo(null);
					frame.setVisible(true);
					}
					else {
						Confirm cf = new Confirm();
						cf.setLocationRelativeTo(null);
						cf.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
						cf.setVisible(true);
					}
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
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(74, 212, 286, 38);
		contentPane.add(scrollPane);
		
		txtpnInfo = new JTextArea();
		txtpnInfo.setFont(new Font("Times New Roman", Font.PLAIN, 14));
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
				txtpnInfo.append("\nLogin successful");
				if (!isThisPerson()) {
					Config cfid = new Config();
					cfid.setLoginID(loginID);
					cfid.setPassword(passwd);
					cfid.setLocationRelativeTo(null);
					cfid.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					cfid.setVisible(true);
				}
				else {
				OrderTool tool = new OrderTool();
				tool.setVisible(true);
				tool.setLoginID(loginID);
				tool.setPassword(passwd);
				tool.setLocationRelativeTo(null);
				dispose();
				}
			} catch (Exception e1) {
				System.out.println(e1.getMessage());
				txtpnInfo.append("\nPlease check your ID and Password!");
				
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
	private static boolean isThisComputer() {
		String sha1 = "";
		try {
		FileReader reader = new FileReader("sha1");
		BufferedReader in = new BufferedReader(reader);
		sha1 = in.readLine();
		in.close();
		}
		catch (Exception e1) {
			return false;
		}
		if (sha1.equals(thisComputer())) 
			return true;
		else 
			return false;
	}
	
	private static String thisComputer() {
		String sha1 = "";
		InetAddress ip;
		String originalsha1 = "";
		try {
				
			ip = InetAddress.getLocalHost();
			try {
				URL whatismyip = new URL("http://myexternalip.com/raw");
				BufferedReader in = new BufferedReader(new InputStreamReader(
				                whatismyip.openStream()));

				String ip1 = in.readLine();
				originalsha1 = ip1;
			}
			catch (Exception e1) {
				e1.getMessage();
			}
			
			NetworkInterface network = NetworkInterface.getByInetAddress(ip);
				
			byte[] mac = network.getHardwareAddress();
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < mac.length; i++) {
				sb.append(String.format("%02X", mac[i]));		
			}
			originalsha1 += sb;
		} catch (UnknownHostException e) {
			
			e.printStackTrace();
			
		} catch (SocketException e){
				
			e.printStackTrace();
				
		}
		sha1 = DigestUtils.sha1Hex(originalsha1);
		return sha1;
	}
	public boolean isThisPerson() {
		String filePath = loginID + "config.json";
		File f = new File(filePath);
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		else {
			return false;
		}
	}
}
