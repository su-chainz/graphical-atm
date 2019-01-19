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
public class DepositView extends JPanel implements ActionListener {

	private JButton logoutButton;
	private JTextField depositAmount;
	private JButton submit;
	
	private ViewManager manager;
	private BankAccount account;

	public DepositView(ViewManager manager) {
		super();

		this.manager = manager;
		initialize();
	}
	
	private void initLogoutButton() {
		logoutButton = new JButton("Back to Home");
		logoutButton.setBounds(50, 30, 200, 35);
		logoutButton.addActionListener(this);

		this.add(logoutButton);
	}
	
	private void initDepositAmount() {
		JLabel label = new JLabel("Amount:", SwingConstants.RIGHT);
		label.setBounds(75, 200, 120, 35);
		label.setLabelFor(depositAmount);
		label.setFont(new Font("DialogInput", Font.PLAIN, 14));

		depositAmount = new JTextField(20);
		depositAmount.setBounds(205, 200, 200, 35);
		this.add(label);
		this.add(depositAmount);
	}
	
	private void initSubmitButton() {
		submit = new JButton("Submit");
		submit.setBounds(150, 440, 200, 35);
		submit.addActionListener(this);
		
		this.add(submit);
	}
	
	private void initialize() {
		this.setLayout(null);
		this.add(new javax.swing.JLabel("WithdrawView", javax.swing.SwingConstants.CENTER));
		initLogoutButton();
		initDepositAmount();
		initSubmitButton();
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
			manager.initAccountNumber();
			manager.switchTo(ATM.HOME_VIEW);
		}
		if (source.equals(submit)) {
//			account.setBalance(account.getBalance() + Double.parseDouble(depositAmount.getText()));
			int status = manager.deposit(Double.parseDouble(depositAmount.getText()));
			
			System.out.println("status: " + status);
			
			// check status
			manager.initAccountNumber();
			this.removeAll();
			this.initialize();
			manager.switchTo(ATM.HOME_VIEW);
		}
	}
}