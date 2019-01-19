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
public class TransferView extends JPanel implements ActionListener {

	private JButton logoutButton;
	private JTextField transferAmount;
	private JTextField transferRecipient;
	private JButton submit;
	
	private ViewManager manager;

	public TransferView(ViewManager manager) {
		super();

		this.manager = manager;
		initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
		this.add(new javax.swing.JLabel("WithdrawView", javax.swing.SwingConstants.CENTER));
		initLogoutButton();
		initTransferAmount();
		initTransferRecipient();
		initSubmitButton();
	}

	private void initLogoutButton() {
		logoutButton = new JButton("Back to Home");
		logoutButton.setBounds(50, 30, 200, 35);
		logoutButton.addActionListener(this);

		this.add(logoutButton);
	}
	
	private void initTransferAmount() {
		JLabel label = new JLabel("Amount:", SwingConstants.RIGHT);
		label.setBounds(75, 200, 120, 35);
		label.setLabelFor(transferAmount);
		label.setFont(new Font("DialogInput", Font.PLAIN, 14));

		transferAmount = new JTextField(20);
		transferAmount.setBounds(205, 200, 200, 35);
		this.add(label);
		this.add(transferAmount);
	}
	
	private void initTransferRecipient() {
		JLabel label = new JLabel("Recipient:", SwingConstants.RIGHT);
		label.setBounds(75, 240, 120, 35);
		label.setLabelFor(transferRecipient);
		label.setFont(new Font("DialogInput", Font.PLAIN, 14));

		transferRecipient = new JTextField(20);
		transferRecipient.setBounds(205, 240, 200, 35);
		this.add(label);
		this.add(transferRecipient);
	}
	
	private void initSubmitButton() {
		submit = new JButton("Submit");
		submit.setBounds(150, 440, 200, 35);
		submit.addActionListener(this);
		
		this.add(submit);
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
			manager.transfer(Long.parseLong(transferRecipient.getText()), Double.parseDouble(transferAmount.getText()));
			System.out.println(manager.getAccount(Long.parseLong(transferRecipient.getText())));
			
			manager.initAccountNumber();
			
			this.removeAll();
			this.initialize();
			
			manager.switchTo(ATM.HOME_VIEW);
		}
	}
}