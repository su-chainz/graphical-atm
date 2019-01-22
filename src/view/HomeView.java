package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.ViewManager;
import model.BankAccount;
import model.User;
import view.LoginView;

@SuppressWarnings("serial")
public class HomeView extends JPanel implements ActionListener {
	
	private JButton logoutButton;
	private JButton withdrawButton;
	private JButton depositButton;
	private JButton transferButton;
	private JButton informationButton;
	private JButton closeButton;
	private ViewManager manager;		// manages interactions between the views, model, and database
	private JLabel accountNumberLabel;
	private BankAccount account;
	private User user;
	
	/**
	 * Constructs an instance (or objects) of the HomeView class.
	 * 
	 * @param manager
	 */
	
	public HomeView(ViewManager manager) {
		super();
		
		this.manager = manager;
		initialize();
	}
	
	private void initLogoutButton() {
		logoutButton = new JButton("Logout");
		logoutButton.setBounds(30, 30, 200, 35);
		logoutButton.addActionListener(this);

		this.add(logoutButton);
	}
	
	private void initWithdrawButton() {
		withdrawButton = new JButton("Withdraw");
		withdrawButton.setBounds(30, 120, 200, 35);
		withdrawButton.addActionListener(this);

		this.add(withdrawButton);
	}
	
	private void initDepositButton() {
		depositButton = new JButton("Deposit");
		depositButton.setBounds(30, 160, 200, 35);
		depositButton.addActionListener(this);

		this.add(depositButton);
	}
	
	private void initCloseButton() {
		closeButton = new JButton("Close");
		closeButton.setBounds(30, 280, 200, 35);
		closeButton.addActionListener(this);

		this.add(closeButton);
	}
	
	private void initTransferButton() {
		transferButton = new JButton("Transfer");
		transferButton.setBounds(30, 200, 200, 35);
		transferButton.addActionListener(this);

		this.add(transferButton);
	}
	
	private void initInformationButton() {
		informationButton = new JButton("Information");
		informationButton.setBounds(30, 240, 200, 35);
		informationButton.addActionListener(this);

		this.add(informationButton);
	}
	
	public void initAccountNumber() {
		accountNumberLabel = new JLabel();
		accountNumberLabel.setText("Hello " + account.getUser().getFirstName() + " " + account.getUser().getLastName() + ", your account number is " + account.getAccountNumber() + " and your balance is " + account.getFormattedBalance());
		accountNumberLabel.setBounds(10, 75, 500, 35);
		accountNumberLabel.setFont(new Font("DialogInput", Font.PLAIN, 8));
		
		this.add(accountNumberLabel);
	}
	///////////////////// PRIVATE METHODS /////////////////////////////////////////////
	
	/*
	 * Initializes the HomeView components.
	 */
	
	private void initialize() {
		
		// TODO
		//
		// this is a placeholder for this view and should be removed once you start
		// building the HomeView.
		
		this.add(new javax.swing.JLabel("HomeView", javax.swing.SwingConstants.CENTER));
		
		this.setLayout(null);
		
		// TODO
		//
		// this is where you should build the HomeView (i.e., all the components that
		// allow the user to interact with the ATM - deposit, withdraw, transfer, etc.).
		//
		// feel free to use my layout in LoginView as an example for laying out and
		// positioning your components.

		initLogoutButton();
		initWithdrawButton();
		initDepositButton();
		initTransferButton();
		initInformationButton();
		initCloseButton();
	}
	
	public void setAccount(BankAccount account) {
		this.account = account;
		this.user = account.getUser();
		this.removeAll();
		this.initialize();
		initAccountNumber();
	}
	
	/*
	 * HomeView is not designed to be serialized, and attempts to serialize will throw an IOException.
	 * 
	 * @param oos
	 * @throws IOException
	 */
	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR: The HomeView class is not serializable.");
	}
	
	///////////////////// OVERRIDDEN METHODS //////////////////////////////////////////
	
	/*
	 * Responds to button clicks and other actions performed in the HomeView.
	 * 
	 * @param e
	 */
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(logoutButton)) {
			manager.logout();			
		} 
		if (source.equals(withdrawButton)) {
			this.remove(accountNumberLabel);
			manager.switchTo(ATM.WITHDRAW_VIEW);
		} 
		if (source.equals(depositButton)) {
			this.remove(accountNumberLabel);
			manager.switchTo(ATM.DEPOSIT_VIEW);
		} 
		if (source.equals(transferButton)) {
			this.remove(accountNumberLabel);
			manager.switchTo(ATM.TRANSFER_VIEW);
		} 
		if (source.equals(informationButton)) {
			this.remove(accountNumberLabel);
			manager.switchTo(ATM.INFORMATION_VIEW);
			manager.setAccount(account);
		} 
		if (source.equals(closeButton)) {
			manager.closeAccount();
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