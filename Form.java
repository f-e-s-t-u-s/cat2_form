// package dev.clint;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Form {

	private JFrame frame;
	private JTextField nameText;
	private JTextField usernameText;
	private JTextField emailText;
	private JTextField phoneText;
	private JTextField addressText;
	private JPasswordField passwordText;
	private JPasswordField confPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Form window = new Form();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Form() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 558, 533);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JLabel lblRegisterForm = new JLabel("Register Form");
		lblRegisterForm.setFont(new Font("Tahoma", Font.PLAIN, 30));
		lblRegisterForm.setBounds(15, 16, 234, 37);
		frame.getContentPane().add(lblRegisterForm);

		nameText = new JTextField();
		nameText.setBounds(181, 78, 339, 26);
		frame.getContentPane().add(nameText);
		nameText.setColumns(10);

		usernameText = new JTextField();
		usernameText.setColumns(10);
		usernameText.setBounds(181, 120, 339, 26);
		frame.getContentPane().add(usernameText);

		emailText = new JTextField();
		emailText.setColumns(10);
		emailText.setBounds(181, 248, 339, 26);
		frame.getContentPane().add(emailText);

		phoneText = new JTextField();
		phoneText.setColumns(10);
		phoneText.setBounds(181, 291, 339, 26);
		frame.getContentPane().add(phoneText);

		addressText = new JTextField();
		addressText.setColumns(10);
		addressText.setBounds(181, 333, 339, 26);
		frame.getContentPane().add(addressText);

		JLabel lblName = new JLabel("Name :");
		lblName.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblName.setHorizontalAlignment(SwingConstants.RIGHT);
		lblName.setBounds(15, 81, 151, 20);
		frame.getContentPane().add(lblName);

		JLabel lblUsername = new JLabel("Username :");
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setHorizontalAlignment(SwingConstants.RIGHT);
		lblUsername.setBounds(15, 123, 151, 20);
		frame.getContentPane().add(lblUsername);

		JLabel lblConfirmPassword = new JLabel("Confirm Password :");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblConfirmPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblConfirmPassword.setBounds(15, 209, 151, 20);
		frame.getContentPane().add(lblConfirmPassword);

		JLabel lblEmail = new JLabel("Email :");
		lblEmail.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblEmail.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEmail.setBounds(15, 251, 151, 20);
		frame.getContentPane().add(lblEmail);

		JLabel lblPhone = new JLabel("Phone :");
		lblPhone.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPhone.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPhone.setBounds(15, 294, 151, 20);
		frame.getContentPane().add(lblPhone);

		JLabel lblAddress = new JLabel("Address :");
		lblAddress.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblAddress.setHorizontalAlignment(SwingConstants.RIGHT);
		lblAddress.setBounds(15, 336, 151, 20);
		frame.getContentPane().add(lblAddress);

		JLabel lblPassword = new JLabel("Password :");
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPassword.setBounds(15, 165, 151, 20);
		frame.getContentPane().add(lblPassword);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
				String username = usernameText.getText();
				String password = String.valueOf(passwordText.getPassword());
				String confirm = String.valueOf(confPassword.getPassword());
				String email = emailText.getText();
				String phone = phoneText.getText();
				String address = addressText.getText();

				if (password.equals(confirm)) {
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/form", "festus004",
								"nixcraft@mysql");
						String sql = "INSERT INTO Registration (Name, Username, Password, Email, Phone, Address) VALUES (?, ?, ?, ?, ?, ?)";
						PreparedStatement preparedStatement = con.prepareStatement(sql);
						preparedStatement.setString(1, name);
						preparedStatement.setString(2, username);
						preparedStatement.setString(3, password);
						preparedStatement.setString(4, email);
						preparedStatement.setString(5, phone);
						preparedStatement.setString(6, address);
						preparedStatement.executeUpdate();
						preparedStatement.close();
						con.close();
						nameText.setText("");
						usernameText.setText("");
						passwordText.setText("");
						confPassword.setText("");
						emailText.setText("");
						phoneText.setText("");
						addressText.setText("");
						JOptionPane.showMessageDialog(null, "User added!");

					} catch (Exception err) {
						err.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(null, "Password and confirmation do not match!");
				}
			}

		});
		btnSubmit.setBounds(181, 402, 90, 29);
		frame.getContentPane().add(btnSubmit);

		JButton btnClose = new JButton("Close");
		btnClose.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent a) {
				System.exit(0);
			}
		});
		btnClose.setBounds(430, 402, 90, 29);
		frame.getContentPane().add(btnClose);

		JButton btnReset = new JButton("Reset");
		btnReset.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				nameText.setText("");
				usernameText.setText("");
				passwordText.setText("");
				confPassword.setText("");
				emailText.setText("");
				phoneText.setText("");
				addressText.setText("");
			}
		});
		btnReset.setBounds(300, 402, 103, 29);
		frame.getContentPane().add(btnReset);

		passwordText = new JPasswordField();
		passwordText.setBounds(181, 162, 339, 26);
		frame.getContentPane().add(passwordText);

		confPassword = new JPasswordField();
		confPassword.setBounds(181, 206, 339, 26);
		frame.getContentPane().add(confPassword);
	}
}