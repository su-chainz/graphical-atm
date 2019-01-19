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
public class WithdrawView extends JPanel implements ActionListener {

	private JButton logoutButton;
	private JTextField withdrawAmount;
	private JButton submit;
	
	private ViewManager manager;

	public WithdrawView(ViewManager manager) {
		super();

		this.manager = manager;
		initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
		this.add(new javax.swing.JLabel("WithdrawView", javax.swing.SwingConstants.CENTER));
		initLogoutButton();
		initWithdrawAmount();
		initSubmitButton();
	}

	private void initLogoutButton() {
		logoutButton = new JButton("Back to Home");
		logoutButton.setBounds(50, 30, 200, 35);
		logoutButton.addActionListener(this);

		this.add(logoutButton);
	}
	
	private void initWithdrawAmount() {
		JLabel label = new JLabel("Amount:", SwingConstants.RIGHT);
		label.setBounds(75, 200, 120, 35);
		label.setLabelFor(withdrawAmount);
		label.setFont(new Font("DialogInput", Font.PLAIN, 14));

		withdrawAmount = new JTextField(20);
		withdrawAmount.setBounds(205, 200, 200, 35);
		this.add(label);
		this.add(withdrawAmount);
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
			int status = manager.withdraw(Double.parseDouble(withdrawAmount.getText()));
			
			System.out.println("status: " + status);
			
			// check status
			manager.initAccountNumber();
			this.removeAll();
			this.initialize();
			manager.switchTo(ATM.HOME_VIEW);
		}
	}
}