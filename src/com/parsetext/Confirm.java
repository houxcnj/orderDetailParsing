package com.parsetext;


import javax.swing.JDialog;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.SwingConstants;
import javax.swing.AbstractAction;
import java.awt.event.ActionEvent;
import javax.swing.Action;

public class Confirm extends JDialog {
	private final Action action = new SwingAction();
	private final Action action_1 = new SwingAction_1();

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Confirm dialog = new Confirm();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/
	/**
	 * Create the dialog.
	 */
	public Confirm() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		
		JButton btnOk = new JButton("OK");
		btnOk.setAction(action);
		btnOk.setBounds(163, 149, 117, 29);
		getContentPane().add(btnOk);
		
		JLabel lblNewLabel = new JLabel("Please Contact Your Supervisor!");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyChar() == 'u' )
					System.out.println("great!");
			}
		});
		lblNewLabel.setBounds(98, 51, 256, 63);
		getContentPane().add(lblNewLabel);
		
		JButton btnUnlock = new JButton("Unlock");
		btnUnlock.setAction(action_1);
		btnUnlock.setBounds(163, 202, 117, 29);
		getContentPane().add(btnUnlock);

	}
	private class SwingAction extends AbstractAction {
		public SwingAction() {
			putValue(NAME, "OK");
			putValue(SHORT_DESCRIPTION, "Exit");
		}
		public void actionPerformed(ActionEvent e) {
			System.exit(0);
		}
	}
	private class SwingAction_1 extends AbstractAction {
		public SwingAction_1() {
			putValue(NAME, "Unlock");
			putValue(SHORT_DESCRIPTION, "Ask supervisor to unlock the software!");
		}
		public void actionPerformed(ActionEvent e) {
			Unlock sv = new Unlock();
			sv.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			sv.setLocationRelativeTo(null);
			sv.setVisible(true);
			dispose();
		}
	}
}
