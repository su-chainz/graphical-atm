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
	
	private ViewManager manager;

	public WithdrawView(ViewManager manager) {
		super();

		this.manager = manager;
		initialize();
	}
	
	private void initialize() {
		this.setLayout(null);
		this.add(new javax.swing.JLabel("WithdrawView", javax.swing.SwingConstants.CENTER));
	}

	
	private void writeObject(ObjectOutputStream oos) throws IOException {
		throw new IOException("ERROR The WithdrawView class is not serializable.");
	}
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if (source.equals(logoutButton)) {
			manager.logout();
		}
	}
}