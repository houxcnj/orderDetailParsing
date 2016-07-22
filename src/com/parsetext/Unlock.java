package com.parsetext;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import org.apache.commons.codec.digest.DigestUtils;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPasswordField;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.URL;
import java.net.UnknownHostException;

import javax.swing.Action;

public class Unlock extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JPasswordField passwordField;
	private final Action action = new SwingAction();
	private final String PASSWORD = "unuunuunu";
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			Unlock dialog = new Unlock();
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
	public Unlock() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblPleaseInputPassword = new JLabel("Please Input Password:");
			lblPleaseInputPassword.setHorizontalAlignment(SwingConstants.CENTER);
			lblPleaseInputPassword.setFont(new Font("Times New Roman", Font.PLAIN, 20));
			lblPleaseInputPassword.setBounds(107, 38, 246, 54);
			contentPanel.add(lblPleaseInputPassword);
		}
		
		passwordField = new JPasswordField();
		passwordField.setBounds(117, 104, 219, 20);
		contentPanel.add(passwordField);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setAction(action);
				// okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setAction(action_1);
				cancelButton.setActionCommand("Exit");
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
			String ps = new String(passwordField.getPassword());
			if (ps.equals(PASSWORD)) {
				try {
					BufferedWriter out = new BufferedWriter(new FileWriter("sha1"));
					out.write(thisComputer());
					out.close();
				} 
				catch (Exception e1)
				{
						e1.getMessage();
				}
				JOptionPane.showMessageDialog(null, "Done. please login again!");
				dispose();
				Login frame = new Login();
				frame.setLocationRelativeTo(null);
				frame.setVisible(true);
			}
			else 
				JOptionPane.showMessageDialog(null, "Wrong Password, Try again!");
			
		}
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
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Exit");
			putValue(SHORT_DESCRIPTION, "Cancel and exit");
		}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}

}
