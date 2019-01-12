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
public class CreateView extends JPanel implements ActionListener {

	private JTextField firstName;
	private JTextField lastName;
	private JTextField phoneNumber;
	private JTextField address;
	private JTextField city;
	private JTextField postalCode;
	private JTextField pin;
	private JButton logoutButton;
	private JComboBox<Integer> month;
	private JComboBox<Integer> year;
	private JComboBox<Integer> day;
	private JComboBox<String> state;
	private JButton submit;
	

	private ViewManager manager; // manages interactions between the views, model, and database

	/**
	 * Constructs an instance (or object) of the CreateView class.
	 * 
	 * @param manager
	 */

	public CreateView(ViewManager manager) {
		super();

		this.manager = manager;
		initialize();
	}

	///////////////////// PRIVATE METHODS
	///////////////////// /////////////////////////////////////////////

	/*
	 * Initializes the CreateView components.
	 */

	private void initialize() {
		this.setLayout(null);
		this.add(new javax.swing.JLabel("CreateView", javax.swing.SwingConstants.CENTER));
		initFirstName();
		initLastName();
		initDatePicker();
		initPhoneNumber();
		initAddress();
		initCity();
		initState();
		initPostalCode();
		initPIN();
		initLogoutButton();
		initSubmitButton();
		initAccountNumber();
		
		// TODO
		//
		// this is where you should build the CreateView (i.e., all the components that
		// allow the user to enter his or her information and create a new account).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.
	}

	private void initAccountNumber() {
		JLabel label = new JLabel("Account Number: " + manager.newAccountNumber());
		label.setBounds(275, 30, 200, 35);
		label.setLabelFor(null);
		label.setFont(new Font("DialogInput", Font.PLAIN, 12));
		
		this.add(label);
	}
	private void initFirstName() {
		JLabel label = new JLabel("First Name", SwingConstants.RIGHT);
		label.setBounds(100, 80, 95, 35);
		label.setLabelFor(firstName);
		label.setFont(new Font("DialogInput", Font.PLAIN, 14));

		firstName = new JTextField(20);
		firstName.setBounds(205, 80, 200, 35);

		this.add(label);
		this.add(firstName);
	}

	private void initLastName() {
		JLabel label = new JLabel("Last Name", SwingConstants.RIGHT);
		label.setBounds(100, 120, 95, 35);
		label.setLabelFor(lastName);
		label.setFont(new Font("DialogInput", Font.PLAIN, 14));

		lastName = new JTextField(30);
		lastName.setBounds(205, 120, 200, 35);

		this.add(label);
		this.add(lastName);
	}

	private void initDatePicker() {
		JLabel label = new JLabel("Date of Birth", SwingConstants.RIGHT);
		label.setBounds(55, 160, 140, 35);
		label.setFont(new Font("DialogInput", Font.PLAIN, 14));

		Integer[] months = { 01, 02, 03, 04, 05, 06, 07, 8, 9, 10, 11, 12 };
		month = new JComboBox<Integer>(months);
		month.setBounds(205, 160, 85, 30);

		int x = 0;
		Integer[] days = new Integer[31];
		for (int i = 1; i <= 31; i++) {
			days[x] = i;
			x++;
		}
		
		day = new JComboBox<Integer>(days);
		day.setBounds(300, 160, 85, 30);
		int m = 0;
		Integer[] years = new Integer[119];
		for (int i = 1900; i <= 2018; i++) {
			years[m] = i;
			m++;
		}

		year = new JComboBox<Integer>(years);
		year.setBounds(400, 160, 85, 30);

		this.add(label);
		this.add(month);
		this.add(day);
		this.add(year);
	}

	private void initPhoneNumber() {
		JLabel label = new JLabel("Phone Number", SwingConstants.RIGHT);
		label.setBounds(75, 200, 120, 35);
		label.setLabelFor(phoneNumber);
		label.setFont(new Font("DialogInput", Font.PLAIN, 14));

		phoneNumber = new JTextField(10);
		phoneNumber.setBounds(205, 200, 200, 35);
		// TO DO make it split into 4 segments
		this.add(label);
		this.add(phoneNumber);
	}

	private void initAddress() {
		JLabel label = new JLabel("Street Address", SwingConstants.RIGHT);
		label.setBounds(75, 240, 120, 35);
		label.setLabelFor(address);
		label.setFont(new Font("DialogInput", Font.PLAIN, 14));

		address = new JTextField(30);
		address.setBounds(205, 240, 200, 35);
		this.add(label);
		this.add(address);
	}

	private void initCity() {
		JLabel label = new JLabel("City", SwingConstants.RIGHT);
		label.setBounds(75, 280, 120, 35);
		label.setLabelFor(city);
		label.setFont(new Font("DialogInput", Font.PLAIN, 14));

		city = new JTextField(20);
		city.setBounds(205, 280, 200, 35);
		this.add(label);
		this.add(city);
	}

	private void initPostalCode() {
		JLabel label = new JLabel("Postal Code", SwingConstants.RIGHT);
		label.setBounds(75, 320, 120, 35);
		label.setLabelFor(postalCode);
		label.setFont(new Font("DialogInput", Font.PLAIN, 14));

		postalCode = new JTextField(5);
		postalCode.setBounds(205, 320, 200, 35);
		this.add(label);
		this.add(postalCode);
	}

	private void initPIN() {
		JLabel label = new JLabel("PIN", SwingConstants.RIGHT);
		label.setBounds(75, 360, 120, 35);
		label.setLabelFor(pin);
		label.setFont(new Font("DialogInput", Font.PLAIN, 14));

		pin = new JTextField(4);
		pin.setBounds(205, 360, 200, 35);
		this.add(label);
		this.add(pin);
	}

	private void initState() {
		JLabel label = new JLabel("State", SwingConstants.RIGHT);
		label.setBounds(60, 400, 140, 35);
		label.setFont(new Font("DialogInput", Font.PLAIN, 14));
		String[] states = { "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL", "IN",
				"KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND", "NE", "NH", "NJ", "NM",
				"NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN", "TX", "UT", "VA", "VT", "WA", "WI", "WV",
				"WY" };
		state = new JComboBox<String>(states);
		state.setBounds(205, 400, 85, 30);

		this.add(label);
		this.add(state);

	}

	private void initLogoutButton() {
		logoutButton = new JButton("Cancel");
		logoutButton.setBounds(50, 30, 200, 35);
		logoutButton.addActionListener(this);

		this.add(logoutButton);
	}
	
	private void initSubmitButton() {
		submit = new JButton("Submit");
		submit.setBounds(150, 440, 200, 35);
		submit.addActionListener(this);
		
		this.add(submit);
	}
	/*
	 * CreateView is not designed to be serialized, and attempts to serialize will
	 * throw an IOException.
	 * 
	 * @param oos
	 * 
	 * @throws IOException
	 */

	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR The CreateView class is not serializable.");
	}

	///////////////////// OVERRIDDEN METHODS
	///////////////////// //////////////////////////////////////////

	/*
	 * Responds to button clicks and other actions performed in the CreateView.
	 * 
	 * @param e
	 */

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(logoutButton)) {
			manager.logout();
			this.removeAll();
			this.initialize();
		}
		
		if (source.equals(submit)) {
			User user = new User(Integer.parseInt(pin.getText()), (int) year.getSelectedItem() * 1000 + (int) day.getSelectedItem() + (int) month.getSelectedItem() * 100, (long) Long.parseLong(phoneNumber.getText()), firstName.getText(), lastName.getText(), address.getText(), city.getText(), state.getSelectedItem().toString(), postalCode.getText());
			BankAccount account = new BankAccount('y', manager.newAccountNumber(), 0, user);
			manager.setAccount(account);
			manager.getAccount().setUser(user);
			manager.insertAccount(account);
			manager.switchTo(ATM.LOGIN_VIEW);
			this.removeAll();
			this.initialize();
		}

		// TODO
		//
		// this is where you'll setup your action listener, which is responsible for
		// responding to actions the user might take in this view (an action can be a
		// user clicking a button, typing in a textfield, etc.).
		//
		// feel free to use my action listener in LoginView.java as an example.
	}
}