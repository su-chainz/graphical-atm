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
	private JButton save;
	
	JTextField accountNumber;
	JTextField firstName;
	JTextField lastName;
	JTextField address;
	JTextField city;
	JTextField state;
	JTextField postalCode;
	JTextField dob;
	JTextField phoneNumber;
	JTextField pin;
	
	private BankAccount account;
	private User user;
	private boolean editable = false;
	
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
	}
	
	public void initInfo() {
		initAccountNumber();
		initFirstName();
		initLasttName();
		initAddress();
		initCity();
		initState();
		initPostalCode();
		initDob();
		initPhoneNumber();
		initPin();
		initEditSaveButton();
	}
	
	private void initLogoutButton() {
		logoutButton = new JButton("Back to Home");
		logoutButton.setBounds(50, 30, 200, 35);
		logoutButton.addActionListener(this);
		this.add(logoutButton);
	}
	
	public void initAccountNumber() {
		accountNumber = new JTextField(20);
		accountNumber.setBounds(100, 70, 200, 35);
		accountNumber.setText("" + account.getAccountNumber());
		accountNumber.setEditable(false);

		this.add(accountNumber);
	}
	
	public void initFirstName() {
		firstName = new JTextField(20);
		firstName.setBounds(100, 110, 200, 35);
		firstName.setText(account.getUser().getFirstName());
		firstName.setEditable(false);

		this.add(firstName);
	}
	
	public void initLasttName() {
		lastName = new JTextField(20);
		lastName.setBounds(100, 150, 200, 35);
		lastName.setText(account.getUser().getLastName());
		lastName.setEditable(false);

		this.add(lastName);
	}
	
	public void initAddress() {
		address = new JTextField(50);
		address.setBounds(100, 190, 200, 35);
		address.setText(account.getUser().getStreetAddress());
		address.setEditable(editable);
		
		this.add(address);
	}
	
	public void initCity() {
		city = new JTextField(50);
		city.setBounds(100, 230, 200, 35);
		city.setText(account.getUser().getCity());
		city.setEditable(editable);
		
		this.add(city);
	}
	
	public void initState() {
		state = new JTextField(20);
		state.setBounds(100, 270, 200, 35);
		state.setText(account.getUser().getState());
		state.setEditable(editable);
		
		this.add(state);
	}
	
	public void initPostalCode() {
		postalCode = new JTextField(20);
		postalCode.setBounds(100, 310, 200, 35);
		postalCode.setText(account.getUser().getZip());
		postalCode.setEditable(editable);
		
		this.add(postalCode);
	}
	
	public void initDob() {
		dob = new JTextField(20);
		dob.setBounds(100, 310, 200, 35);
		dob.setText("" + account.getUser().getDob());
		dob.setEditable(false);
		
		this.add(dob);
	}
	
	public void initPhoneNumber() {
		phoneNumber = new JTextField(20);
		phoneNumber.setBounds(100, 350, 200, 35);
		phoneNumber.setText("" + account.getUser().getPhone());
		phoneNumber.setEditable(editable);
		
		this.add(phoneNumber);
	}
	
	public void initPin() {
		pin = new JTextField(20);
		pin.setBounds(100, 390, 200, 35);
		pin.setText("" + account.getUser().getPin());
		pin.setEditable(editable);
		
		this.add(pin);
	}
	
	private void initEditSaveButton() {
		if(editable == false) {
			edit = new JButton("Edit");
			edit.setBounds(150, 440, 200, 35);
			edit.addActionListener(this);
			
			this.add(edit);
		} else {
			save = new JButton("Save");
			save.setBounds(150, 440, 200, 35);
			save.addActionListener(this);
			
			this.add(save);
		}

	}
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR The WithdrawView class is not serializable.");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(logoutButton)) {
			this.removeAll();
			this.initialize();
			initInfo();
			manager.initAccountNumber();
			manager.switchTo(ATM.HOME_VIEW);
		}
		if (source.equals(edit)) {
			this.removeAll();
			this.initialize();
			initInfo();
			editable = true;
		}
		if (source.equals(save)) {
			user.setStreetAddress(address.getText());
			user.setCity(city.getText());
			user.setState(state.getText());
			user.setZip(postalCode.getText());
			user.setPhone(Long.parseLong(phoneNumber.getText()));
			user.setPin(user.getPin(), Integer.parseInt(pin.getText()));
			manager.updateAccount(account);
			editable = false;
			this.removeAll();
			this.initialize();
			initInfo();
		}
	}
}