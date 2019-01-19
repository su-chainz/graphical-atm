package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import controller.ViewManager;
import model.User;
import model.BankAccount;
import data.Database;

@SuppressWarnings("serial")
public class InformationView extends JPanel implements ActionListener {

	private JButton logoutButton;
	private JButton edit;
	private BankAccount account;
	private User user;
	
	private ViewManager manager;

	public InformationView(ViewManager manager) {
		super();

		this.manager = manager;
		initialize();
	}
	
	public void setAccount(BankAccount account) {
		this.account = account;
		this.user = account.getUser();
		this.removeAll();
		this.initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
		this.add(new javax.swing.JLabel("InformationView", javax.swing.SwingConstants.CENTER));
		initLogoutButton();
		initFirstName();
	}

	private void initLogoutButton() {
		logoutButton = new JButton("Back to Home");
		logoutButton.setBounds(50, 30, 200, 35);
		logoutButton.addActionListener(this);
		this.add(logoutButton);
	}
	
	public void initFirstName() {
		JLabel label = new JLabel("Name: " + account.getUser().getFirstName() + " " + account.getUser().getLastName(), SwingConstants.RIGHT);
		label.setBounds(100, 80, 95, 35);
		label.setFont(new Font("DialogInput", Font.PLAIN, 14));


		this.add(label);

	}

//	private void initDatePicker() {
//		JLabel label = new JLabel("Date of Birth", SwingConstants.RIGHT);
//		label.setBounds(55, 160, 140, 35);
//		label.setFont(new Font("DialogInput", Font.PLAIN, 14));
//
//		Integer[] months = { 01, 02, 03, 04, 05, 06, 07, 8, 9, 10, 11, 12 };
//
//		int x = 0;
//		Integer[] days = new Integer[31];
//		for (int i = 1; i <= 31; i++) {
//			days[x] = i;
//			x++;
//		}
//
//		int m = 0;
//		Integer[] years = new Integer[119];
//		for (int i = 1900; i <= 2018; i++) {
//			years[m] = i;
//			m++;
//		}
//
//		this.add(label);
//
//	}
//
//	private void initPhoneNumber() {
//		JLabel label = new JLabel("Phone Number", SwingConstants.RIGHT);
//		label.setBounds(75, 200, 120, 35);
//		label.setLabelFor(phoneNumber);
//		label.setFont(new Font("DialogInput", Font.PLAIN, 14));
//
//
//		// TO DO make it split into 4 segments
//		this.add(label);
//	}
//
//	private void initAddress() {
//		JLabel label = new JLabel("Street Address", SwingConstants.RIGHT);
//		label.setBounds(75, 240, 120, 35);
//		label.setLabelFor(address);
//		label.setFont(new Font("DialogInput", Font.PLAIN, 14));
//
//
//		this.add(label);
//
//	}
//
//	private void initCity() {
//		JLabel label = new JLabel("City", SwingConstants.RIGHT);
//		label.setBounds(75, 280, 120, 35);
//		label.setLabelFor(city);
//		label.setFont(new Font("DialogInput", Font.PLAIN, 14));
//
//		this.add(label);
//
//	}
//
//	private void initPostalCode() {
//		JLabel label = new JLabel("Postal Code", SwingConstants.RIGHT);
//		label.setBounds(75, 320, 120, 35);
//		label.setLabelFor(postalCode);
//		label.setFont(new Font("DialogInput", Font.PLAIN, 14));
//
//		this.add(label);
//
//	}
//	
//	private void initState() {
//		JLabel label = new JLabel("State", SwingConstants.RIGHT);
//		label.setBounds(60, 400, 140, 35);
//		label.setFont(new Font("DialogInput", Font.PLAIN, 14));
//		String[] states = { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN",
//				"KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM",
//				"NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV",
//				"WY" };
//
//		this.add(label);
//
//	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR The WithdrawView class is not serializable.");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(logoutButton)) {
			this.removeAll();
			this.initialize();
			manager.initAccountNumber();
			manager.switchTo(ATM.HOME_VIEW);
		}
		if (source.equals(edit)) {

			manager.initAccountNumber();
			this.removeAll();
			this.initialize();
			manager.switchTo(ATM.HOME_VIEW);
		}
	}
}