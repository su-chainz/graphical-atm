package controller;

import java.awt.CardLayout;
import java.awt.Container;
import javax.swing.JOptionPane;
import data.Database;
import model.BankAccount;
import view.ATM;
import view.HomeView;
import view.LoginView;
import view.WithdrawView;
import view.InformationView;


public class ViewManager {
	
	private Container views;				// the collection of all views in the application
	private Database db;					// a reference to the database
	private BankAccount account;			// the user's bank account
	private BankAccount destination;		// an account to which the user can transfer funds
	
	/**
	 * Constructs an instance (or object) of the ViewManager class.
	 * 
	 * @param layout
	 * @param container
	 */
	
	public ViewManager(Container views) {
		this.views = views;
		this.db = new Database();
	}
	
	///////////////////// INSTANCE METHODS ////////////////////////////////////////////
	
	/**
	 * Routes a login request from the LoginView to the Database.
	 * 
	 * @param accountNumber
	 * @param pin
	 */
	
	public void login(String accountNumber, char[] pin) {
		try {
			account = db.getAccount(Long.valueOf(accountNumber), Integer.valueOf(new String(pin)));
			
			if (account == null || account.getStatus() == 'N') {
				LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
				lv.updateErrorMessage("Invalid account number and/or PIN.");
			} else {
				HomeView hv = ((HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX]);
				hv.setAccount(account);
				switchTo(ATM.HOME_VIEW);
				
				LoginView lv = ((LoginView) views.getComponents()[ATM.LOGIN_VIEW_INDEX]);
				lv.updateErrorMessage("");
			}
		} catch (NumberFormatException e) {
			// ignore
		}
	}
	public void showLabels() {
		HomeView hv = ((HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX]);
		hv.initAccountNumber();
	}
	
	public void closeAccount() {
		db.closeAccount(account);
		setAccount(null);
		setDestination(null);
		switchTo(ATM.LOGIN_VIEW);
	}
	
	private void setDestination(BankAccount destination) {
		this.destination = destination;
	}

	public void insertAccount(BankAccount account) {
		db.insertAccount(account);
	}
	
	public long newAccountNumber() {
		return db.getMaxAccountNumber() + 1;
	}
	
	
	
	public long getAccountNumber() {
		try {
			System.out.println(account.getAccountNumber());
			return account.getAccountNumber();
		}
		catch (NullPointerException e) {
			return -1;
		}
		
	
		
	}
	
	public void initAccountNumber() {
		HomeView hv = ((HomeView) views.getComponents()[ATM.HOME_VIEW_INDEX]);
		hv.initAccountNumber();
	}
	
	public void initFirstName() {
		InformationView iv = ((InformationView) views.getComponents()[ATM.INFORMATION_VIEW_INDEX]);
		iv.initInfo();
	}
	
	
	public int deposit(double amount) {
		return account.deposit(amount);
	}
	
	public int withdraw(double amount) {
		return account.withdraw(amount);
	}
	public void transfer(long accountNumber, double amount) {
		this.destination = db.getAccount(accountNumber);
		account.transfer(destination, amount);
		updateAccount(account);
		updateAccount(destination);
	}
	public BankAccount getAccount(long accountNumber) {
		return db.getAccount(accountNumber);
	}
	public void logout() {
		int choice = JOptionPane.showConfirmDialog(
			views,
			"Are you sure?",
			"Shutdown ATM",
			JOptionPane.YES_NO_OPTION,
			JOptionPane.QUESTION_MESSAGE
		);
			
		if (choice == 0) {
			account = null;
			switchTo(ATM.LOGIN_VIEW);
		}	
	}
	
	/**
	 * Switches the active (or visible) view upon request.
	 * 
	 * @param view
	 */
	
	public void switchTo(String view) {
		if (view == ATM.INFORMATION_VIEW) {
			InformationView iv = ((InformationView) views.getComponents()[ATM.INFORMATION_VIEW_INDEX]);
			iv.setAccount(account);
			iv.initInfo();
		}
		((CardLayout) views.getLayout()).show(views, view);
	}
	
	/**
	 * Routes a shutdown request to the database before exiting the application. This
	 * allows the database to clean up any open resources it used.
	 */
	
	public void shutdown() {
		try {			
			int choice = JOptionPane.showConfirmDialog(
				views,
				"Are you sure?",
				"Shutdown ATM",
				JOptionPane.YES_NO_OPTION,
				JOptionPane.QUESTION_MESSAGE
			);
			
			if (choice == 0) {
				db.shutdown();
				System.exit(0);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public BankAccount getAccount() {
		return account;
	}

	public void setAccount(BankAccount account) {
		this.account = account;
	}
	
	public void setAccountInfo(BankAccount account) {
		InformationView iv = ((InformationView) views.getComponents()[ATM.INFORMATION_VIEW_INDEX]);
		iv.setAccount(account);
	}
	
	public boolean updateAccount(BankAccount account) {
		return db.updateAccount(account);
	}
	
	public boolean updateDestinationAccount(BankAccount destination) {
		return db.updateAccount(destination);
	}
}